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

    @Override
    public String toString() {
        return "->" + value;
    }

    @Override
    public boolean equals(Object o) {
        Mutation that = (Mutation) o;
        return method.equals(that.method) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return method.hashCode() ^ value.hashCode();
    }
}
