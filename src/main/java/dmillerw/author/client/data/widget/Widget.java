package dmillerw.author.client.data.widget;

import com.google.common.collect.Maps;
import dmillerw.author.client.data.Page;
import dmillerw.author.client.data.widget.impl.WidgetImage;
import dmillerw.author.client.data.widget.impl.WidgetItem;
import dmillerw.author.client.data.widget.impl.WidgetTextBox;
import dmillerw.author.client.data.widget.impl.WidgetTextLabel;
import dmillerw.author.client.gui.GuiBook;

import java.util.Map;

/**
 * @author dmillerw
 */
public class Widget {

    public static Widget construct(WidgetContainer widgetContainer, Page page) {
        Widget widget = null;
        try {
            widget = widgetClassMap.get(widgetContainer.type).newInstance();
        } catch (Exception ex) {

        }

        if (widget == null) {
            throw new IllegalStateException(widgetContainer.type + " isn't a valid widget type!");
        }

        Map<String, Object> data = Maps.newHashMap();

        // We attempt to fill a new map with all the data the widget will need
        // from both the data provided in the container, and the data provided
        // by the page variables

        // We scan through the container data and if there's a defined variable
        // we check the page variable mappings for the value
        for (Map.Entry<String, Object> entry : widgetContainer.data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof String && ((String)value).startsWith("#")) {
                String var = (String) value;
                Object val = page.variables.get(var.substring(1));

                if (val != null) {
                    data.put(key, val);
                }
            } else {
                data.put(key, value);
            }
        }

        // We then feed the completed data to the widget and return!
        widget.acceptData(data);
        return widget;
    }

    private static Map<String, Class<? extends Widget>> widgetClassMap = Maps.newHashMap();

    static {
        widgetClassMap.put("TEXT_BOX", WidgetTextBox.class);
        widgetClassMap.put("TEXT_LABEL", WidgetTextLabel.class);
        widgetClassMap.put("ITEM", WidgetItem.class);
        widgetClassMap.put("IMAGE", WidgetImage.class);
    }

    public void draw(GuiBook guiBook) {

    }

    public void acceptData(Map<String, Object> data) {

    }
}
