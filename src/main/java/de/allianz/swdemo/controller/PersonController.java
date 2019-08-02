package de.allianz.swdemo.controller;

import java.util.List;
import java.util.Optional;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import de.allianz.swdemo.helper.PersonHelper;
import de.allianz.swdemo.model.PersonDataEntity;
import de.allianz.swdemo.repository.PersonenRepository;
import de.personen.services.model.Address;
import de.personen.services.model.PersonData;
import de.personen.services.model.PersonsData;
import de.personen.services.model.ResponseStatus;

@Component
public class PersonController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonenRepository perRepo;
	
	@Autowired
	private PersonHelper personHelper;
	
	public ResponseEntity<ResponseStatus> addPersons(PersonData reqPersonData) {
		LOG.info("PersonController.addPersons");
		ResponseStatus rspStatus = new ResponseStatus();
		if (reqPersonData != null) {
			rspStatus.setText(String.format("Der volle Name der neu angelegten Person heißt %s: ", reqPersonData.getFirstname() + " " + reqPersonData.getFamilyname()));
			PersonDataEntity personDataEntity =  personHelper.createPersonDataEntity(reqPersonData);			
			perRepo.save(personDataEntity);			
			rspStatus.setUserId(personDataEntity.getId().toString());
			return new ResponseEntity<ResponseStatus>(rspStatus, HttpStatus.CREATED);
		}
		rspStatus.setText("personData ist null");
		return new ResponseEntity<ResponseStatus>(rspStatus, HttpStatus.BAD_REQUEST);		
	}

	public ResponseEntity<?> getPerson(String personId) {
		LOG.info("PersonsController.getPerson");
		try { 
			Optional<PersonDataEntity> personOptional = perRepo.findById(personId);
			if (personOptional.isPresent()) {				
				PersonDataEntity person = personOptional.get();				
				return new ResponseEntity<PersonData>(personHelper.createPersonData(person), HttpStatus.OK);
			}
			ResponseStatus rspStatus = new ResponseStatus();
			rspStatus.setText("Person konnte mit der ID " + personId + " nicht gefunden werden");
			return new ResponseEntity<ResponseStatus>(rspStatus, HttpStatus.BAD_REQUEST);
			
		} catch (IllegalArgumentException ex) {
			return getErrorOutOfMe("Person konnte nicht mit der ID " + personId + " gefunden werden", "getPerson", ex);
		}
	}



	public ResponseEntity<?> delPerson(String personId) {
		LOG.info("PersonController.delPerson");
		try {
			Optional<PersonDataEntity> personOptional = perRepo.findById(personId);
			if (personOptional.isPresent()) {
				PersonDataEntity person = personOptional.get();
				PersonData rspPersonData = personHelper.createPersonData(person);
				perRepo.delete(person);
				
				return new ResponseEntity<PersonData>(rspPersonData, HttpStatus.OK);
			}
			
			ResponseStatus rspStatus = new ResponseStatus();
			rspStatus.setText("zu löschende Person konnte nicht mit der ID " + personId + " gefunden werden");
			return new ResponseEntity<ResponseStatus>(rspStatus, HttpStatus.UNPROCESSABLE_ENTITY);			
			
		} catch (Exception ex) {
			return getErrorOutOfMe("Person konnte nicht mit der ID " + personId + " gelöscht werden", "delPerson", ex);
		}
		
	}

	public ResponseEntity<?> updatePerson(PersonData reqPersonData, String personId) {
		LOG.info("PersonController.updatePerson");
		try {
			Optional<PersonDataEntity> personOptional = perRepo.findById(personId);
			if (personOptional.isPresent())  {
				PersonDataEntity personData = personOptional.get();
				PersonDataEntity personDataUpd = personHelper.updPersonDataEntity(personData, reqPersonData);
				perRepo.save(personDataUpd);
				
				PersonData rspPersonData = personHelper.createPersonData(perRepo.findById(personId).get());
				return new ResponseEntity<PersonData>(rspPersonData, HttpStatus.OK);
			}
			ResponseStatus rspStatus = new ResponseStatus();
			rspStatus.setText("zu aktualisierende Person konnte mit der ID " + personId + " nicht gefunden werden");
			return new ResponseEntity<ResponseStatus>(rspStatus, HttpStatus.UNPROCESSABLE_ENTITY);					
		} catch (Exception ex) {
			return getErrorOutOfMe("Person konnte nicht mit der ID " + personId + " veraendert werden", "updatePerson", ex);
		}		
	}

	public ResponseEntity<?> findPersons(String familyname, String firstname, String city, String kontaktadresse) {
		LOG.info("PersonController.findPersons");
		try {
			List<PersonDataEntity> list=null;
			
			if (! StringUtils.isEmpty(familyname) && ! StringUtils.isEmpty(firstname)) {
				list = perRepo.findByFirstNameOrFamilyName(firstname, familyname);
			} else if (! StringUtils.isEmpty(familyname)) {
				list = perRepo.findByFamilyName(familyname);
			} else if (! StringUtils.isEmpty(firstname)) {
 				list = perRepo.findByFirstName(firstname);
			} else if (! StringUtils.isEmpty(city)) {
				list = perRepo.findByCity(city);
			} else if (! StringUtils.isEmpty(kontaktadresse)) {
				list = perRepo.findByKontanktAdresse(kontaktadresse);
			}
			
			
			
			if (! list.isEmpty() && list.size() > 0 ) {
				PersonsData rspPersons = new PersonsData();
				
				for (PersonDataEntity person : list) {
					PersonData rspPerson = new PersonData();
					rspPerson.setFirstname(person.getFirstName());
					rspPerson.setFamilyname(person.getFamilyName());
					Address rspAddress = new Address();
					rspAddress.setCity(person.getCity());
					rspAddress.setStreetname(person.getStreet());
					rspAddress.setHousenumber(person.getHouseNumber());
					rspAddress.setPostleitzahl(person.getPlz());
					rspPerson.setAdresse(rspAddress);
					rspPersons.addPersondataItem(rspPerson);
				}
				return new ResponseEntity<PersonsData>(rspPersons, HttpStatus.OK);
				} else {
					LOG.error("Keine Person gefunden");
					ResponseStatus rspStatus = new ResponseStatus();
					rspStatus.setText("Keine Person gefunden");
					return new ResponseEntity<ResponseStatus>(rspStatus, HttpStatus.UNPROCESSABLE_ENTITY);					
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return getErrorOutOfMe("PKeine Person gefunden", "findPersons", ex);
		}
	}

	private ResponseEntity<?> getErrorOutOfMe(String errMsg, String methodName, Exception ex) {
		LOG.error("PersonenController." + methodName + ex.getMessage());
		ResponseStatus rspStatus = new ResponseStatus();
		rspStatus.setText(errMsg + " - Exceptionmessage: " + ex.getMessage());
		return new ResponseEntity<ResponseStatus>(rspStatus, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
}
