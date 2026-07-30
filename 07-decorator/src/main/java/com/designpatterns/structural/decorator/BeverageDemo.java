package com.designpatterns.structural.decorator;

public final class BeverageDemo {

    private BeverageDemo() {
    }

    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        print(espresso);

        Beverage mocha = new MochaDecorator(new Espresso());
        print(mocha);

        // Decorators stack at runtime, in whatever order the caller chooses.
        Beverage kitchenSink = new WhipDecorator(new MilkDecorator(new MochaDecorator(new HouseBlend())));
        print(kitchenSink);
    }

    private static void print(Beverage beverage) {
        System.out.printf("%s -> $%.2f%n", beverage.getDescription(), beverage.getCost());
    }
}
