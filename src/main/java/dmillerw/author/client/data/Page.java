package dmillerw.author.client.data;

import dmillerw.author.client.data.widget.Widget;
import dmillerw.author.client.gui.GuiBook;

import java.util.List;
import java.util.Map;

/**
 * @author dmillerw
 */
public class Page {

    public String ident;
    public String type;
    public Map<String, Object> variables;

    public Template template;

    public List<Widget> widgets;

    public void onGuiOpen(GuiBook guiBook) {
        for (Widget widget : widgets) {
            widget.onGuiOpen(guiBook);
        }
    }

    public void drawPage(GuiBook guiBook, int mouseX, int mouseY, float partial) {
        for (Widget widget : widgets) {
            widget.draw(guiBook, mouseX, mouseY, partial);
        }
    }

    public void onGuiClose(GuiBook guiBook) {
        for (Widget widget : widgets) {
            widget.onGuiClose(guiBook);
        }
    }
}
