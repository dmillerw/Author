package dmillerw.author.common.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import dmillerw.author.client.gui.GuiBook;
import dmillerw.author.common.item.ItemJSONBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author dmillerw
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new GuiBook(((ItemJSONBook)player.getHeldItem().getItem()).book);
    }
}
