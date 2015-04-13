package dmillerw.author.client.gui;

import dmillerw.author.client.data.Page;
import dmillerw.author.common.data.Book;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author dmillerw
 */
public class GuiBook extends GuiScreen {

    private Book book;

    private Page currentPage;
    private int pageIndex;

    private int guiLeft;
    private int guiTop;

    private ResourceLocation backgroundImage;

    public GuiBook(Book book) {
        this.book = book;
        this.currentPage = book.getPage(0);
        this.pageIndex = 0;
        this.backgroundImage = new ResourceLocation("author:" + book.backgroundImage);
    }

    @Override
    public void initGui() {
        super.initGui();

        this.guiLeft = (this.width - book.guiWidth) / 2;
        this.guiTop = (this.height - book.guiHeight) / 2;

        if (currentPage != null) {
            currentPage.onGuiOpen(this);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partial) {
        super.drawScreen(mouseX, mouseY, partial);

        if (currentPage != null) {
            GL11.glColor4f(1, 1, 1, 1);

            mc.renderEngine.bindTexture(backgroundImage);
            drawTexturedModalRect(guiLeft, guiTop, 0, 0, book.guiWidth, book.guiHeight);

            GL11.glPushMatrix();
            GL11.glTranslated(guiLeft, guiTop, zLevel);
            currentPage.drawPage(this, mouseX, mouseY, partial);
            GL11.glPopMatrix();
        }
    }

    public final void drawTexturedRectangle(int x, int y, int u, int v, int width, int height, int uvWidth, int uvHeight) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(x),         (double)(y + height), (double)this.zLevel, (double)((float)(u) * f),           (double)((float)(v + uvHeight) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), (double)this.zLevel, (double)((float)(u + uvWidth) * f), (double)((float)(v + uvHeight) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y),          (double)this.zLevel, (double)((float)(u + uvWidth) * f), (double)((float)(v) * f1));
        tessellator.addVertexWithUV((double)(x),         (double)(y),          (double)this.zLevel, (double)((float)(u) * f),           (double)((float)(v) * f1));
        tessellator.draw();
    }
}
