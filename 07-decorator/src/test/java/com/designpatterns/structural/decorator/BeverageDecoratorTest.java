package com.designpatterns.structural.decorator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class BeverageDecoratorTest {

    @Test
    void baseComponentReportsItsOwnCostAndDescription() {
        Beverage espresso = new Espresso();

        assertThat(espresso.getDescription()).isEqualTo("Espresso");
        assertThat(espresso.getCost()).isCloseTo(1.99, within(0.001));
    }

    @Test
    void singleDecoratorAddsToCostAndAppendsToDescription() {
        Beverage mochaEspresso = new MochaDecorator(new Espresso());

        assertThat(mochaEspresso.getDescription()).isEqualTo("Espresso, Mocha");
        assertThat(mochaEspresso.getCost()).isCloseTo(1.99 + 0.45, within(0.001));
    }

    @Test
    void decoratorsStackInTheOrderTheyAreAppliedAtRuntime() {
        Beverage drink = new WhipDecorator(new MilkDecorator(new Espresso()));

        assertThat(drink.getDescription()).isEqualTo("Espresso, Milk, Whip");
        assertThat(drink.getCost()).isCloseTo(1.99 + 0.35 + 0.50, within(0.001));
    }

    @Test
    void sameDecoratorsInADifferentOrderProduceADifferentDescriptionButSameCost() {
        Beverage a = new WhipDecorator(new MilkDecorator(new Espresso()));
        Beverage b = new MilkDecorator(new WhipDecorator(new Espresso()));

        assertThat(a.getDescription()).isNotEqualTo(b.getDescription());
        assertThat(a.getCost()).isCloseTo(b.getCost(), within(0.001));
    }

    @Test
    void multipleBaseComponentsWithTheSameDecoratorStackProduceIndependentTotals() {
        Beverage decoratedEspresso = new MochaDecorator(new MilkDecorator(new Espresso()));
        Beverage decoratedHouseBlend = new MochaDecorator(new MilkDecorator(new HouseBlend()));

        assertThat(decoratedEspresso.getCost()).isNotCloseTo(decoratedHouseBlend.getCost(), within(0.001));
    }
}
