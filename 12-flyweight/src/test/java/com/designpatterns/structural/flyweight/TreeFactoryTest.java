package com.designpatterns.structural.flyweight;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TreeFactoryTest {

    @Test
    void requestingTheSameCombinationTwiceReturnsTheSameTreeTypeInstance() {
        TreeFactory factory = new TreeFactory();

        TreeType first = factory.getTreeType("Oak", "Green", "Rough");
        TreeType second = factory.getTreeType("Oak", "Green", "Rough");

        assertThat(second).isSameAs(first);
    }

    @Test
    void requestingADifferentCombinationReturnsADifferentTreeTypeInstance() {
        TreeFactory factory = new TreeFactory();

        TreeType oak = factory.getTreeType("Oak", "Green", "Rough");
        TreeType pine = factory.getTreeType("Pine", "Green", "Rough");

        assertThat(pine).isNotSameAs(oak);
    }

    @Test
    void changingAnySingleAttributeCountsAsADifferentCombination() {
        TreeFactory factory = new TreeFactory();

        TreeType greenOak = factory.getTreeType("Oak", "Green", "Rough");
        TreeType darkGreenOak = factory.getTreeType("Oak", "DarkGreen", "Rough");
        TreeType smoothOak = factory.getTreeType("Oak", "Green", "Smooth");

        assertThat(darkGreenOak).isNotSameAs(greenOak);
        assertThat(smoothOak).isNotSameAs(greenOak);
    }

    @Test
    void factoryCreatesOnlyAsManyTreeTypesAsThereAreDistinctCombinations() {
        TreeFactory factory = new TreeFactory();
        Forest forest = new Forest(factory);

        for (int i = 0; i < 1000; i++) {
            String name = i % 2 == 0 ? "Oak" : "Pine";
            forest.plantTree(i, i, name, "Green", "Rough");
        }

        assertThat(forest.getTreeCount()).isEqualTo(1000);
        assertThat(factory.getCreatedTypeCount()).isEqualTo(2);
    }

    @Test
    void eachPlantedTreeKeepsItsOwnExtrinsicPositionWhileSharingTheFlyweight() {
        TreeFactory factory = new TreeFactory();
        Forest forest = new Forest(factory);

        forest.plantTree(1, 2, "Oak", "Green", "Rough");
        forest.plantTree(3, 4, "Oak", "Green", "Rough");

        Tree first = forest.getTrees().get(0);
        Tree second = forest.getTrees().get(1);

        assertThat(first.getType()).isSameAs(second.getType());
        assertThat(first.getX()).isNotEqualTo(second.getX());
        assertThat(first.getY()).isNotEqualTo(second.getY());
    }
}
