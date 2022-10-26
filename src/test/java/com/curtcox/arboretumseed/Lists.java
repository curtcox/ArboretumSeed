package com.curtcox.arboretumseed;

import java.util.*;

import static org.junit.Assert.assertTrue;

final class Lists {

    static <T> List<T> list(Iterable<T> values) {
        List<T> list = new ArrayList<>();
        for (T t : values) {
            list.add(t);
        }
        return list;
    }

    static <T> List<T> list(Iterator<T> values) {
        List<T> list = new ArrayList<>();
        while (values.hasNext()) {
            list.add(values.next());
        }
        return list;
    }

    static <T> void assertContains(List<T> list, T... items) {
        for (T item : items) {
            String message = item + " should be in " + list;
            assertTrue(message,list.contains(item));
        }
    }

}
