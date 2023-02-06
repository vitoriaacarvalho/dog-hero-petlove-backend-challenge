package com.vitoria.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TimeStamp {
	@JsonProperty("A")
	A(30),
	@JsonProperty("B")
	B(60);
	
	private int numVal;
	
	@JsonCreator
    TimeStamp(int numVal) {
        this.numVal = numVal;
    }
	
	@JsonValue
    public int getNumVal() {
        return numVal;
    }
	
}
