package com.vitoria.enums;

public enum TimeStamp {
	
	A(30),
	B(60);
	
	private int numVal;

    TimeStamp(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
	
}
