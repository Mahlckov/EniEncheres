package fr.eni.javaee.eniencheres.dal;

import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public Utilisateur selectUserById(int id) throws BusinessException;
	
	public List<Utilisateur> selectAllUser() throws BusinessException;
	
	public void updateUser(Utilisateur data) throws BusinessException;
	
	public void insertUser(Utilisateur data) throws BusinessException;
	
	public void deleteUser(int id) throws BusinessException;
	
	public Utilisateur selectUserByPseudo(String pseudo) throws BusinessException ;
	
	public Utilisateur selectUserByMail(String mail) throws BusinessException ;



}

