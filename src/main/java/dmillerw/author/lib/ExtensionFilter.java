package dmillerw.author.lib;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author dmillerw
 */
public class ExtensionFilter implements FilenameFilter {

    public static final ExtensionFilter JSON = new ExtensionFilter("author");
    public static final ExtensionFilter OGG = new ExtensionFilter("ogg");
    public static final ExtensionFilter TXT = new ExtensionFilter("txt");

    private final String extension;

    public ExtensionFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }

    public File[] listFiles(File file) {
        return file.listFiles(this);
    }
}