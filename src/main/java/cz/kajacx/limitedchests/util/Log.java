
package cz.kajacx.limitedchests.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import cz.kajacx.limitedchests.LimitedChests;

public class Log implements AutoCloseable {

    public static final Logger LOGGER = LogManager.getLogger(LimitedChests.MODID);

    public static final Marker badArgsMarker = MarkerManager.getMarker("BAD_ARGUMENTS");
    public static final Marker badFieldsMarker = MarkerManager.getMarker("BAD_FIELDS");
    public static final Marker missingFieldMarker = MarkerManager.getMarker("MISSING_FIELD");
    public static final Marker exceptionMarker = MarkerManager.getMarker("EXCEPTION");


    private static final Marker methodTraceMarker = MarkerManager.getMarker("METHOD_TRACE");
    private static final List<Log> traceLogStack = new ArrayList<>();
    private static final List<String> paddingStack = new ArrayList<>();
    private static int traceLogIndex = 0;
    
    private String methodName;

    static {
        paddingStack.add("");
    }

    private static Log open(String methodName) {
        while (traceLogStack.size() <= traceLogIndex) {
            traceLogStack.add(new Log());
            paddingStack.add(paddingStack.get(paddingStack.size() - 1) + '#');
        }
        Log log = traceLogStack.get(traceLogIndex);

        log.methodName = methodName;
        ++traceLogIndex;
        return log;
    }

    public static Log enter(String methodName) {
        LOGGER.trace(methodTraceMarker, "{}> Entering into {}", paddingStack.get(traceLogIndex), methodName);
        return open(methodName);
    }

    public static Log enter(String methodName, Object arg0) {
        LOGGER.trace(methodTraceMarker, "{}> Entering into {} with args: {}", paddingStack.get(traceLogIndex), methodName, arg0);
        return open(methodName);
    }

    public static Log enter(String methodName, Object arg0, Object arg1) {
        LOGGER.trace(methodTraceMarker, "{}> Entering into {} with args: {}, {}", paddingStack.get(traceLogIndex), methodName, arg0, arg1);
        return open(methodName);
    }

    public static Log enter(String methodName, Object arg0, Object arg1, Object arg2) {
        LOGGER.trace(methodTraceMarker, "{}> Entering into {} with args: {}, {}, {}", paddingStack.get(traceLogIndex), methodName, arg0, arg1, arg2);
        return open(methodName);
    }

    @Override
    public void close() {
        --traceLogIndex;
        LOGGER.trace(methodTraceMarker, "{}> Leaving {}", paddingStack.get(traceLogIndex), methodName);
    }

}
