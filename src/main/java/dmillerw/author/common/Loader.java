package dmillerw.author.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import dmillerw.author.client.data.Page;
import dmillerw.author.client.data.Template;
import dmillerw.author.client.data.widget.Widget;
import dmillerw.author.client.data.widget.WidgetContainer;
import dmillerw.author.common.data.Book;
import dmillerw.author.lib.ExtensionFilter;
import dmillerw.author.lib.JsonLib;
import dmillerw.author.lib.Log;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

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
        rootFolder = _new(config, "Author");
        bookFolder = _new(rootFolder, "book");
        templateFolder = _new(rootFolder, "template");
        pageFolder = _new(rootFolder, "page");
        resourceFolder = _new(rootFolder, "resources");

        Map<String, Page> pages = Maps.newHashMap();

        for (File file : ExtensionFilter.JSON.listFiles(templateFolder)) {
            Registry.registerTemplate(parseTemplate(file));
        }

        for (File file : ExtensionFilter.JSON.listFiles(pageFolder)) {
            Page page = parsePage(file);
            Template template = Registry.getTemplate(page.type);

            if (template != null) {
                page.template = template;
                page.widgets = Lists.newArrayList();

                for (WidgetContainer container : template.widgets) {
                    page.widgets.add(Widget.construct(container, page));
                }
            } else {
                throw new IllegalStateException("Page has invalid type: " + page.type);
            }

            page.template.widgets = null;
            page.variables = null;

            pages.put(page.ident, page);
        }

        for (File file : ExtensionFilter.JSON.listFiles(bookFolder)) {
            Book book = parseBook(file);
            for (String str : book.pages) {
                Page page = pages.get(str);
                if (page != null) {
                    book.attachedPages.add(page);
                }
            }
            Registry.registerBook(book);
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
            Log.info("Parsing " + file.getName() + " as " + type.getSimpleName());
            return JsonLib.gson().fromJson(new FileReader(file), type);
        } catch (Exception ex) {
            return null;
        }
    }
}
