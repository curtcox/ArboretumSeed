package com.curtcox.arboretumseed;

import java.util.*;

/**
 * For producing mutated copies of an original value.
 */
final class MutatedValuesIterator<T> implements Iterator<T> {

    private final Copier<T> copier;
    private final LinkedList<T> valuesToReturn = new LinkedList<>();
    private final StateTracker tracker;

    private MutatedValuesIterator(T initial, Copier<T> copier, StateTracker tracker) {
        this.copier = copier;
        this.tracker = tracker;
        valuesToReturn.add(initial);
    }

    static <T> MutatedValuesIterator<T> of(T initial, Copier<T> copier, List<MutationChoices<T,?>> mutations) {
        return new MutatedValuesIterator<>(initial,copier,StateTracker.of(mutations));
    }

    @Override
    public boolean hasNext() {
        return !valuesToReturn.isEmpty();
    }

    @Override public T next() {
        if (valuesToReturn.isEmpty()) throw new IllegalStateException();
        T value = valuesToReturn.removeFirst();
        if (valuesToReturn.isEmpty()) {
            generateNextValueToReturn(value);
        }
        return value;
    }

    private void generateNextValueToReturn(T value) {
        Mutation mutation = tracker.next();
        if (mutation!=null) {
            T copy = copier.copy(value);
            mutation.mutate(copy);
            valuesToReturn.add(copy);
        }
    }
}
