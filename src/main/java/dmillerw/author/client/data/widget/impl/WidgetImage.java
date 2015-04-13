package dmillerw.author.client.data.widget.impl;

import com.google.gson.annotations.SerializedName;
import dmillerw.author.client.data.widget.Widget;
import dmillerw.author.client.gui.GuiBook;
import dmillerw.author.lib.SafetyLib;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Map;

/**
 * @author dmillerw
 */
public class WidgetImage extends Widget {

    public int x;
    public int y;
    public int u;
    public int width;
    public int height;
    public int v;
    @SerializedName("uv_width")
    public int uvWidth;
    @SerializedName("uv_height")
    public int uvHeight;

    public String path;

    private ResourceLocation resourceLocation;

    @Override
    public void onGuiOpen(GuiBook guiBook) {
        resourceLocation = new ResourceLocation("author:" + path);
    }

    @Override
    public void draw(GuiBook guiBook, int mouseX, int mouseY, float partial) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
        guiBook.drawTexturedRectangle(x, y, u, v, width, height, uvWidth, uvHeight);
    }

    @Override
    public void acceptData(Map<String, Object> data) {
        this.x = SafetyLib.getAsInt(data, "x", 0);
        this.y = SafetyLib.getAsInt(data, "y", 0);
        this.u = SafetyLib.getAsInt(data, "u", 0);
        this.v = SafetyLib.getAsInt(data, "v", 0);
        this.width = SafetyLib.getAsInt(data, "width", 0);
        this.height = SafetyLib.getAsInt(data, "height", 0);
        this.uvWidth = SafetyLib.getAsInt(data, "uv_width", width);
        this.uvHeight = SafetyLib.getAsInt(data, "uv_height", height);
        this.path = SafetyLib.getAsString(data, "path", "", false);
    }
}
