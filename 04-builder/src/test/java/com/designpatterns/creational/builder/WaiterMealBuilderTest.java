package com.designpatterns.creational.builder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WaiterMealBuilderTest {

    private final Waiter waiter = new Waiter();

    @Test
    void directorProducesAVegMealWhenGivenTheVegBuilder() {
        Meal meal = waiter.construct(new VegMealBuilder());

        assertThat(meal.items()).containsExactly("Paneer Tikka", "Dal Makhani", "Lassi");
    }

    @Test
    void directorProducesANonVegMealWhenGivenTheNonVegBuilder() {
        Meal meal = waiter.construct(new NonVegMealBuilder());

        assertThat(meal.items()).containsExactly("Butter Chicken", "Naan", "Soda");
    }

    @Test
    void sameDirectorAlgorithmYieldsDifferentResultsForDifferentBuilders() {
        Meal veg = waiter.construct(new VegMealBuilder());
        Meal nonVeg = waiter.construct(new NonVegMealBuilder());

        assertThat(veg.items()).hasSameSizeAs(nonVeg.items());
        assertThat(veg.items()).doesNotContainAnyElementsOf(nonVeg.items());
    }
}
