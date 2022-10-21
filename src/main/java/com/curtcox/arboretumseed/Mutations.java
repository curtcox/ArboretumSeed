package com.curtcox.arboretumseed;

import java.util.*;

final class Mutations<T> {

    private final T value;
    private final Copier<T> copier;

    private final List<MutationChoices<T,?>> mutations = new ArrayList<>();

    Mutations(T value, Copier<T> copier) {
        this.value = value;
        this.copier = copier;
    }

    void add(MutationChoices<T, ?> choices) {
        mutations.add(choices);
    }

    Iterable<T> values() {
        return () -> MutatedValuesIterator.of(value,copier,mutations);
    }

    // Null means unknown
    Long count() {
        long total = 1;
        for (MutationChoices choices : mutations) {
            Long count = choices.count();
            if (count==null) {
                return null;
            }
            total = total + count;
        }
        return total;
    }
}
