package com.designpatterns.structural.flyweight;

import java.util.Random;

public final class ForestDemo {

    private static final String[] NAMES = {"Oak", "Pine", "Birch"};
    private static final String[] COLORS = {"Green", "DarkGreen"};
    private static final String[] TEXTURES = {"Rough", "Smooth"};
    private static final int TREE_COUNT = 10_000;

    private ForestDemo() {
    }

    public static void main(String[] args) {
        TreeFactory treeFactory = new TreeFactory();
        Forest forest = new Forest(treeFactory);
        Random random = new Random(42);

        for (int i = 0; i < TREE_COUNT; i++) {
            String name = NAMES[random.nextInt(NAMES.length)];
            String color = COLORS[random.nextInt(COLORS.length)];
            String texture = TEXTURES[random.nextInt(TEXTURES.length)];
            forest.plantTree(random.nextInt(1000), random.nextInt(1000), name, color, texture);
        }

        System.out.println("Trees planted: " + forest.getTreeCount());
        System.out.println("Distinct TreeType (flyweight) objects created: " + treeFactory.getCreatedTypeCount());
        System.out.printf("Max possible distinct combinations: %d%n",
                NAMES.length * COLORS.length * TEXTURES.length);
    }
}
