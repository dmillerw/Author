package dmillerw.author.lib;

import java.util.Map;

/**
 * @author dmillerw
 */
public class SafetyLib {

    public static int getAsInt(Map<String, Object> data, String name, int def) {
        Object object = data.get(name);
        if (object == null || !(object instanceof Number)) {
            return def;
        } else {
            return ((Number)object).intValue();
        }
    }

    public static boolean getAsBoolean(Map<String, Object> data, String name, boolean def) {
        Object object = data.get(name);
        if (object == null || !(object instanceof Boolean)) {
            return def;
        } else {
            return ((Boolean)object).booleanValue();
        }
    }

    public static String getAsString(Map<String, Object> data, String name, String def, boolean multiline) {
        Object object = data.get(name);
        if (object instanceof String[]) {
            String[] array = (String[])object;
            StringBuilder stringBuilder = new StringBuilder();

            for (int i=0; i<array.length; i++) {
                stringBuilder.append(array[i]);
                if (multiline)
                    stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        } else if (object instanceof String) {
            return object.toString();
        } else {
            return def;
        }
    }

    public static String[] getAsStringArray(Map<String, Object> data, String name, String def, boolean multiline) {
        Object object = data.get(name);
        if (object instanceof String[]) {
            String[] array = (String[])object;
            StringBuilder stringBuilder = new StringBuilder();

            for (int i=0; i<array.length; i++) {
                stringBuilder.append(array[i]);
                if (multiline)
                    stringBuilder.append("\n");
            }

            return stringBuilder.toString().split("\n");
        } else if (object instanceof String) {
            return new String[] {object.toString()};
        } else {
            return new String[] {def};
        }
    }
}
