package de.projet.First;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;


@Entity
@Data
public class Formation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_formation;
	
	
	String type;
	int nb_session;
	int volume_horaire;
	
	@ManyToMany
	private List<Formateur> formateurs;

}
