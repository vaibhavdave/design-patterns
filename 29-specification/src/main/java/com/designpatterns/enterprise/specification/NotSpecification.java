package com.designpatterns.enterprise.specification;

public record NotSpecification<T>(Specification<T> wrapped)
        implements Specification<T> {

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return !wrapped.isSatisfiedBy(candidate);
    }
}
