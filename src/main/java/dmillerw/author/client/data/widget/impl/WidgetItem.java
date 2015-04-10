package dmillerw.author.client.data.widget.impl;

import cpw.mods.fml.common.registry.GameData;
import dmillerw.author.client.data.widget.Widget;
import dmillerw.author.client.gui.GuiBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

import java.util.Map;

import static dmillerw.author.lib.SafetyLib.getAsInt;
import static dmillerw.author.lib.SafetyLib.getAsString;

/**
 * @author dmillerw
 */
public class WidgetItem extends Widget {

    private static RenderBlocks renderBlocks;
    private static RenderItem renderItem;

    public static void renderItem(float x, float y, float z, ItemStack stack) {
        GL11.glPushMatrix();

        RenderHelper.enableGUIStandardItemLighting();

        GL11.glDisable(2896);
        GL11.glEnable(32826);
        GL11.glEnable(2903);
        GL11.glEnable(2896);

        x -= 8;
        y -= 8;

        if (renderBlocks == null || renderBlocks.blockAccess != Minecraft.getMinecraft().theWorld) {
            renderBlocks = new RenderBlocks(Minecraft.getMinecraft().theWorld);
        }

        if (renderItem == null) {
            renderItem = new RenderItem();
        }

        if (!ForgeHooksClient.renderInventoryItem(renderBlocks, Minecraft.getMinecraft().getTextureManager(), stack, true, z, x, y)) {
            renderItem.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), stack, (int) x, (int) y);
        }

        GL11.glColor4f(1, 1, 1, 1);

        GL11.glDisable(2896);

        GL11.glPopMatrix();
    }

    public int x;
    public int y;
    public int damage;

    public String item;

    private ItemStack itemStack;

    @Override
    public void draw(GuiBook guiBook) {
        if (itemStack == null) {
            itemStack = new ItemStack(GameData.getItemRegistry().getObject(item), 1, damage);
        }
        renderItem(x, y, 0, itemStack);
    }

    @Override
    public void acceptData(Map<String, Object> data) {
        this.x = getAsInt(data, "x", 0);
        this.y = getAsInt(data, "y", 0);
        this.damage = getAsInt(data, "damage", 0);
        this.item = getAsString(data, "item", "", false);
    }
}
