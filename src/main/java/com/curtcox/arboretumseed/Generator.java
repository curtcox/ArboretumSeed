package com.curtcox.arboretumseed;

import java.util.*;

public final class Generator<T,B> {

    public static <T,B> Generator<T,B> of(T value, B builder) {
        return new Generator<>();
    }

    public <O> Generator<T,B> with(Setter<B,O> method, O... values) {
        return this;
    }

    public <O> Generator<T,B> with(Iterable<O> producer, Setter<B,O>... methods) {
        return this;
    }

    public <O> Generator<T,B> with(Setter<B,O> method, Iterable<O> producer) {
        return this;
    }

    Iterable<T> singles() {
        return Collections.EMPTY_LIST;
    }

    Iterable<List<T>> lists() {
        return new ArrayList<>();
    }
}
