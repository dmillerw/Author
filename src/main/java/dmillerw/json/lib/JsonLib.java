package dmillerw.json.lib;

import com.google.gson.*;
import dmillerw.json.client.data.widget.WidgetContainer;

/**
 * @author dmillerw
 */
public class JsonLib {

    private static Gson gson;

    public static Gson gson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();

            // Deserializer
            gsonBuilder.registerTypeAdapter(WidgetContainer.class, new WidgetContainer.Deserializer());

            // Serializer

            gsonBuilder.setPrettyPrinting();

            gson = gsonBuilder.create();
        }
        return gson;
    }

    public static Object convert(JsonElement jsonElement) {
        if (jsonElement.isJsonObject()) {
            throw new RuntimeException("Cannot convert high-level JSON object. Use a derserializer you dummy!");
        }

        if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (jsonPrimitive.isBoolean()) {
                return jsonPrimitive.getAsBoolean();
            } else if (jsonPrimitive.isNumber()) {
                return jsonPrimitive.getAsDouble();
            } else if (jsonPrimitive.isString()) {
                return jsonPrimitive.getAsString();
            } else {
                return null;
            }
        } else if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            Object[] array = new Object[jsonArray.size()];

            for (int i=0; i<jsonArray.size(); i++) {
                array[i] = convert(jsonArray.get(i));
            }

            return array;
        } else {
            return null;
        }
    }
}
