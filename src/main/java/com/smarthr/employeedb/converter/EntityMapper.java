package com.smarthr.employeedb.converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class EntityMapper<S, T> implements Function<S, T> {
    public T apply(S source) {
        return source == null
                ? null
                : doApply(source);
    }

    public abstract T doApply(S source);

    public List<T> apply(List<S> source) {
        return source != null
                ? source.stream()
                    .map(this)
                    .collect(Collectors.toList())
                : null;
    }
}
