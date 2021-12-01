package com.riscure.internal.view;

public enum SelectionMode {

    SELECTION_MODE("Selection mode"),
    BITS("Bits"),
    PIECES("Pieces"),
    BITS_PIECES("Bits & Pieces");

    private String value;

    SelectionMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
