package com.riscure.internal.view;

public enum Pieces {

    PIECES("Pieces"),
    PIE("Pie"),
    CES("ces");

    private String value;

    Pieces(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
