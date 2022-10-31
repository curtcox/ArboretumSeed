package com.curtcox.arboretumseed.debug;

import java.util.*;

public final class Debug {

    public static <E> Iterator<E> debug(String prefix, Iterator<E> source) {
        return DebugIterator.of(prefix,source);
    }

    public static <E> Iterable<E> debug(String prefix, Iterable<E> source) {
        return () -> DebugIterator.of(prefix,source.iterator());
    }

}