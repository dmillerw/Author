package dmillerw.author.client.data.widget.impl;

import dmillerw.author.client.data.widget.Widget;
import dmillerw.author.client.gui.GuiBook;
import dmillerw.author.lib.SafetyLib;

import java.util.Map;

/**
 * @author dmillerw
 */
public class WidgetTextLabel extends Widget {

    public int x;
    public int y;

    public String text;

    @Override
    public void draw(GuiBook guiBook) {

    }

    @Override
    public void acceptData(Map<String, Object> data) {
        this.x = SafetyLib.getAsInt(data, "x", 0);
        this.y = SafetyLib.getAsInt(data, "y", 0);
        this.text = SafetyLib.getAsString(data, "text", "", false);
    }
}
