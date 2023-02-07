package com.vitoria.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	String resourceName;
	String fieldName;
	

	public ResourceNotFoundException() {
	}
	public ResourceNotFoundException(String resourceName, String fieldName) {
		super(String.format("&s not found with %s : %s", resourceName, fieldName));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}