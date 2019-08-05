package de.allianz.swdemo.endpoint;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import de.allianz.swdemo.controller.PersonController;
import de.personen.services.api.BuchApiController;
import de.personen.services.model.Buch;
import de.personen.services.model.ResponseStatus;
import io.swagger.annotations.ApiParam;

@RestController
public class BuchEndpoint extends BuchApiController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BuchEndpoint.class);
	
	@Autowired
	PersonController personController;
	
	public BuchEndpoint(NativeWebRequest request) {
		super(request);
	}
	
	public ResponseEntity<ResponseStatus> buchPost(
			@ApiParam(value = "Dieses Feld wird später dazu verwendet den Bucheintrag zu einer Person zuzuordnen") @Valid @RequestParam(value = "userid", required = false) String userid,
			@ApiParam(value = "") @Valid @RequestBody Buch buch) {
		return personController.addBuch(userid, buch);
	}

}
