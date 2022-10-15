
package com.curtcox.arboretumseed;

import java.util.*;
import java.util.function.BiConsumer;

final class Mutator<T> {

    static class Mutation<T,O> {
        final BiConsumer<T,O> method;
        final O[] values;
        final Iterable<O> producer;

        Mutation(BiConsumer<T, O> method, O[] values, Iterable<O> producer) {
            this.method = method;
            this.values = values;
            this.producer = producer;
        }
    }

    final T t;
    final List<Mutation> mutations = new ArrayList<>();

    Mutator(T t) {
        this.t = t;
    }

    public static <T> Mutator<T> of(T t) {
        return new Mutator<>(t);
    }

    public <O> Mutator<T> with(BiConsumer<T,O> method, O... values) {
        mutations.add(new Mutation<>(method,values,null));
        return this;
    }

    public <O> Mutator<T> with(Iterable<O> producer, BiConsumer<T,O>... methods) {
        for (BiConsumer<T,O> method : methods) {
            mutations.add(new Mutation<>(method,null, producer));
        }
        return this;
    }

    public <O> Mutator<T> with(BiConsumer<T,O> method, Iterable<O> producer) {
        mutations.add(new Mutation<>(method,null, producer));
        return this;
    }

    Iterable<T> singles() {
        return () -> new Iterator<T>() {
            @Override public boolean hasNext() { return true; }
            @Override public T next() { return t; }
        };
    }

    Iterable<List<T>> lists() {
        return new ArrayList<>();
    }

}
