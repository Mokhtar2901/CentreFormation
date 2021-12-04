package de.projet.First;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Formateur extends User {
	
	private String spécialité;
	private LocalDate DOB = LocalDate.of(2000, 11, 11);
	
	@ManyToMany(mappedBy = "formateurs")
	private List<Formation> formations;
	@OneToMany(mappedBy = "formateur")
	private List<Reclamation> reclamations;
	
	public String getNomcomplet() {
		return getNom()+ " " + getPrenom();
	}
	public int getAge() {
		return (int)ChronoUnit.YEARS.between(DOB, LocalDate.now());
		
	}

}
