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
    public String name = "";
    @SerializedName("lore")
    public String[] loreText = new String[0];
    public String icon = "";

    private List<Page> associatedPages = Lists.newArrayList();

    public void attachPage(Page page) {
        associatedPages.add(page);
    }
}
