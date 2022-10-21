package com.curtcox.arboretumseed;

import java.util.*;

/**
 * For producing mutated copies of an original value.
 */
final class MutatedValuesIterator<T> implements Iterator<T> {
    private final LinkedList<T> values = new LinkedList<>();
    private final List<Iterator<Mutation>> iterators;
    private T current;
    private final Copier<T> copier;
    private int index = 0;

    private MutatedValuesIterator(T initial, Copier<T> copier, List<Iterator<Mutation>> iterators) {
        this.iterators = iterators;
        this.current = initial;
        this.copier = copier;
        values.add(initial);
    }

    static <T> MutatedValuesIterator<T> of(T initial, Copier<T> copier, List<MutationChoices<T,?>> mutations) {
        List<Iterator<Mutation>> iterators = new ArrayList<>();
        for (MutationChoices choices : mutations) {
            iterators.add(choices.mutations().iterator());
        }
        return new MutatedValuesIterator<>(initial,copier,iterators);
    }

    @Override public boolean hasNext() { return !values.isEmpty(); }
    void addMoreValues() {
        if (index>=iterators.size()) {
            index = 0;
        }
        Iterator<Mutation> iterator = iterators.get(index);
        if (iterator.hasNext()) {
            Mutation mutation = iterator.next();
            index++;
            current = copier.copy(current);
            mutation.mutate(current);
            values.add(current);
        } else {
            iterators.remove(iterator);
        }
    }
    boolean thereAreMoreValuesToAdd() {
        return !iterators.isEmpty();
    }

    @Override public T next() {
        if (values.isEmpty()) throw new IllegalStateException();
        T value = values.removeFirst();
        while (values.isEmpty() && thereAreMoreValuesToAdd()) {
            addMoreValues();
        }
        return value;
    }
}
