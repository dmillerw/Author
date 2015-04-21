package dmillerw.author.client.data;

import com.google.gson.JsonObject;
import dmillerw.author.common.Registry;

/**
 * @author dmillerw
 */
public class Page {

    public static Page[] construct(String type, JsonObject object) {
        if (type.equals("MARKDOWN")) {
            // Parse as markdown, which allows for continuous pages
        } else if (type.equals("FREEFORM")) {
            // Similar to a template, but all attributes are set in the entry object
        } else {
            // Configure based off the template and the data

            Template template = Registry.getTemplate(type);
        }
        return new Page[0];
    }
}
