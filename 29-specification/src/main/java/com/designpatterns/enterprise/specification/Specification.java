package com.designpatterns.enterprise.specification;

/**
 * Encapsulates a business rule that can be evaluated against a candidate object
 * and combined with other rules using boolean logic.
 *
 * @param <T> the type of object this specification evaluates
 */
public interface Specification<T> {

    /** Check whether the given candidate satisfies this specification. */
    boolean isSatisfiedBy(T candidate);

    /** Create a combined spec satisfied only if BOTH this AND the other are satisfied. */
    default Specification<T> and(Specification<T> other) {
        return new AndSpecification<>(this, other);
    }

    /** Create a combined spec satisfied if EITHER this OR the other is satisfied. */
    default Specification<T> or(Specification<T> other) {
        return new OrSpecification<>(this, other);
    }

    /** Create a spec that negates this one. */
    default Specification<T> not() {
        return new NotSpecification<>(this);
    }
}
