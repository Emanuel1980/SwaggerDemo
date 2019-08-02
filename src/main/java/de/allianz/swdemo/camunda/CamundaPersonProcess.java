package de.allianz.swdemo.camunda;

import static de.allianz.swdemo.camunda.enums.CamundaPersonVariables.FAMILIEN_NAME;
import static de.allianz.swdemo.camunda.enums.CamundaPersonVariables.VOR_NAME;
import static de.allianz.swdemo.camunda.enums.CamundaPersonObject.START_PERSON;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import de.personen.services.model.PersonData;

@Service
public class CamundaPersonProcess {
	
	private static final Logger LOG = LoggerFactory.getLogger(CamundaPersonProcess.class);
	
	@Autowired
	private RuntimeService runtimeService;
	
	public void createPersonProcess(PersonData reqPersonData, String userId) {
		LOG.info("CamundaPersonProcess.createPersonProcess");
		VariableMap map = Variables.createVariables();		
		map.putValue(FAMILIEN_NAME.value(), reqPersonData.getFamilyname());
		map.putValue(VOR_NAME.value(), reqPersonData.getFirstname());
		runtimeService.startProcessInstanceByMessage(START_PERSON.value(), userId, map);
	}


}
