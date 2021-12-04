package de.projet.First.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import de.projet.First.Administrateur;
import de.projet.First.Candidat;
import de.projet.First.Formateur;
import de.projet.First.User;
import de.projet.First.DTO.UserDTO;
import de.projet.First.repositories.UserRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepos;
	private ModelMapper mapper;
	
	
	public UserDTO saveUserInDB(UserDTO dto) {
		 
		User user = null;
		switch (dto.getType()) {
		case "Administrateur":
			user = mapper.map(dto, Administrateur.class);
			break;
		case "Formateur":
			user = mapper.map(dto, Formateur.class);
			break;
		case "Candidat":
			user = mapper.map(dto, Candidat.class);
			break;
		
		
		}
		user = userRepos.save(user);
		dto.setId(user.getId());
		return dto;
		
		
	}
	
	public List<UserDTO> getAllUser(){
		/*
		 * List<User> entities = userRepos.findAll();
		 *  List<UserDTO> dtos = newArrayList<>(); 
		 * for (User user : entities) { 
		 * UserDTO dto = mapper.map(user,UserDTO.class); 
		 * 
		 * //if(user instanceof Formateur) 
		 * //dto.setType("Formateur");
		 * 
		 * dto.setType(user.getClass().getSimpleName());// pour remplir le champ type
		 * car il n'existe pas dans la base
		 * dtos.add(dto);
		 * }
		 * return dtos;
		 */
		
		return userRepos.findAll().stream().map((User ent) -> {
			UserDTO dto = mapper.map(ent, UserDTO.class);
			dto.setType(ent.getClass().getSimpleName());
			return dto;
		}).collect(Collectors.toList());
		
		
	}
	
	
	  public UserDTO getUserById(int id){
		  Optional<User> user =userRepos.findById(id); 
		  user.orElseThrow(()->new NoSuchElementException("n'existe pas"));
		  UserDTO dto = mapper.map(user, UserDTO.class);
			 dto.setType(user.getClass().getSimpleName());
			 return dto;
	 
	 
	  }

	  public UserDTO UpdateUserById(int id, UserDTO newUser) {
		UserDTO dto= getUserById(id);
		User user1 = mapper.map(dto, User.class);
		if(newUser.getNom()!=null)
			user1.setNom(newUser.getNom());
		if(newUser.getPrenom()!=null)
			user1.setPrenom(newUser.getPrenom());
		if(newUser.getMail()!=null)
			user1.setMail(newUser.getMail());
		if(newUser.getTel()!=null)
			user1.setTel(newUser.getTel());
		if(newUser.getType()!=null)
			user1.setType(newUser.getType());
		userRepos.save(user1);
		UserDTO dtos = mapper.map(user1, UserDTO.class);
		
		return dtos;
	}

	 public UserDTO deleteUserById(int id) {
		
		 UserDTO user1 = getUserById(id);
		 User user=mapper.map(user1, User.class);
		 userRepos.deleteById(id);
		 UserDTO dtos = mapper.map(user, UserDTO.class);
		 return dtos;
	 }
	  
	  
	  
		  
	  

	
	 
	
	

	
	
	
	
	  
	  
	  
	  }
	 


