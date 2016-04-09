package com.thales.model;

public enum Destination {

    AU07(180), AU17(170), AU21(88), AU04(74), AU03(71), AU18(66), AU05(63), KBSB(0);

    private final int value;

    private Destination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
