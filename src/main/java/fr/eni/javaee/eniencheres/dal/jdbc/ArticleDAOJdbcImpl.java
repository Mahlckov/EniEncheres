package fr.eni.javaee.eniencheres.dal.jdbc;


import fr.eni.javaee.eniencheres.dal.ArticleDAO;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES";
	private static final String SELECT_BY_ID = "SELECT* FROM ARTICLES WHERE noArticle=?";
	
	private static final String INSERT_ARTICLE ="INSERT INTO ARTICLES(noArticle, nomArticle, description, dateDebutEncheres,\r\n"
			+ "			 dateFinEncheres, miseAprix, prixVente, etatVente) VALUES(?,?,?,?,?,?,?,?)";
	private static final String UPDATE_ARTICLE ="UPDATE ARTICLES SET nomArticle=?, description=?, dateDebutEncheres=?, \"\r\n"
			+ "				+ \"dateFinEncheres=?, miseAprix=?, prixVente=?, etatVente=? WHERE noArticle=?";
	private static final String DELETE_ARTICLE ="DELETE FROM ARTICLES WHERE noArticle=?";


}
