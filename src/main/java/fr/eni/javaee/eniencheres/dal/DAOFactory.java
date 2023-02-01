package fr.eni.javaee.eniencheres.dal;

import fr.eni.javaee.eniencheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.javaee.eniencheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO()
	{
		return new ArticleDAOJdbcImpl();
	}
	
}
	