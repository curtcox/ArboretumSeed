package com.curtcox.arboretumseed;

import java.util.*;

/**
 * For exhaustively creating graphs of immutable objects -- or perhaps mutable ones that you want
 * to make with a builder. Builders are cool.
 */
public final class Generator<T,B> {

    private final Mutations<B> mutations;
    private final Build<T,B> build;

    Generator(Mutations<B> mutations,Build<T,B> build) {
        this.mutations = mutations;
        this.build = build;
    }

    public static <T,B> Generator<T,B> of(B builder,Build<T,B> build) {
        return new Generator<>(Mutations.of(builder,(x)->x),build);
    }

    public <O> Generator<T,B> with(Setter<B,O> method, O... values) {
        mutations.add(MutationChoices.fromValues(method,values));
        return this;
    }

    public <O> Generator<T,B> with(Iterable<O> producer, Setter<B,O>... methods) {
        for (Setter<B,O> method : methods) {
            mutations.add(MutationChoices.fromProducer(method,producer));
        }
        return this;
    }

    public <O> Generator<T,B> with(Setter<B,O> method, Iterable<O> producer) {
        mutations.add(MutationChoices.fromProducer(method,producer));
        return this;
    }

    /**
     * Return values generated using the specified builder.
     */
    public Iterable<T> singles() {
        Iterator<B> builders = mutations.values().iterator();
        return () -> new Iterator<T>() {
            @Override public boolean hasNext() {
                return builders.hasNext();
            }

            @Override
            public T next() {
                return build.build(builders.next());
            }
        };
    }

    /**
     * Return (possibly empty) lists produced with the specified builder.
     */
    public Iterable<List<T>> lists() {
        return () -> MutatedValuesListIterator.of(singles());
    }
}
