package dmillerw.author.common.data;

import com.google.gson.annotations.SerializedName;
import dmillerw.author.client.data.Page;

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

    public boolean indexed = false;

    public Page[] attachedPages = new Page[0];

    public Page getPage(int i) {
        return attachedPages[i];
    }
}
