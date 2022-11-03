
package com.curtcox.arboretumseed;

import java.util.*;

import static com.curtcox.arboretumseed.debug.Debug.debug;

/**
 * For exhaustively creating graphs of mutable objects.
 * This class is designed to support the fluent creation of IterableS of the mutated objects.
 */
public final class Mutator<T> {

    private final T value;
    private final Mutations<T> mutations;

    Mutator(T value,Mutations<T> mutations) {
        this.value = value;
        this.mutations = mutations;
    }

    /**
     * Create a mutator for a specific object.
     * The original object is not mutated.
     * The given copier is required so that values produced are different objects.
     */
    public static <T> Mutator<T> of(T value, Copier<T> copier) {
        return new Mutator<>(value,Mutations.of(value,copier));
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

    /**
     * Return mutated copies of the original value.
     * That is copies with different combinations of the given mutations (setter,value) applied.
     */
    public Iterable<T> singles() {
        return debug("singles",mutations.values());
    }

    /**
     * Return (possibly empty) lists of mutated copies of the original value.
     */
    public Iterable<List<T>> lists() {
        return () -> debug("MutatedValuesListIterator " + value,MutatedValuesListIterator.of(singles()));
    }

}
