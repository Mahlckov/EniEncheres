package fr.eni.javaee.eniencheres.dal;

import fr.eni.javaee.eniencheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.javaee.eniencheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.javaee.eniencheres.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.javaee.eniencheres.dal.jdbc.RetraitDAOJdbcImpl;
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
	
	public static EnchereDAO getEnchereDAO()
	{
		return new EnchereDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO()
	{
		return new CategorieDAOJdbcImpl();
	}
	
	public static RetraitDAO getRetraitDAO()
	{
		return new RetraitDAOJdbcImpl();
	}
	
}
	