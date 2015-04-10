package dmillerw.author.common.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import dmillerw.author.Author;
import dmillerw.author.common.Loader;
import dmillerw.author.common.Registry;
import dmillerw.author.common.data.Book;
import dmillerw.author.common.handler.GuiHandler;
import dmillerw.author.common.item.ItemJSONBook;

/**
 * @author dmillerw
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        Loader.initialize(event.getModConfigurationDirectory());

        NetworkRegistry.INSTANCE.registerGuiHandler(Author.instance, new GuiHandler());

        for (Book book : Registry.getAllBooks()) {
            ItemJSONBook item = new ItemJSONBook(book);
            GameRegistry.registerItem(item, "book." + book.ident);
        }
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
