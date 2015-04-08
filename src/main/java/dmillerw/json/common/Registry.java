package dmillerw.json.common;

import com.google.common.collect.Maps;
import dmillerw.json.common.data.Book;
import dmillerw.json.client.data.Template;

import java.util.Collection;
import java.util.Map;

/**
 * @author dmillerw
 */
public class Registry {

    private static final Map<String, Book> bookMap = Maps.newHashMap();
    private static final Map<String, Template> templateMap = Maps.newHashMap();

    public static void registerBook(Book book) {
        if (bookMap.containsKey(book.ident)) {
            throw new IllegalStateException("Duplicate book type found: " + book.ident);
        }
        bookMap.put(book.ident, book);
    }

    public static Book getBook(String ident) {
        return bookMap.get(ident);
    }

    public static Book[] getAllBooks() {
        Collection<Book> values = bookMap.values();
        return values.toArray(new Book[values.size()]);
    }

    public static void registerTemplate(Template template) {
        if (templateMap.containsKey(template.type)) {
            throw new IllegalStateException("Duplicate template type found: " + template.type);
        }
        templateMap.put(template.type, template);
    }

    public static Template getTemplate(String ident) {
        return templateMap.get(ident);
    }
}
