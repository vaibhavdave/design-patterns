package com.designpatterns.structural.flyweight;

import java.util.ArrayList;
import java.util.List;

public class Forest {

    private final TreeFactory treeFactory;
    private final List<Tree> trees = new ArrayList<>();

    public Forest(TreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType type = treeFactory.getTreeType(name, color, texture);
        trees.add(new Tree(x, y, type));
    }

    public int getTreeCount() {
        return trees.size();
    }

    public List<Tree> getTrees() {
        return List.copyOf(trees);
    }

    public void draw() {
        for (Tree tree : trees) {
            tree.draw();
        }
    }
}
