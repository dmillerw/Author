package dmillerw.author.common.data;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import dmillerw.author.client.data.Page;

import java.util.List;

/**
 * @author dmillerw
 */
public class Book {

    public String ident;
    @SerializedName("background_image")
    public String backgroundImage;
    @SerializedName("item_icon")
    public String itemIcon = "";
    public String[] pages;

    @SerializedName("gui_width")
    public int guiWidth;
    @SerializedName("gui_height")
    public int guiHeight;

    public List<Page> attachedPages = Lists.newArrayList();
}
