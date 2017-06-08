package com.netflix.yoda;

@FunctionalInterface
public interface CheckedFunction<T, R, E extends Exception>
{
	R apply(T t) throws E;
}