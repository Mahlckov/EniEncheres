package fr.eni.javaee.eniencheres.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.dal.ArticleDAO;
import fr.eni.javaee.eniencheres.dal.CodesResultatDAL;
import fr.eni.javaee.eniencheres.dal.ConnectionProvider;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES";
	private static final String SELECT_BY_ID = "SELECT* FROM ARTICLES WHERE no_article=?";
	private static final String DELETE_ARTICLE ="DELETE FROM ARTICLES WHERE no_article=?";

	private static final String INSERT_ARTICLE ="INSERT INTO ARTICLES( nom_article, description, date_debut_encheres,\r\n"
			+ "			 date_fin_encheres,prix_initial, prix_vente, etat_vente) VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE_ARTICLE ="UPDATE ARTICLES SET nom_article=?, description=?, date_debut_encheres=?, \"\r\n"
			+ "				+ \"date_fin_encheres=?, prix_initial=?, prix_vente=?, etat_vente=? WHERE no_article=?";
	
	@Override
	public List<Articles> selectAll() throws BusinessException {
		List<Articles> listArticles = new ArrayList<Articles>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listArticles.add(new Articles(
						rs.getInt("noArticle"), 
						rs.getString("nomArticle"),
						rs.getString("description"), 
						rs.getDate("dateDebutEncheres").toLocalDate(),
						rs.getDate("dateFinEncheres").toLocalDate(),
						rs.getInt("miseAprix"), 
						rs.getInt("prixVente"), 
						rs.getString("etatVente")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listArticles;
	}	
	
	@Override
	public Articles selectById(int noArticle) throws BusinessException {
		Articles article = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, noArticle);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String nomArticle = rs.getString("nomArticle");
				String description = rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("dateDebutEncheres").toLocalDate();
				LocalDate dateFinEncheres =	rs.getDate("dateFinEncheres").toLocalDate();
				int miseAprix = rs.getInt("miseAprix");
				int prixVente = rs.getInt("prixVente");
				String etatVente = rs.getString("etatVente");
				
				article = new Articles(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, 
						miseAprix, prixVente, etatVente);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;	
		}
		if(article.getNoArticle()==0){
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_INEXISTANT);
			throw businessException;
		}
		return article;
	}
	
	@Override
	public void deleteArticle(int noArticle) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement ps = cnx.prepareStatement(DELETE_ARTICLE);
			ps.setInt(1, noArticle);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE_ERREUR);
			throw businessException;
		}
	}
	
@Override
	
	public void insert(Articles article) throws BusinessException {
		if (article== null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);//does not commit transaction automatically after each query
			    PreparedStatement pstmt;
			    ResultSet rs;
			    pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			    pstmt.setString(1, article.getNomArticle());
			    pstmt.setString(2, article.getDescription());
			    pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			    pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			    pstmt.setInt(5, article.getMiseAprix());
//			    pstmt.setInt(6, article.getPrixVente());
			    pstmt.setString(7, article.getEtatVente());
			    
			    pstmt.executeUpdate();
			    rs= pstmt.getGeneratedKeys();
			   
			    if(rs.next()) {
					article.setNoArticle(rs.getInt(1));
			    }
			    rs.close();
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
		

	@Override
	public void updateArticle(Articles article) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3,java.sql.Date.valueOf(article.getDateDebutEncheres()));
		    pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
		    pstmt.setInt(5, article.getMiseAprix());
//		    pstmt.setInt(6, article.getPrixVente());
		    pstmt.setString(7, article.getEtatVente());
		    pstmt.setInt(8, article.getNoArticle());
		    pstmt.executeUpdate();
		    pstmt.close();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ERREUR);
			throw businessException;
		}
	}
	
	
	
}
