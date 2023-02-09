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
	
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES";
	private static final String SELECT_BY_ID = "SELECT* FROM ARTICLES WHERE no_article=?";
	private static final String DELETE_ARTICLE ="DELETE FROM ARTICLES WHERE no_article=?";

	private static final String INSERT_ARTICLE ="INSERT INTO ARTICLES(nom_article, description, date_debut_encheres,\r\n"
			+ "			 date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, no_vendeur) "
			+ "			 VALUES(?,?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE_ARTICLE ="UPDATE ARTICLES SET nom_article=?, description=?, date_debut_encheres=?, \r\n"
			+ "				date_fin_encheres=?, prix_initial=?, prix_vente=?, etat_vente=?, no_categorie=?, \r\n"
			+ "				no_vendeur=?, no_acheteur=?  WHERE no_article=?";
	
	private static final String UPDATE_ARTICLE_SANS_noACHETEUR ="UPDATE ARTICLES SET nom_article=?, description=?, date_debut_encheres=?, \r\n"
			+ "				date_fin_encheres=?, prix_initial=?, prix_vente=?, etat_vente=?, no_categorie=?, \r\n"
			+ "				no_vendeur=?  WHERE no_article=?";
	
	private static final String SELECT_ALL_BY_ETAT_VENTE = "SELECT* FROM ARTICLES WHERE etat_vente=?";
	
	private static final String SELECT_ALL_BY_CATEGORIE = "SELECT* FROM ARTICLES WHERE no_categorie=?";


	
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
				int prixVente = rs.getInt("prix_vente");
				String etatVente = rs.getString("etat_vente");
				
				Categorie categorie = categorieDAOJdbcImpl.selectByIdCategorie(rs.getInt("no_categorie")) ;
				Utilisateur vendeur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_vendeur"));
				Utilisateur acheteur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_acheteur"));

				Articles article = new Articles(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAprix, prixVente, etatVente, categorie, vendeur, acheteur);
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
				int prixVente = rs.getInt("prix_vente");
				String etatVente = rs.getString("etat_vente");
				
				Categorie categorie = categorieDAOJdbcImpl.selectByIdCategorie(rs.getInt("no_categorie")) ;
				Utilisateur vendeur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_vendeur"));
				Utilisateur acheteur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_acheteur"));
				
				article = new Articles(noArticle, nomArticle, description,dateDebutEncheres, dateFinEncheres, miseAprix, prixVente, etatVente, categorie, vendeur, acheteur);
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
//				cnx.setAutoCommit(false); //does not commit transaction automatically after each query
			    PreparedStatement pstmt;
			    ResultSet rs;
			    pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			    pstmt.setString(1, article.getNomArticle());
			    pstmt.setString(2, article.getDescription());
			    pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			    pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			    pstmt.setInt(5, article.getMiseAprix());
			    pstmt.setInt(6, article.getPrixVente());
			    pstmt.setString(7, article.getEtatVente());
			    pstmt.setInt(8, article.getNoCategorie().getNoCategorie());
			    pstmt.setInt(9, article.getNoVendeur().getNoUtilisateur());
//			    pstmt.setInt(10, article.getNoAcheteur().getNoUtilisateur());
			    
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
			
			PreparedStatement pstmt=null;
			
			//SI UPDATE D UN ARTICLE AVANT DEBUT DE LA VENTE(noACHETEUR = null DONC NON MIS DANS LE STATEMENT)
			
			if(article.getNoAcheteur()==null) {
				
				pstmt = cnx.prepareStatement(UPDATE_ARTICLE_SANS_noACHETEUR);
				pstmt.setString(1,article.getNomArticle());
				pstmt.setString(2,article.getDescription());
				pstmt.setDate(3,java.sql.Date.valueOf(article.getDateDebutEncheres()));
			    pstmt.setDate(4,java.sql.Date.valueOf(article.getDateFinEncheres()));
			    pstmt.setInt(5,article.getMiseAprix());
			    pstmt.setInt(6,article.getPrixVente());
			    pstmt.setString(7,article.getEtatVente());
			    pstmt.setInt(8,article.getNoCategorie().getNoCategorie());
			    pstmt.setInt(9,article.getNoVendeur().getNoUtilisateur());
			    pstmt.setInt(10,article.getNoArticle());

			    pstmt.executeUpdate();
			    pstmt.close();	
				

			}
			
			//SI UPDATE D'ARTICLE SUITE A UNE ENCHERE
			else {
			
			pstmt = cnx.prepareStatement(UPDATE_ARTICLE); 
			
			
			pstmt.setString(1,article.getNomArticle());
			pstmt.setString(2,article.getDescription());
			pstmt.setDate(3,java.sql.Date.valueOf(article.getDateDebutEncheres()));
		    pstmt.setDate(4,java.sql.Date.valueOf(article.getDateFinEncheres()));
		    pstmt.setInt(5,article.getMiseAprix());
		    pstmt.setInt(6,article.getPrixVente());
		    pstmt.setString(7,article.getEtatVente());
		    pstmt.setInt(8,article.getNoCategorie().getNoCategorie());
		    pstmt.setInt(9,article.getNoVendeur().getNoUtilisateur());
		    pstmt.setInt(10,article.getNoAcheteur().getNoUtilisateur());
		    pstmt.setInt(11,article.getNoArticle());

		    pstmt.executeUpdate();
		    pstmt.close();	
		}}
		catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ERREUR);
			throw businessException;
		}
	}
	
	public List<Articles> selectAllByEtatVente(String inputEtatVente) throws BusinessException {
		List<Articles> listArticles = new ArrayList<Articles>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BY_ETAT_VENTE);
			pstmt.setString(1, inputEtatVente);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				int noArticle = rs.getInt("no_article");
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
				int miseAprix = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				String etatVente = rs.getString("etat_vente");
				
				Categorie categorie = categorieDAOJdbcImpl.selectByIdCategorie(rs.getInt("no_categorie")) ;
				Utilisateur vendeur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_vendeur"));
				Utilisateur acheteur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_acheteur"));

				Articles article = new Articles(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAprix, prixVente, etatVente, categorie, vendeur, acheteur);
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
	
	
	public List<Articles> selectAllByCategorie(Categorie inputCategorie) throws BusinessException {
		List<Articles> listArticles = new ArrayList<Articles>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BY_CATEGORIE);
			pstmt.setInt(1, inputCategorie.getNoCategorie());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				int noArticle = rs.getInt("no_article");
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
				int miseAprix = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				String etatVente = rs.getString("etat_vente");
				
				Categorie categorie = categorieDAOJdbcImpl.selectByIdCategorie(rs.getInt("no_categorie")) ;
				Utilisateur vendeur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_vendeur"));
				Utilisateur acheteur = utilisateurDAOJdbcImpl.selectUserById(rs.getInt("no_acheteur"));

				Articles article = new Articles(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAprix, prixVente, etatVente, categorie, vendeur, acheteur);
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

	
	
	
}
