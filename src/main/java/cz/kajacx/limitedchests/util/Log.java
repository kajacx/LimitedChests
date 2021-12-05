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

    public static final Marker MARKER_ARGS = MarkerManager.getMarker("INVALID_ARGUMENTS");
    public static final Marker MARKER_FIELDS = MarkerManager.getMarker("INVALID_FIELDS");

    private static final Marker MARKER_TRACE = MarkerManager.getMarker("METHOD_TRACE");
    private static final List<Log> TRACE_STACK = new ArrayList<>();
    private static final List<String> PADDING_STACK = new ArrayList<>();
    private static int traceLogIndex = 0;
    
    private String methodName;

    static {
        PADDING_STACK.add("");
    }

    private static Log open(String methodName) {
        while (TRACE_STACK.size() <= traceLogIndex) {
            TRACE_STACK.add(new Log());
            PADDING_STACK.add(PADDING_STACK.get(PADDING_STACK.size() - 1) + '#');
        }
        Log log = TRACE_STACK.get(traceLogIndex);

        log.methodName = methodName;
        ++traceLogIndex;
        return log;
    }

    public static Log enter(String methodName) {
        LOGGER.trace(MARKER_TRACE, "{}> Entering into {}",
            PADDING_STACK.get(traceLogIndex), methodName);
        return open(methodName);
    }

    public static Log enter(String methodName, Object arg0) {
        LOGGER.trace(MARKER_TRACE, "{}> Entering into {} with args: {}",
            PADDING_STACK.get(traceLogIndex), methodName, arg0);
        return open(methodName);
    }

    public static Log enter(String methodName, Object arg0, Object arg1) {
        LOGGER.trace(MARKER_TRACE, "{}> Entering into {} with args: {}, {}",
            PADDING_STACK.get(traceLogIndex), methodName, arg0, arg1);
        return open(methodName);
    }

    public static Log enter(String methodName, Object arg0, Object arg1, Object arg2) {
        LOGGER.trace(MARKER_TRACE, "{}> Entering into {} with args: {}, {}, {}",
            PADDING_STACK.get(traceLogIndex), methodName, arg0, arg1, arg2);
        return open(methodName);
    }

    public static Log enter(String methodName, Object arg0, Object arg1, Object arg2, Object arg3) {
        LOGGER.trace(MARKER_TRACE, "{}> Entering into {} with args: {}, {}, {}, {}",
            PADDING_STACK.get(traceLogIndex), methodName, arg0, arg1, arg2, arg3);
        return open(methodName);
    }

    public static Log enter(String methodName, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4) {
        LOGGER.trace(MARKER_TRACE, "{}> Entering into {} with args: {}, {}, {}, {}, {}",
            PADDING_STACK.get(traceLogIndex), methodName, arg0, arg1, arg2, arg3, arg4);
        return open(methodName);
    }

    @Override
    public void close() {
        --traceLogIndex;
        LOGGER.trace(MARKER_TRACE, "{}> Leaving {}", PADDING_STACK.get(traceLogIndex), methodName);
    }

}
