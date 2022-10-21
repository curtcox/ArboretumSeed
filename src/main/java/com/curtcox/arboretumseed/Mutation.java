package com.curtcox.arboretumseed;

/**
 * For making a single state change -- a method invocation with a value.
 */
final class Mutation<T,V> {

    final Setter<T,V> method;
    final V value;

    Mutation(Setter<T, V> method, V value) {
        this.method = method;
        this.value = value;
    }

    void mutate(T input) {
        method.set(input,value);
    }

}
