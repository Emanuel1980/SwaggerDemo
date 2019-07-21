package de.allianz.swdemo.endpoint;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import de.allianz.swdemo.controller.PersonController;
import de.personen.services.api.FindpersonsApiController;
import de.personen.services.model.PersonData;
import de.personen.services.model.PersonsData;
import io.swagger.annotations.ApiParam;

@RestController
public class FindPersonsEndpoint extends FindpersonsApiController {
	
	@Autowired
	private PersonController personController;	

	public FindPersonsEndpoint(NativeWebRequest request) {
		super(request);
	}
	
	@Override
	public ResponseEntity<PersonsData> findpersonsGet(@ApiParam(value = "") @Valid @RequestParam(value = "familyname", required = false) String familyname,@ApiParam(value = "") @Valid @RequestParam(value = "firstname", required = false) String firstname,@ApiParam(value = "") @Valid @RequestParam(value = "city", required = false) String city,@ApiParam(value = "") @Valid @RequestParam(value = "kontaktadresse", required = false) String kontaktadresse) {
		System.out.println("PersonEndpoint personsPut (Update)");
		return (ResponseEntity<PersonsData>) personController.findPersons(familyname, firstname, city, kontaktadresse);
	}

}
