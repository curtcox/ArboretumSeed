package com.curtcox.arboretumseed;

import java.util.*;

import static com.curtcox.arboretumseed.debug.Debug.debug;

final class Mutations<T> {

    private final T value;
    private final Copier<T> copier;

    private final List<MutationChoices<T,?>> mutations = new ArrayList<>();

    private Mutations(T value, Copier<T> copier) {
        this.value = value;
        this.copier = copier;
    }

    static <T> Mutations<T> of(T value, Copier<T> copier) {
        return new Mutations(value,copier);
    }

    void add(MutationChoices<T, ?> choices) {
        mutations.add(choices);
    }

    Iterable<T> values() {
        return () -> debug("Mutations " + value, MutatedValuesIterator.of(value,copier,mutations));
    }

    // Null means unknown
    Long count() {
        long total = 1;
        for (MutationChoices choices : mutations) {
            Long count = choices.count();
            if (count==null) {
                return null;
            }
            total = total * count;
        }
        total = total + mutations.size();
        return total;
    }
}
