package de.allianz.swdemo.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.allianz.swdemo.model.KontaktDataEntity;
import de.allianz.swdemo.model.PersonDataEntity;
import de.allianz.swdemo.utils.CryptIdGenerator;
import de.personen.services.model.Address;
import de.personen.services.model.Kontakt;
import de.personen.services.model.PersonData;

@Component
public class PersonHelper {
	
	@Autowired
	private CryptIdGenerator cryptIdGenerator;	
	
	public PersonData createPersonData(PersonDataEntity person) {
		PersonData rspPerson = new PersonData();
		rspPerson.setFamilyname(person.getFamilyName());
		rspPerson.setFirstname(person.getFirstName());
		Address address = new Address();
		address.setStreetname(person.getCity());
		address.setHousenumber(person.getHouseNumber());
		address.setPostleitzahl(person.getPlz());
		address.setCity(person.getCity());		
		rspPerson.setAdresse(address);
		
		if (! person.getKontakte().isEmpty() && person.getKontakte().size() > 0) {
			List<Kontakt> rspKontakte = new ArrayList<>();
			for (KontaktDataEntity kontaktEntity: person.getKontakte()) {
				Kontakt rspKontakt = new Kontakt();
				rspKontakt.setKontaktadresse(kontaktEntity.getKontaktAdresse());
				rspKontakt.setKontaktart(kontaktEntity.getKontaktArt());				
				rspKontakte.add(rspKontakt);
			}			
			rspPerson.setKontakt(rspKontakte );
		}

		return rspPerson;
	}

	public PersonDataEntity createPersonDataEntity(PersonData reqPersonData) {
		PersonDataEntity person = new PersonDataEntity();
		final String personId = cryptIdGenerator.generateCryptId();
		person.setId(personId);
		final String kontaktId = cryptIdGenerator.generateCryptId();
		person.setFirstName(reqPersonData.getFirstname());
		person.setFamilyName(reqPersonData.getFamilyname());
		person.setStreet(reqPersonData.getAdresse().getStreetname());
		person.setHouseNumber(reqPersonData.getAdresse().getHousenumber());
		person.setPlz(reqPersonData.getAdresse().getPostleitzahl());
		person.setCity(reqPersonData.getAdresse().getCity());		
		
		if (reqPersonData.getKontakt() != null && ! reqPersonData.getKontakt().isEmpty()) {
			List<KontaktDataEntity> kontakte = new ArrayList<>();
			for (Kontakt reqKontakt: reqPersonData.getKontakt()) {
				KontaktDataEntity kontakt = new KontaktDataEntity();
				kontakt.setKontaktAdresse(reqKontakt.getKontaktadresse());
				kontakt.setKontaktArt(reqKontakt.getKontaktart());
				kontakt.setId(cryptIdGenerator.generateCryptId());
				kontakt.setFkPersonenId(personId);
				kontakte.add(kontakt);
			}
			person.setKontakte(kontakte);
		}
		
		return person;
	}	
	
	public PersonDataEntity updPersonDataEntity(PersonDataEntity personData, PersonData reqPersonData) {
		
		personData.setFirstName(reqPersonData.getFirstname());
		personData.setFamilyName(reqPersonData.getFamilyname());
		personData.setStreet(reqPersonData.getAdresse().getStreetname());
		personData.setHouseNumber(reqPersonData.getAdresse().getHousenumber());
		personData.setPlz(reqPersonData.getAdresse().getPostleitzahl());
		personData.setCity(reqPersonData.getAdresse().getCity());		
		
		return personData;
	}	

}
