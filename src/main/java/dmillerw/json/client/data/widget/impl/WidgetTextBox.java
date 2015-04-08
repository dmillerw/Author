package dmillerw.json.client.data.widget.impl;

import dmillerw.json.client.data.widget.Widget;
import dmillerw.json.client.gui.GuiBook;
import dmillerw.json.lib.SafetyLib;

import java.util.Map;

/**
 * @author dmillerw
 */
public class WidgetTextBox extends Widget {

    public int x;
    public int y;
    public int width;
    public int height;

    public String text;

    @Override
    public void draw(GuiBook guiBook) {

    }

    @Override
    public void acceptData(Map<String, Object> data) {
        this.x = SafetyLib.getAsInt(data, "x", 0);
        this.y = SafetyLib.getAsInt(data, "y", 0);
        this.width = SafetyLib.getAsInt(data, "w", 0);
        this.height = SafetyLib.getAsInt(data, "h", 0);
        this.text = SafetyLib.getAsString(data, "text", "", true);
    }
}
