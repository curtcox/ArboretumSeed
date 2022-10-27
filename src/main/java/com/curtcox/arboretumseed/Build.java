package com.curtcox.arboretumseed;

@FunctionalInterface
public interface Build<T,B> {
    T build(B builder);
}
