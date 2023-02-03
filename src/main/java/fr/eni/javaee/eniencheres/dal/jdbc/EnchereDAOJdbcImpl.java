package fr.eni.javaee.eniencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.bo.Encheres;
import fr.eni.javaee.eniencheres.bo.Utilisateur;
import fr.eni.javaee.eniencheres.dal.CodesResultatDAL;
import fr.eni.javaee.eniencheres.dal.ConnectionProvider;
import fr.eni.javaee.eniencheres.dal.EnchereDAO;



public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String SELECT_ALL_ENCHERES_BY_NOARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ?";
	UtilisateurDAOJdbcImpl utilisateurDAOJdbcImpl = new UtilisateurDAOJdbcImpl();
	ArticleDAOJdbcImpl articleDAOJdbcImpl = new ArticleDAOJdbcImpl();
	
	
	
	
	
	
	public List<Encheres> selectAllEncheresByNoArticle(int noArticle) throws BusinessException {
		List<Encheres> listEncheres = new ArrayList<Encheres>();
		
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
		PreparedStatement ps = cnx.prepareStatement(SELECT_ALL_ENCHERES_BY_NOARTICLE);			
		ps.setInt(1, noArticle);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Utilisateur noAcheteur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_acheteur")); 
			Articles articleNo = articleDAOJdbcImpl.selectById(rs.getInt("no_article")) ;
			LocalDate date_enchere = rs.getDate("date_enchere").toLocalDate();
			int montant_enchere = rs.getInt("montant_enchere");
			
		
			Encheres encheres = new Encheres(noAcheteur,articleNo,date_enchere,montant_enchere);
			listEncheres.add(encheres);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERES_ECHEC);
			throw businessException;
		}
		return listEncheres;
	}
		
	
	}

	
	

