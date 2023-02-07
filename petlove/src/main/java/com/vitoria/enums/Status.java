package com.vitoria.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
	@JsonProperty("OPEN")
	OPEN,
	@JsonProperty("CLOSED")
	CLOSED,
	@JsonProperty("IN_MOTION")
	IN_MOTION,
	@JsonProperty("CANCELED")
	CANCELED;	
}
