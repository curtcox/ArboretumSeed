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

    static <T> void assertContainsAll(List<T> list, Iterable<List<T>> sublists) {
        for (List<T> sublist : sublists) {
            String message = sublist + " should be in " + list;
            assertTrue(message,containsSublist(list,sublist));
        }
    }

    static boolean containsSublist(List list, List sublist) {
        int stop = list.size() - sublist.size();
        int size = sublist.size();
        for (int i=0; i<stop; i++) {
            if (sublist.equals(list.subList(i,i+size))) {
                return true;
            }
        }
        return false;
    }

    static List<Integer> _1_to_(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<size; i++) {
            list.add(i+1);
        }
        return list;
    }

}
