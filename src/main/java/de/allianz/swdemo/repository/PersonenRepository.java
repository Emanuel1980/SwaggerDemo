package de.allianz.swdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.allianz.swdemo.model.PersonDataEntity;

public interface PersonenRepository extends CrudRepository<PersonDataEntity, String> {
	
	public List<PersonDataEntity> findByFamilyName(String familyName);

	//public List<PersonDataEntity> findByFirstName(String firstname);

	public List<PersonDataEntity> findByCity(String city);

	public List<PersonDataEntity> findByFirstNameAndFamilyName(String firstname, String familyName);
	
	public List<PersonDataEntity> findByFirstNameOrFamilyName(String firstname, String familyName);
	
	@Query("select p from PersonDataEntity p where p.firstName like :firstname%")
	public List<PersonDataEntity> findByFirstName(String firstname);

	@Query("select p from PersonDataEntity p inner join p.kontakte ko where p.id = ko.fkPersonenId AND ko.kontaktAdresse like :kontaktadresse%")
	public List<PersonDataEntity> findByKontanktAdresse(String kontaktadresse);

}
