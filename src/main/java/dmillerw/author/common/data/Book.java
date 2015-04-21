package dmillerw.author.common.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dmillerw.author.client.data.Entry;

import java.util.Collection;
import java.util.List;

/**
 * @author dmillerw
 */
public class Book {

    public String ident;
    public Resources resources;
    public Dimensions dimensions;

    @SideOnly(Side.CLIENT)
    public List<Entry> entries;

    @SideOnly(Side.CLIENT)
    public Collection<String> getCategories() {
        List<String> categories = Lists.newArrayList();
        for (int i=0; i<entries.size(); i++) {
            categories.add(entries.get(i).category);
        }
        return categories;
    }

    @SideOnly(Side.CLIENT)
    public Collection<Entry> getAllEntries() {
        return ImmutableList.copyOf(entries);
    }

    @SideOnly(Side.CLIENT)
    public Collection<Entry> getEntriesForCategory(String category) {
        ImmutableList.Builder<Entry> builder = ImmutableList.builder();
        for (int i=0; i<entries.size(); i++) {
            final Entry entry = entries.get(i);
            if (entry.category.equalsIgnoreCase(category)) {
                builder.add(entry);
            }
        }
        return builder.build();
    }

    // Technically client only, but loaded on both
    public static class Resources {
        @SerializedName("item_icon")
        public String itemIcon;
        @SerializedName("gui_background")
        public String guiBackground;
    }

    // Same with these
    public static class Dimensions {
        public int width;
        public int height;
        @SerializedName("margin_x")
        public int marginX;
        @SerializedName("margin_y")
        public int marginY;
    }
}
