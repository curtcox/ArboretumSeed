package com.curtcox.arboretumseed.debug;

import java.util.*;

/**
 * For wrapping IterableS and IteratorS to debug them.
 */
public final class Debug {

    public static <E> Iterator<E> debug(String prefix, Iterator<E> source) {
        return DebugIterator.of(prefix,source);
    }

    public static <E> Iterable<E> debug(String prefix, Iterable<E> source) {
        return () -> DebugIterator.of(prefix,source.iterator());
    }

    public static <E> Iterator<E> debug(Iterator<E> source) {
        return DebugIterator.of(caller(),source);
    }

    public static <E> Iterable<E> debug(Iterable<E> source) {
        return () -> DebugIterator.of(caller(),source.iterator());
    }

    static String caller() {
        boolean foundUs = false;
        for (StackTraceElement e : Thread.currentThread().getStackTrace()) {
            if (thisClass(e)) foundUs = true;
            if (foundUs && !thisClass(e)) {
                return where(e);
            }
        }
        return "?";
    }

    private static String where(StackTraceElement e) {
        String[] parts = e.getClassName().split("\\.");
        return parts[parts.length-1] + " at " + e.getLineNumber();
    }
    private static boolean thisClass(StackTraceElement e) {
        return e.getClassName().contains("Debug");
    }

}