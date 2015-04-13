package dmillerw.author.client.data.widget.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmillerw.author.client.data.widget.Widget;
import dmillerw.author.client.gui.GuiBook;

import java.util.Map;

import static dmillerw.author.common.proxy.ClientProxy.smallFontRenderer;
import static dmillerw.author.lib.SafetyLib.getAsInt;
import static dmillerw.author.lib.SafetyLib.getAsStringArray;

/**
 * @author dmillerw
 */
public class WidgetTextBox extends Widget {

    private static final int DEFAULT_PADDING = 2;

    public int x;
    public int y;
    public int width;
    public int height;
    public int padding;

    public String[] text;
    @SideOnly(Side.CLIENT)
    public String[] paddedText;

    @Override
    public void onGuiOpen(GuiBook guiBook) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : text) {
            for (String split : smallFontRenderer.listFormattedStringToWidth(str, width - (padding * 2))) {
                stringBuilder.append(split).append("\n");
            }
        }
        paddedText = stringBuilder.toString().split("\n");
    }

    @Override
    public void draw(GuiBook guiBook, int mouseX, int mouseY, float partial) {
        for (int i=0; i<paddedText.length; i++) {
            String str = paddedText[i];
            smallFontRenderer.drawString(str, x + padding, y + (smallFontRenderer.FONT_HEIGHT * i), 0x000000);
        }
    }

    @Override
    public void acceptData(Map<String, Object> data) {
        this.x = getAsInt(data, "x", 0);
        this.y = getAsInt(data, "y", 0);
        this.width = getAsInt(data, "w", 0);
        this.height = getAsInt(data, "h", 0);
        this.text = getAsStringArray(data, "text", "", false);
        this.padding = getAsInt(data, "padding", DEFAULT_PADDING);
    }
}
