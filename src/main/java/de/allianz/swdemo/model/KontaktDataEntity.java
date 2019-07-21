package de.allianz.swdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kontakte")
public class KontaktDataEntity {
	
	@Id
	@Column(name = "id", unique=true, nullable=false, updatable=false)
	private String Id;
	
	@Column(name = "fk_personen_id", unique=false, nullable=false, updatable=false)
	private String fkPersonenId;
	
	@Column(name = "kontakt_adresse")
	private String kontaktAdresse;
	
	@Column(name = "kontakt_art")
	private String kontaktArt;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getFkPersonenId() {
		return fkPersonenId;
	}

	public void setFkPersonenId(String fk_personen_id) {
		this.fkPersonenId = fk_personen_id;
	}

	public String getKontaktAdresse() {
		return kontaktAdresse;
	}

	public void setKontaktAdresse(String kontaktAdresse) {
		this.kontaktAdresse = kontaktAdresse;
	}

	public String getKontaktArt() {
		return kontaktArt;
	}

	public void setKontaktArt(String kontaktArt) {
		this.kontaktArt = kontaktArt;
	}

}
