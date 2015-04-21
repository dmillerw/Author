package dmillerw.author.lib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author dmillerw
 */
public class Log {

    private static final Logger LOGGER = LogManager.getLogger("Author");

    private static final boolean SHOW_DEBUG = true;

    public static void info(String msg) {
        LOGGER.info(msg);
    }

    public static void warn(String msg) {
        LOGGER.warn(msg);
    }

    public static void debug(String msg) {
        if (SHOW_DEBUG) LOGGER.info(msg); else LOGGER.debug(msg);
    }
}
