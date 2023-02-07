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
	private static final String SELECT_RETRAIT_BY_IDARTICLE = "SELECT* FROM RETRAITS WHERE no_article=?";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS(no_article, rue, code_postal,\r\n"
			+ "			 ville) "
			+ "			 VALUES(?,?,?,?)";

	@Override
	public Retrait selectRetraitByNoArticle(int noArticle) throws BusinessException {
		Retrait retrait = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{	
			
			PreparedStatement pstmt;
			ResultSet rs;
			pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_IDARTICLE);
			pstmt.setInt(1, noArticle);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String rue = rs.getString("rue");
				String code_postal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				Articles article = new Articles (rs.getInt("no_article"));
				
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
	

	@Override
	public void insertRetrait(Retrait retrait) throws BusinessException {

		if (retrait== null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
//				cnx.setAutoCommit(false); //does not commit transaction automatically after each query
			    PreparedStatement pstmt;
			    pstmt = cnx.prepareStatement(INSERT_RETRAIT);
			    pstmt.setInt(1, retrait.getNoArticle().getNoArticle());
			    pstmt.setString(2, retrait.getRue());
			    pstmt.setString(3, retrait.getCode_postal());
			    pstmt.setString(4, retrait.getVille());

			    
			    pstmt.executeUpdate();

					
			    
			    pstmt.close();
			 
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	
	}
	

}
