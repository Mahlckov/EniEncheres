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
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.bo.Retrait;
import fr.eni.javaee.eniencheres.bo.Utilisateur;
import fr.eni.javaee.eniencheres.dal.ArticleDAO;
import fr.eni.javaee.eniencheres.dal.CodesResultatDAL;
import fr.eni.javaee.eniencheres.dal.ConnectionProvider;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	UtilisateurDAOJdbcImpl utilisateurDAOJdbcImpl = new UtilisateurDAOJdbcImpl();
	CategorieDAOJdbcImpl categorieDAOJdbcImpl = new CategorieDAOJdbcImpl();
//	RetraitDAOJdbcImpl retraitDAOJdbcImpl = new RetraitDAOJdbcImpl();

	
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES";
	private static final String SELECT_BY_ID = "SELECT* FROM ARTICLES WHERE no_article=?";
	private static final String DELETE_ARTICLE ="DELETE FROM ARTICLES WHERE no_article=?";

	private static final String INSERT_ARTICLE ="INSERT INTO ARTICLES(nom_article, description, date_debut_encheres,\r\n"
			+ "			 date_fin_encheres, prix_initial, etat_vente, no_categorie, no_vendeur) VALUES(?,?,?,?,?,?,?,?)";
	private static final String UPDATE_ARTICLE ="UPDATE ARTICLES SET nom_article=?, description=?, date_debut_encheres=?, \"\r\n"
			+ "				+ \"date_fin_encheres=?, prix_initial=?, etat_vente=?, no_categorie=?, no_vendeur=? WHERE no_article=?";
	
	@Override
	public List<Articles> selectAll() throws BusinessException {
		List<Articles> listArticles = new ArrayList<Articles>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				int noArticle = rs.getInt("no_article");
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
				int miseAprix = rs.getInt("prix_initial");
				String etatVente = rs.getString("etat_vente");
				
				Categorie categorie = categorieDAOJdbcImpl.selectByIdCategorie(rs.getInt("no_categorie")) ;
				Utilisateur vendeur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_vendeur"));
//				Retrait retrait = retraitDAOJdbcImpl.selectRetraitByNoArticle(rs.getInt("no_vendeur"));

				Articles article = new Articles(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAprix, etatVente, categorie, vendeur);
				listArticles.add(article);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLES_ECHEC);
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
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
				int miseAprix = rs.getInt("prix_initial");
				String etatVente = rs.getString("etat_vente");
				
				Categorie categorie = categorieDAOJdbcImpl.selectByIdCategorie(rs.getInt("no_categorie")) ;
				Utilisateur vendeur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_vendeur"));
				
				article = new Articles(noArticle, nomArticle, description,dateDebutEncheres, dateFinEncheres, miseAprix, etatVente, categorie, vendeur);
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
//				cnx.setAutoCommit(false);//does not commit transaction automatically after each query
			    PreparedStatement pstmt;
			    ResultSet rs;
			    pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			    pstmt.setString(1, article.getNomArticle());
			    pstmt.setString(2, article.getDescription());
			    pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			    pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			    pstmt.setInt(5, article.getMiseAprix());
			    pstmt.setString(6, article.getEtatVente());
			    pstmt.setInt(7, Integer.valueOf(article.getNoCategorie()));
			    pstmt.setInt(8, article.getNoVendeur());
			    
			   
			    Categorie noCategorie = categorieDAOJdbcImpl.selectByIdCategorie(0) ;
				Utilisateur noVendeur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("noVendeur"));
			    
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
		    pstmt.setString(6, article.getEtatVente());
		    pstmt.setInt(7, article.getNoCategorie());
		    pstmt.setInt(8, article.getNoVendeur());
		    pstmt.setInt(9, article.getNoArticle());
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
