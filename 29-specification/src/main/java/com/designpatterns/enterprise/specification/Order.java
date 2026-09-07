package com.designpatterns.enterprise.specification;

public record Order(double amount, int itemCount, boolean isExpress) {

    public static final double HIGH_VALUE_THRESHOLD = 1_000.0;
    public static final int LARGE_ORDER_THRESHOLD = 20;
    public static final double EXPRESS_THRESHOLD = 500.0;
    public static final double DISCOUNT_MIN_VALUE = 200.0;
    public static final int DISCOUNT_MIN_ITEMS = 5;
}
