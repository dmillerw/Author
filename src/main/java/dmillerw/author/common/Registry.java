package dmillerw.author.common;

import com.google.common.collect.Maps;
import dmillerw.author.client.data.Template;
import dmillerw.author.common.data.Book;

import java.util.Collection;
import java.util.Map;

/**
 * @author dmillerw
 */
public class Registry {

    private static final Map<String, Book> bookMap = Maps.newHashMap();
    private static final Map<String, Template> templateMap = Maps.newHashMap();

    public static boolean registerBook(Book book) {
        if (bookMap.containsKey(book.ident)) {
            return false;
        }
        bookMap.put(book.ident, book);
        return true;
    }

    public static Book getBook(String ident) {
        return bookMap.get(ident);
    }

    public static Book[] getAllBooks() {
        Collection<Book> values = bookMap.values();
        return values.toArray(new Book[values.size()]);
    }

    public static boolean registerTemplate(Template template) {
        if (templateMap.containsKey(template.ident)) {
            return false;
        }
        templateMap.put(template.ident, template);
        return true;
    }

    public static Template getTemplate(String ident) {
        return templateMap.get(ident);
    }
}
