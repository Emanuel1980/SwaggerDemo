package de.allianz.swdemo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "personen")
public class PersonDataEntity {
	
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;        
    }
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)    
    @JoinColumn(name = "fk_personen_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private List<KontaktDataEntity> kontakte;    
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String familyName;
	
	private String street;
	
	@Column(name = "house_number")
	private String houseNumber;
	
	private String plz;
	
	private String city;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<KontaktDataEntity> getKontakte() {
		return kontakte;
	}

	public void setKontakte(List<KontaktDataEntity> kontakte) {
		this.kontakte = kontakte;
	}
	

}
