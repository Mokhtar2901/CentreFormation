package de.projet.First.DTO;

import lombok.Data;

@Data
public class UserDTO {
	private int id;
	private String nom;
	private String prenom;
	private String mail;
	private String tel;
	private String type;

}
