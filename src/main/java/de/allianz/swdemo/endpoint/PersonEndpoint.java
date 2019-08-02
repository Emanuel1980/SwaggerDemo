package de.allianz.swdemo.endpoint;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import de.allianz.swdemo.controller.PersonController;
import de.personen.services.api.PersonsApiController;
import de.personen.services.model.PersonData;
import de.personen.services.model.ResponseStatus;
import io.swagger.annotations.ApiParam;

@RestController
public class PersonEndpoint extends PersonsApiController {

	
	private static final Logger LOG = LoggerFactory.getLogger(PersonEndpoint.class);

	
	@Autowired
	private PersonController personController;
	
	public PersonEndpoint(NativeWebRequest request) {
		super(request);
	}
	
	@Override
	public ResponseEntity<ResponseStatus> personsPost(@ApiParam(value = ""  )  @Valid @RequestBody PersonData personData) {
		LOG.info("PersonEndpoint personsPost (Create)");
		return (ResponseEntity<ResponseStatus>) personController.addPersons(personData);
	}
	
	@Override
	public ResponseEntity<PersonData> personsGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "personId", required = true) String personId) {
		LOG.info("PersonEndpoint personsGet");
		return (ResponseEntity<PersonData>) personController.getPerson(personId);
	}
	
	@Override
	public ResponseEntity<PersonData> personsDelete(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "personId", required = true) String personId) {
		LOG.info("PersonEndpoint personsDelete");
		return (ResponseEntity<PersonData>) personController.delPerson(personId);
	}
	
	@Override
	public ResponseEntity<PersonData> personsPut(@ApiParam(value = "") @Valid @RequestParam(value = "personId", required = false) String personId,@ApiParam(value = ""  )  @Valid @RequestBody PersonData personData) {
		LOG.info("PersonEndpoint personsPut (Update)");
		return (ResponseEntity<PersonData>) personController.updatePerson(personData, personId);		
	}

}
