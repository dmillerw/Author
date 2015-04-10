package dmillerw.author.client.data;

import dmillerw.author.client.data.widget.Widget;

import java.util.List;
import java.util.Map;

/**
 * @author dmillerw
 */
public class Page {

    public String book;
    public String type;
    public String title;
    public int guiWidth;
    public int guiHeight;
    public Map<String, Object> variables;

    public Template template;

    public List<Widget> widgets;
}
