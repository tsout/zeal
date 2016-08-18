package org.gem.patterns.dto;

public class InvalidDtoWithInterface implements InvalidDtoInterface {

	String field = "test";

	@Override
	public void setSomeField(String field) {
		this.field = field;
	}

	public String getField(){return field;}
}
