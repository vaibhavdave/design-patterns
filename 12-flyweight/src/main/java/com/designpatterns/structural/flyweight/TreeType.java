package com.designpatterns.structural.flyweight;

/**
 * The flyweight: immutable, shared, intrinsic state. Everything here is the same for every tree
 * that happens to be an "Oak/Green/Rough" tree, so it only ever needs to exist once no matter how
 * many trees of that type get planted.
 */
public final class TreeType {

    private final String name;
    private final String color;
    private final String texture;

    TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
        // Simulated expensive setup — loading a sprite/texture from disk, for example.
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getTexture() {
        return texture;
    }

    void draw(int x, int y) {
        System.out.printf("Drawing %s tree (color=%s, texture=%s) at (%d, %d)%n",
                name, color, texture, x, y);
    }
}
