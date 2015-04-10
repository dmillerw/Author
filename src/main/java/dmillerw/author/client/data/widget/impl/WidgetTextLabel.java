package dmillerw.author.client.data.widget.impl;

import dmillerw.author.client.data.widget.Widget;
import dmillerw.author.client.gui.GuiBook;

import java.util.Map;

import static dmillerw.author.common.proxy.ClientProxy.smallFontRenderer;
import static dmillerw.author.lib.SafetyLib.*;

/**
 * @author dmillerw
 */
public class WidgetTextLabel extends Widget {

    public int x;
    public int y;

    public boolean center;

    public String text;

    @Override
    public void draw(GuiBook guiBook) {
        if (center) {
            smallFontRenderer.drawString(text, x - (smallFontRenderer.getStringWidth(text) / 2), y, 0x000000);
        } else {
            smallFontRenderer.drawString(text, x, y, 0x000000);
        }
    }

    @Override
    public void acceptData(Map<String, Object> data) {
        this.x = getAsInt(data, "x", 0);
        this.y = getAsInt(data, "y", 0);
        this.center = getAsBoolean(data, "center", false);
        this.text = getAsString(data, "text", "", false);
    }
}
