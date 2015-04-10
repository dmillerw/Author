package dmillerw.author.client.gui;

import com.google.common.collect.Maps;
import dmillerw.author.client.data.widget.impl.WidgetItem;
import dmillerw.author.client.data.widget.impl.WidgetTextBox;
import net.minecraft.client.gui.GuiScreen;

import java.util.Map;

/**
 * @author dmillerw
 */
public class GuiBook extends GuiScreen {

    private WidgetTextBox widgetTextBox;
    private WidgetItem widgetItem;

    @Override
    public void initGui() {
        super.initGui();

        Map<String, Object> data = Maps.newHashMap();
        data.put("x", 5);
        data.put("y", 5);
        data.put("w", 50);
        data.put("text", "Oh\nhi\nbabes. I really really really like cheese and toast and I smell toast am I having a stroke?");

        Map<String, Object> data1 = Maps.newHashMap();
        data1.put("x", 50);
        data1.put("y", 50);
        data1.put("item", "minecraft:stone");

        widgetTextBox = new WidgetTextBox();
        widgetTextBox.acceptData(data);

        widgetItem = new WidgetItem();
        widgetItem.acceptData(data1);
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

        widgetTextBox.draw(this);
        widgetItem.draw(this);
    }
}
