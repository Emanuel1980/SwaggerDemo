package de.allianz.swdemo.camunda.delegate;

import static de.allianz.swdemo.camunda.enums.CamundaPersonVariables.FAMILIEN_NAME;
import static de.allianz.swdemo.camunda.enums.CamundaPersonVariables.VOR_NAME;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("personAusgabe")
public class PersonAusgabe implements JavaDelegate {
	
	private static final Logger LOG = LoggerFactory.getLogger(PersonAusgabe.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LOG.info("PersonAusgabe.execeute");
		String familienName = (String) execution.getVariable(FAMILIEN_NAME.value());
		String vorName = (String) execution.getVariable(VOR_NAME.value());
		
		LOG.info(String.format("Vorname %s und Familienname ist %s", vorName, familienName));
	}

}
