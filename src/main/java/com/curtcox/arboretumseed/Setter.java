package com.curtcox.arboretumseed;

/**
 * For setting an instance of a particular type to a given value.
 */
@FunctionalInterface
public interface Setter<T, V> {

    void set(T type, V value);
}
