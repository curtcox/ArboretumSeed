package com.curtcox.arboretumseed;

import java.util.*;

public class Lists {

    static <T> List<T> list(Iterable<T> values) {
        List<T> list = new ArrayList<>();
        for (T t : values) {
            list.add(t);
        }
        return list;
    }

}
