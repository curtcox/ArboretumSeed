
package com.curtcox.arboretumseed;

import java.util.*;

import static java.util.Collections.*;

/**
 * For exhaustively creating graphs of mutable objects.
 */
public final class Mutator<T> {

    private final Mutations mutations;

    Mutator(Mutations mutations) {
        this.mutations = mutations;
    }

    public static <T> Mutator<T> of(T value, Copier<T> copier) {
        return new Mutator<>(new Mutations(value,copier));
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
        List<List<T>> all = new ArrayList<>();
        all.add(EMPTY_LIST);
        T single = singles().iterator().next();
        all.add(singletonList(single));
        return all;
    }

}
