
package com.curtcox.arboretumseed;

import java.util.*;
import java.util.function.BiConsumer;

import static java.util.Collections.*;

/**
 * For exhaustively creating graphs of mutable objects.
 */
public final class Mutator<T> {

    private static class Mutation<T,O> {
        final BiConsumer<T,O> method;
        final O[] values;
        final Iterable<O> producer;
        final Copier<T> copier;

        Mutation(BiConsumer<T, O> method, O[] values, Iterable<O> producer,Copier<T> copier) {
            this.method = method;
            this.values = values;
            this.producer = producer;
            this.copier = copier;
        }

        List<T> produce(T input) {
            List copies = new ArrayList<>();
            if (values!=null) for (O value : values) copies.add(mutated(input,value));
            for (O value : producer) copies.add(mutated(input,value));
            return copies;
        }

        T mutated(T input,O value) {
            T copy = copier.copy(input);
            method.accept(copy,value);
            return copy;
        }
    }

    private final T value;
    private final Copier<T> copier;
    private final List<Mutation> mutations = new ArrayList<>();

    Mutator(T t, Copier<T> copier) {
        this.value = t;
        this.copier = copier;
    }

    public static <T> Mutator<T> of(T t, Copier<T> copier) {
        return new Mutator<>(t,copier);
    }

    public <O> Mutator<T> with(BiConsumer<T,O> method, O... values) {
        mutations.add(new Mutation<>(method,values,EMPTY_LIST,copier));
        return this;
    }

    public <O> Mutator<T> with(Iterable<O> producer, BiConsumer<T,O>... methods) {
        for (BiConsumer<T,O> method : methods) {
            mutations.add(new Mutation<>(method,null,producer,copier));
        }
        return this;
    }

    public <O> Mutator<T> with(BiConsumer<T,O> method, Iterable<O> producer) {
        mutations.add(new Mutation<>(method,null,producer,copier));
        return this;
    }

    Iterable<T> singles() {
        LinkedList<T> values = new LinkedList<>();
        LinkedList<Mutation> more = new LinkedList<>(mutations);
        values.add(value);
        return () -> new Iterator<T>() {
            @Override public boolean hasNext() { return values.isEmpty(); }
            @Override public T next() {
                if (values.isEmpty()) throw new IllegalStateException();
                T value = values.removeFirst();
                while (values.isEmpty() && !more.isEmpty()) {
                    values.addAll(more.removeFirst().produce(value));
                }
                return value;
            }
        };
    }

    Iterable<List<T>> lists() {
        return () -> new Iterator<List<T>>() {
            @Override public boolean hasNext() { return true; }
            @Override public List<T> next() { return new ArrayList<>(); }
        };
    }

}
