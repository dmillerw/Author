package dmillerw.json.common;

import com.google.common.collect.Lists;
import dmillerw.json.common.data.Book;
import dmillerw.json.client.data.Page;
import dmillerw.json.client.data.Template;
import dmillerw.json.client.data.widget.WidgetContainer;
import dmillerw.json.client.data.widget.Widget;
import dmillerw.json.lib.ExtensionFilter;
import dmillerw.json.lib.JsonLib;

import java.io.File;
import java.io.FileReader;

/**
 * @author dmillerw
 */
public class Loader {

    public static File rootFolder;
    public static File bookFolder;
    public static File templateFolder;
    public static File pageFolder;
    public static File resourceFolder;

    public static void initialize(File config) {
        rootFolder = _new(config, "JSONcumentation");
        bookFolder = _new(rootFolder, "book");
        templateFolder = _new(rootFolder, "template");
        pageFolder = _new(rootFolder, "page");
        resourceFolder = _new(rootFolder, "resources");

        for (File file : ExtensionFilter.JSON.listFiles(bookFolder)) {
            Registry.registerBook(parseBook(file));
        }

        for (File file : ExtensionFilter.JSON.listFiles(templateFolder)) {
            Registry.registerTemplate(parseTemplate(file));
        }

        for (File file : ExtensionFilter.JSON.listFiles(pageFolder)) {
            Page page = parsePage(file);
            Template template = Registry.getTemplate(page.type);
            Book owner = Registry.getBook(page.book);

            if (template != null) {
                page.template = template;
                page.widgets = Lists.newArrayList();

                for (WidgetContainer container : template.widgets) {
                    page.widgets.add(Widget.construct(container, page));
                }
            } else {
                throw new IllegalStateException("Page has invalid type: " + page.type);
            }

            if (owner != null) {
                page.template.widgets = null;
                page.variables = null;

                owner.attachPage(page);
            }
        }
    }

    private static File _new(File root, String dir) {
        File file = new File(root, dir);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    private static Book parseBook(File file) {
        return parse(file, Book.class);
    }

    private static Template parseTemplate(File file) {
        return parse(file, Template.class);
    }

    private static Page parsePage(File file) {
        return parse(file, Page.class);
    }

    private static <T> T parse(File file, Class<T> type) {
        try {
            return JsonLib.gson().fromJson(new FileReader(file), type);
        } catch (Exception ex) {
            return null;
        }
    }
}
