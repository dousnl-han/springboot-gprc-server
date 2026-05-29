package com.dousnl.function;

@FunctionalInterface
public interface GenericFunction<T, R> {

    R apply(T t);
}
