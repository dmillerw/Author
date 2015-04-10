package dmillerw.author.lib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author dmillerw
 */
public class Log {

    private static final Logger LOGGER = LogManager.getLogger("Author");

    public static void info(String msg) {
        LOGGER.info(msg);
    }

    public static void warn(String msg) {
        LOGGER.warn(msg);
    }
}
