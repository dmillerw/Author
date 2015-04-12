package dmillerw.author.common;

import com.google.common.collect.Maps;
import dmillerw.author.common.data.Book;
import dmillerw.author.client.data.Template;

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
            throw new IllegalStateException("Duplicate book ident found: " + book.ident);
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
        if (templateMap.containsKey(template.ident)) {
            throw new IllegalStateException("Duplicate template ident found: " + template.ident);
        }
        templateMap.put(template.ident, template);
    }

    public static Template getTemplate(String ident) {
        return templateMap.get(ident);
    }
}
