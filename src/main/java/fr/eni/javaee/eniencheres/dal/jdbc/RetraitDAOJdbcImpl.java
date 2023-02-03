package fr.eni.javaee.eniencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.bo.Retrait;
import fr.eni.javaee.eniencheres.bo.Utilisateur;
import fr.eni.javaee.eniencheres.dal.CodesResultatDAL;
import fr.eni.javaee.eniencheres.dal.ConnectionProvider;
import fr.eni.javaee.eniencheres.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO{
	private static final String SELECT_RETRAIT_BY_IDARTICLE = "SELECT* FROM RETRAIT WHERE no_article=?";

	@Override
	public Retrait selectRetraitByNoArticle(int noArticle) throws BusinessException {
		Retrait retrait = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement ps = cnx.prepareStatement(SELECT_RETRAIT_BY_IDARTICLE);			
			ps.setInt(1, noArticle);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String rue = rs.getString("rue");
				String code_postal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				Articles article = new Articles (rs.getInt(noArticle));
				
				retrait = new Retrait(article, rue, code_postal, ville);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_ECHEC);
			throw businessException;	
		}
		if(retrait.getNoArticle()==null){
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_RETRAIT_INEXISTANT);
			throw businessException;
		}
		return retrait;
	}
	

}
