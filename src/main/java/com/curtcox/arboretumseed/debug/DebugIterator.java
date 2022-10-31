package com.curtcox.arboretumseed.debug;

import java.util.*;

final class DebugIterator<E> implements Iterator<E> {

    final Iterator<E> source;
    final String prefix;

    private DebugIterator(String prefix, Iterator<E> inner) {
        this.prefix = prefix;
        this.source = inner;
    }

    static <E> DebugIterator<E> of(String prefix, Iterator<E> source) {
        return new DebugIterator<>(prefix,source);
    }

    @Override
    public boolean hasNext() {
        boolean more = source.hasNext();
        info("hasNext? " + more);
        return more;
    }

    @Override
    public E next() {
        E element = source.next();
        info("next : " + element);
        return element;
    }

    void info(String message) {
        //System.out.println(hashCode() + " " + prefix + " " + message);
    }

}
