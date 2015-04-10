package dmillerw.author.common.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dmillerw.author.client.DummyResourcePack;
import dmillerw.author.client.gui.SmallFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

/**
 * @author dmillerw
 */
public class ClientProxy extends CommonProxy {

    public static SmallFontRenderer smallFontRenderer;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        DummyResourcePack.inject();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

        final Minecraft minecraft = Minecraft.getMinecraft();
        ClientProxy.smallFontRenderer = new SmallFontRenderer(minecraft.gameSettings, new ResourceLocation("minecraft:textures/font/ascii.png"), minecraft.renderEngine, false);
    }
}
