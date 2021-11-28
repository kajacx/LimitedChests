
package cz.kajacx.limitedchests.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import cz.kajacx.limitedchests.LimitedChests;

public class Log {

    public static final Logger logger = LogManager.getLogger(LimitedChests.MODID);

    public static final Marker badArgsMarker = MarkerManager.getMarker("BAD_ARGUMENTS");
    public static final Marker badFieldsMarker = MarkerManager.getMarker("BAD_FIELDS");
    public static final Marker missingFieldMarker = MarkerManager.getMarker("MISSING_FIELD");
    public static final Marker exceptionMarker = MarkerManager.getMarker("EXCEPTION");

    private static final Marker methodTraceMarker = MarkerManager.getMarker("METHOD_TRACE");
    private static final List<TraceLog> traceLogStack = new ArrayList<>();
    private static int traceLogIndex = 0;

    private static TraceLog getTraceLog() {
        while (traceLogStack.size() <= traceLogIndex) {
            traceLogStack.add(new TraceLog());
        }
        return traceLogStack.get(traceLogIndex);
    }

    public static TraceLog enter(String methodName) {
        return getTraceLog().enter(methodName);
    }

    public static TraceLog enter(String methodName, Object arg0) {
        return getTraceLog().enter(methodName, arg0);
    }

    public static TraceLog enter(String methodName, Object arg0, Object arg1) {
        return getTraceLog().enter(methodName, arg0, arg1);
    }

    public static TraceLog enter(String methodName, Object arg0, Object arg1, Object arg2) {
        return getTraceLog().enter(methodName, arg0, arg1, arg2);
    }

    public static class TraceLog implements AutoCloseable {

        private String methodName;

        private TraceLog open(String methodName) {
            this.methodName = methodName;
            ++traceLogIndex;
            return this;
        }

        private TraceLog enter(String methodName) {
            logger.trace(methodTraceMarker, "Entering into {}", methodName);
            return open(methodName);
        }

        private TraceLog enter(String methodName, Object arg0) {
            logger.trace(methodTraceMarker, "Entering into {} with args: {}", methodName, arg0);
            return open(methodName);
        }

        private TraceLog enter(String methodName, Object arg0, Object arg1) {
            logger.trace(methodTraceMarker, "Entering into {} with args: {}, {}", methodName, arg0, arg1);
            return open(methodName);
        }

        private TraceLog enter(String methodName, Object arg0, Object arg1, Object arg2) {
            logger.trace(methodTraceMarker, "Entering into {} with args: {}, {}, {}", methodName, arg0, arg1, arg2);
            return open(methodName);
        }

        @Override
        public void close() {
            logger.trace(methodTraceMarker, "Leaving {}", methodName);
            --traceLogIndex;
        }
    }
}
