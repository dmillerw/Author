package dmillerw.author;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dmillerw.author.common.proxy.CommonProxy;

/**
 * @author dmillerw
 */
@Mod(modid = Author.ID, name = Author.NAME, version = Author.VERSION)
public class Author {

    public static final String ID = "Author";
    public static final String NAME = "Author";
    public static final String VERSION = "%MOD_VERSION$";

    @Mod.Instance(ID)
    public static Author instance;

    @SidedProxy(serverSide = "dmillerw.author.common.proxy.CommonProxy", clientSide = "dmillerw.author.common.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
