package com.vitoria.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
	@JsonProperty("DONE")
	DONE,
	@JsonProperty("OPEN")
	OPEN,
	@JsonProperty("CLOSED")
	CLOSED,
	@JsonProperty("CANCELED")
	CANCELED;
		
}
