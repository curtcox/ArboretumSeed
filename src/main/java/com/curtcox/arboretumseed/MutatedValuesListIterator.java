package com.curtcox.arboretumseed;

import java.util.*;

import static com.curtcox.arboretumseed.debug.Debug.debug;
import static java.util.Collections.*;

final class MutatedValuesListIterator<T> implements Iterator<List<T>> {

    final Iterator<T> singles;
    private final LinkedList<List<T>> values = new LinkedList<>();

    MutatedValuesListIterator(Iterable<T> singles) {
        this.singles = debug("MutatedValuesListIterator:singles",singles.iterator());
        values.add(new ArrayList<>());
    }

    public static <T> Iterator<List<T>> of(Iterable<T> singles) {
        return new MutatedValuesListIterator<>(singles);
    }

    @Override public boolean hasNext() {
        return !values.isEmpty();
    }
    void addMoreValues() {
        if (singles.hasNext()) {
            T single = singles.next();
            values.add(singletonList(single));
        }
    }
    boolean thereAreMoreValuesToAdd() {
        return singles.hasNext();
    }

    @Override public List<T> next() {
        if (values.isEmpty()) throw new IllegalStateException();
        List<T> value = values.removeFirst();
        while (values.isEmpty() && thereAreMoreValuesToAdd()) {
            addMoreValues();
        }
        return value;
    }

}
