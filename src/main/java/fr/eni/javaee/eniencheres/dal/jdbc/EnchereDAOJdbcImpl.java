package fr.eni.javaee.eniencheres.dal.jdbc;

import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.dal.EnchereDAO;


public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String SELECT_ALL = "SELECT * FROM ENCHERES";
	private static final String SELECT_BY_ID = "SELECT* FROM ENCHERES WHERE noArticle=?";
	
	private static final String INSERT_ARTICLE ="INSERT INTO ENCHERES(date_enchere, montant_enchere) VALUES(?,?)";
	private static final String UPDATE_ARTICLE ="UPDATE ENCHERES SET date_enchere=?, montant_enchere=? WHERE noArticle=?";
	private static final String DELETE_ARTICLE ="DELETE FROM ENCHERES WHERE noArticle=?";


//	public List<ListeEnchere> selectAll() throws BusinessException {
//	//Créer une liste d'enchères dans Utilisateur + Articles?	
//		
//		
//	}
	
	
}
