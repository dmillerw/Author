package dmillerw.json.client.data;

import dmillerw.json.client.data.widget.Widget;

import java.util.List;
import java.util.Map;

/**
 * @author dmillerw
 */
public class Page {

    public String book;
    public String type;
    public String title;
    public Map<String, Object> variables;

    public Template template;

    public List<Widget> widgets;
}
