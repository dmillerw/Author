package dmillerw.author.common.item;

import dmillerw.author.common.data.Book;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.Collections;
import java.util.List;

/**
 * @author dmillerw
 */
public class ItemJSONBook extends Item {

    private IIcon icon;
    private Book book;

    public ItemJSONBook(Book book) {
        super();

        this.book = book;

        setMaxStackSize(1);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMisc);
        setUnlocalizedName("book." + book.ident);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean debug) {
        Collections.addAll(list, book.loreText);
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return icon;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        icon = (book.icon == null || book.icon.isEmpty()) ? Items.book.getIconFromDamage(0) : iconRegister.registerIcon("JSON:" + book.icon);
    }
}
