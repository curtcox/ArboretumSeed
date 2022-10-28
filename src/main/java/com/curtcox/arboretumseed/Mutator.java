
package com.curtcox.arboretumseed;

import java.util.*;

/**
 * For exhaustively creating graphs of mutable objects.
 */
public final class Mutator<T> {

    private final Mutations<T> mutations;

    Mutator(Mutations<T> mutations) {
        this.mutations = mutations;
    }

    public static <T> Mutator<T> of(T value, Copier<T> copier) {
        return new Mutator<>(Mutations.of(value,copier));
    }

    public <O> Mutator<T> with(Setter<T,O> method, O... values) {
        mutations.add(MutationChoices.fromValues(method,values));
        return this;
    }

    public <O> Mutator<T> with(Iterable<O> producer, Setter<T,O>... methods) {
        for (Setter<T,O> method : methods) {
            mutations.add(MutationChoices.fromProducer(method,producer));
        }
        return this;
    }

    public <O> Mutator<T> with(Setter<T,O> method, Iterable<O> producer) {
        mutations.add(MutationChoices.fromProducer(method,producer));
        return this;
    }

    Iterable<T> singles() {
        return mutations.values();
    }

    Iterable<List<T>> lists() {
        return () -> MutatedValuesListIterator.of(singles());
    }

}
