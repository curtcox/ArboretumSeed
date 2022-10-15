package com.curtcox.arboretumseed;

/**
 * For making copies of given objects.
 */
public interface Copier<T> {
    T copy(T t);
}

