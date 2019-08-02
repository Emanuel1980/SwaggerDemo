package de.allianz.swdemo.camunda.enums;

public enum CamundaPersonObject {
	
	START_PERSON("StartPerson"),
	END_PERSON("END_Person");
	
	private final String value;
	
	CamundaPersonObject(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}

}
