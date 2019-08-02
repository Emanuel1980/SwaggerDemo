package de.allianz.swdemo.camunda.enums;

public enum CamundaPersonVariables {
	
	FAMILIEN_NAME("Familienname"),
	VOR_NAME("Vorname");
	
	private final String value;
	
	CamundaPersonVariables(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}

}
