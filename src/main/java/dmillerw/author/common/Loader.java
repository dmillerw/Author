package dmillerw.author.common;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dmillerw.author.client.data.Entry;
import dmillerw.author.client.data.Page;
import dmillerw.author.client.data.Template;
import dmillerw.author.common.data.Book;
import dmillerw.author.lib.ExtensionFilter;
import dmillerw.author.lib.JsonLib;
import dmillerw.author.lib.Log;
import scala.actors.threadpool.Arrays;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author dmillerw
 */
public class Loader {

    public static File rootFolder;
    public static File bookFolder;
    public static File templateFolder;
    public static File entryFolder;
    public static File resourceFolder;

    public static void initialize(File config) {
        rootFolder = dir(config, "Author");
        bookFolder = dir(rootFolder, "book");
        templateFolder = dir(rootFolder, "template");
        entryFolder = dir(rootFolder, "entry");
        resourceFolder = dir(rootFolder, "resources");

        // We start by loading the books, as they rule over all else
        for (File file : ExtensionFilter.JSON.listFiles(bookFolder)) {
            Log.debug("Loading book from file: " + file.getName());
            try {
                // We de-serialize manually because there's a lot of extra data in the container classes
                JsonElement jsonElement = JsonLib.gson().fromJson(new FileReader(file), JsonElement.class);

                if (!jsonElement.isJsonObject()) {
                    Log.warn("'" + file.getName() + "' is not an object file!");
                    continue;
                }

                JsonObject jsonObject = jsonElement.getAsJsonObject();

                if (!hasTypes(file, jsonObject, "ident", "resources", "dimensions")) {
                    continue;
                }

                Book book = new Book();
                book.ident = jsonObject.get("ident").getAsString();
                book.resources = JsonLib.gson().fromJson(jsonObject.get("resources"), Book.Resources.class);
                book.dimensions = JsonLib.gson().fromJson(jsonObject.get("dimensions"), Book.Dimensions.class);

                // For sanity
                book.resources.itemIcon = book.resources.itemIcon.replace(".png", "");

                if (!Registry.registerBook(book)) {
                    Log.warn("Duplicate book: Book with ident '" + book.ident + "' already found");
                }
            } catch (IOException ex) {
                Log.warn("Failed to deserialize '" + file.getName() + "': " + ex.getLocalizedMessage());
            }
        }

        // Templates come next, as entries depend on them
        for (File file : ExtensionFilter.JSON.listFiles(templateFolder)) {
            Log.debug("Loading template from file: " + file.getName());
            try {
                Template template = JsonLib.gson().fromJson(new FileReader(file), Template.class);
                if (!Registry.registerTemplate(template)) {
                    Log.warn("Duplicate template: Template with ident '" + template.ident + "' already found");
                }
            } catch (IOException ex) {
                Log.warn("Failed to deserialize '" + file.getName() + "': " + ex.getLocalizedMessage());
            }
        }

        // Finally on to entries! These are attached to the pre-existing book classes
        for (File file : ExtensionFilter.JSON.listFiles(entryFolder)) {
            Log.debug("Loading entry from file: " + file.getName());
            try {
                JsonElement jsonElement = JsonLib.gson().fromJson(new FileReader(file), JsonElement.class);

                if (!jsonElement.isJsonObject()) {
                    Log.warn("'" + file.getName() + "' is not an object file!");
                    continue;
                }

                JsonObject jsonObject = jsonElement.getAsJsonObject();

                if (!hasTypes(file, jsonObject, "ident", "book", "category", "pages")) {
                    continue;
                }

                Entry entry = new Entry();
                entry.ident = jsonObject.get("ident").getAsString();
                entry.book = jsonObject.get("book").getAsString();
                entry.category = jsonObject.get("category").getAsString();

                List<Page> list = Lists.newLinkedList();
                JsonArray pages = jsonObject.getAsJsonArray("pages");
                for (int i=0; i<pages.size(); i++) {
                    JsonObject object = pages.get(i).getAsJsonObject();
                    list.addAll(Arrays.asList(Page.construct(object.get("type").getAsString(), object)));
                }

                entry.pages = list.toArray(new Page[list.size()]);
            } catch (IOException ex) {
                Log.warn("Failed to deserialize '" + file.getName() + "': " + ex.getLocalizedMessage());
            }
        }
    }

    private static boolean hasTypes(File file, JsonObject jsonObject, String ... types) {
        boolean passed = true;
        for (String str : types) {
            if (!jsonObject.has(str)) {
                passed = false;
                Log.warn("'" + file.getName() + "' is missing a required key: " + str);
            }
        }
        return passed;
    }

    private static File dir(File root, String dir) {
        File file = new File(root, dir);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }
}
