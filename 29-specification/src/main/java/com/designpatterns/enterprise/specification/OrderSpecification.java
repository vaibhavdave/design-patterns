package com.designpatterns.enterprise.specification;

/**
 * Named business rules for the Order domain, expressed as Specifications.
 */
public final class OrderSpecification {

    private OrderSpecification() {}

    public static Specification<Order> isRushOrder() {
        return order -> order.isExpress() && order.amount() > Order.EXPRESS_THRESHOLD;
    }

    public static Specification<Order> isHighValue() {
        return order -> order.amount() > Order.HIGH_VALUE_THRESHOLD;
    }

    public static Specification<Order> isLargeOrder() {
        return order -> order.itemCount() > Order.LARGE_ORDER_THRESHOLD;
    }

    /** Eligible if high-value OR (large-order AND worth at least $200). */
    public static Specification<Order> isEligibleForDiscount() {
        Specification<Order> largeEnough =
                (Order o) -> o.amount() >= Order.DISCOUNT_MIN_VALUE;
        return isHighValue().or(isLargeOrder().and(largeEnough));
    }

    /** Express but not rush (amount <= threshold). Demonstrates .not(). */
    public static Specification<Order> isStandardExpress() {
        return (Order o) -> o.isExpress() && isRushOrder().not().isSatisfiedBy(o);
    }
}
