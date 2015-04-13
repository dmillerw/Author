package dmillerw.author.common.item;

import dmillerw.author.Author;
import dmillerw.author.common.data.Book;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

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
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (world.isRemote) {
            entityPlayer.openGui(Author.instance, 0, world, 0, 0, 0);
        }
        return itemStack;
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return icon;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        icon = (book.itemIcon == null || book.itemIcon.isEmpty()) ? Items.book.getIconFromDamage(0) : iconRegister.registerIcon("author:" + book.itemIcon);
    }
}
