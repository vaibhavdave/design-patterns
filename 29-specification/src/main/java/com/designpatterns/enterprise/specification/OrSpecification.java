package com.designpatterns.enterprise.specification;

public record OrSpecification<T>(Specification<T> left, Specification<T> right)
        implements Specification<T> {

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return left.isSatisfiedBy(candidate) || right.isSatisfiedBy(candidate);
    }
}
