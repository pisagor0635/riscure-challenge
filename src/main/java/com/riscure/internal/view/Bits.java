package com.riscure.internal.view;

public enum Bits {

    BITS("Bits"),
    ZERO("0"),
    ONE("1");

    private String value;

    Bits(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
