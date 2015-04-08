package dmillerw.json.client;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import dmillerw.json.common.Loader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FolderResourcePack;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * @author dmillerw
 */
public class DummyResourcePack extends FolderResourcePack {

    private static final String OBF = "field_110449_ao";
    private static final String DEOBF = "defaultResourcePacks";

    public static void inject() {
        try {
            Field field = Minecraft.class.getDeclaredField(OBF);
            field.setAccessible(true);
            ((List)field.get(Minecraft.getMinecraft())).add(new DummyResourcePack(Loader.resourceFolder));
            field.setAccessible(false);
        } catch (Exception ex) {
            try {
                Field field = Minecraft.class.getDeclaredField(DEOBF);
                field.setAccessible(true);
                ((List)field.get(Minecraft.getMinecraft())).add(new DummyResourcePack(Loader.resourceFolder));
                field.setAccessible(false);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public DummyResourcePack(File file) {
        super(file);
    }

    @Override
    protected boolean hasResourceName(String resourceName) {
        return super.hasResourceName(resourceName.replace("assets/json/", ""));
    }

    @Override
    protected InputStream getInputStreamByName(String resourceName) throws IOException {
        try {
            return super.getInputStreamByName(resourceName.replace("assets/json/", ""));
        } catch (IOException ex) {
            if ("pack.mcmeta".equals(resourceName)) {
                return new ByteArrayInputStream(("{\n" +
                        " \"pack\": {\n" +
                        "   \"description\": \"dummy resource pack\",\n" +
                        "   \"pack_format\": 1\n" +
                        "}\n" +
                        "}").getBytes(Charsets.UTF_8));
            } else {
                throw ex;
            }
        }
    }

    @Override
    public Set getResourceDomains() {
        return Sets.newHashSet("json");
    }
}
