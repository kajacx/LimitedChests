
package cz.kajacx.limitedchests.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import cz.kajacx.limitedchests.LimitedChests;

public class Log {

    public static final Logger logger = LogManager.getLogger(LimitedChests.MODID);

    public static final Marker badArgsMarker = MarkerManager.getMarker("BAD_ARGUMENTS");

    public static final Marker badFieldsMarker = MarkerManager.getMarker("BAD_FIELDS");

}
