package dmillerw.json;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dmillerw.json.client.DummyResourcePack;
import dmillerw.json.common.Loader;
import dmillerw.json.common.Registry;
import dmillerw.json.common.data.Book;
import dmillerw.json.common.item.ItemJSONBook;

/**
 * @author dmillerw
 */
@Mod(modid = "JSONcumentation", name = "JSONcumentaion", version = "1.0.0")
public class JSONcumentation {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Loader.initialize(event.getModConfigurationDirectory());
        DummyResourcePack.inject();

        for (Book book : Registry.getAllBooks()) {
            ItemJSONBook item = new ItemJSONBook(book);
            GameRegistry.registerItem(item, "book." + book.ident);
        }
    }
}
