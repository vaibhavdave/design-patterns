package com.designpatterns.enterprise.specification;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpecificationTest {

    private static final Order HIGH_VALUE = new Order(1500.0, 10, false);
    private static final Order LARGE = new Order(300.0, 25, false);
    private static final Order BIG_AND_CHEAP = new Order(50.0, 30, false);
    private static final Order RUSH = new Order(600.0, 3, true);
    private static final Order SMALL = new Order(30.0, 1, false);

    @Nested
    class NamedSpecifications {
        @Test void isHighValue() {
            assertThat(OrderSpecification.isHighValue().isSatisfiedBy(HIGH_VALUE)).isTrue();
            assertThat(OrderSpecification.isHighValue().isSatisfiedBy(LARGE)).isFalse();
        }
        @Test void isLargeOrder() {
            assertThat(OrderSpecification.isLargeOrder().isSatisfiedBy(LARGE)).isTrue();
            assertThat(OrderSpecification.isLargeOrder().isSatisfiedBy(SMALL)).isFalse();
        }
        @Test void isRushOrder() {
            assertThat(OrderSpecification.isRushOrder().isSatisfiedBy(RUSH)).isTrue();
            assertThat(OrderSpecification.isRushOrder()
                    .isSatisfiedBy(new Order(30.0, 1, true))).isFalse();
        }
    }

    @Nested
    class Composition {
        @Test void and_BothTrue() {
            var spec = OrderSpecification.isHighValue().and(o -> o.itemCount() > 5);
            assertThat(spec.isSatisfiedBy(HIGH_VALUE)).isTrue();
        }
        @Test void and_LeftFalse() {
            var spec = OrderSpecification.isHighValue().and(o -> o.itemCount() > 5);
            assertThat(spec.isSatisfiedBy(LARGE)).isFalse();
        }
        @Test void or_EitherTrue() {
            var spec = OrderSpecification.isHighValue().or(OrderSpecification.isLargeOrder());
            assertThat(spec.isSatisfiedBy(HIGH_VALUE)).isTrue();
            assertThat(spec.isSatisfiedBy(LARGE)).isTrue();
        }
        @Test void or_BothFalse() {
            var spec = OrderSpecification.isHighValue().or(OrderSpecification.isLargeOrder());
            assertThat(spec.isSatisfiedBy(SMALL)).isFalse();
        }
        @Test void not_Inverts() {
            var notHighValue = OrderSpecification.isHighValue().not();
            assertThat(notHighValue.isSatisfiedBy(HIGH_VALUE)).isFalse();
            assertThat(notHighValue.isSatisfiedBy(SMALL)).isTrue();
        }
    }

    @Nested
    class EligibilityForDiscount {
        @Test void highValueIsEligible() {
            assertThat(OrderSpecification.isEligibleForDiscount()
                    .isSatisfiedBy(HIGH_VALUE)).isTrue();
        }
        @Test void largeAndWorthEnoughIsEligible() {
            assertThat(OrderSpecification.isEligibleForDiscount()
                    .isSatisfiedBy(LARGE)).isTrue();
        }
        @Test void largeButCheapIsNotEligible() {
            assertThat(OrderSpecification.isEligibleForDiscount()
                    .isSatisfiedBy(BIG_AND_CHEAP)).isFalse();
        }
        @Test void smallOrderIsNotEligible() {
            assertThat(OrderSpecification.isEligibleForDiscount()
                    .isSatisfiedBy(SMALL)).isFalse();
        }
    }

    @Nested
    class StandardExpress {
        @Test void rushOrderIsNotStandardExpress() {
            assertThat(OrderSpecification.isStandardExpress()
                    .isSatisfiedBy(RUSH)).isFalse();
        }
        @Test void smallExpressIsStandardExpress() {
            assertThat(OrderSpecification.isStandardExpress()
                    .isSatisfiedBy(new Order(30.0, 1, true))).isTrue();
        }
        @Test void nonExpressIsNotStandardExpress() {
            assertThat(OrderSpecification.isStandardExpress()
                    .isSatisfiedBy(SMALL)).isFalse();
        }
    }

    @Nested
    class CombinatorRecords {
        @Test void andSpecIsRecord() {
            var spec = new AndSpecification<>(OrderSpecification.isHighValue(), OrderSpecification.isLargeOrder());
            assertThat(spec.left()).isSameAs(OrderSpecification.isHighValue());
        }
        @Test void orSpecIsRecord() {
            var spec = new OrSpecification<>(OrderSpecification.isHighValue(), OrderSpecification.isLargeOrder());
            assertThat(spec.right()).isSameAs(OrderSpecification.isLargeOrder());
        }
        @Test void notSpecIsRecord() {
            var spec = new NotSpecification<>(OrderSpecification.isHighValue());
            assertThat(spec.wrapped()).isSameAs(OrderSpecification.isHighValue());
        }
    }
}
