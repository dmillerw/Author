package dmillerw.json.client.data.widget.impl;

import dmillerw.json.client.data.widget.Widget;
import dmillerw.json.client.gui.GuiBook;
import dmillerw.json.lib.SafetyLib;

import java.util.Map;

/**
 * @author dmillerw
 */
public class WidgetItem extends Widget {

    public int x;
    public int y;
    public int damage;

    public String item;

    @Override
    public void draw(GuiBook guiBook) {

    }

    @Override
    public void acceptData(Map<String, Object> data) {
        this.x = SafetyLib.getAsInt(data, "x", 0);
        this.y = SafetyLib.getAsInt(data, "y", 0);
        this.damage = SafetyLib.getAsInt(data, "damage", 0);
        this.item = SafetyLib.getAsString(data, "item", "", false);
    }
}
