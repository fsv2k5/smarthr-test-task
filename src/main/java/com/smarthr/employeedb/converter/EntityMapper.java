package com.smarthr.employeedb.converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class EntityMapper<S, T> implements Function<S, T> {
    public T apply(S in) {
        return in == null
                ? null
                : doApply(in);
    }

    public abstract T doApply(S in);

    public List<T> apply(List<S> source) {
        return source != null
                ? source.stream()
                    .map(this)
                    .collect(Collectors.toList())
                : null;
    }
}
