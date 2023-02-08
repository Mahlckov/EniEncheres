package fr.eni.javaee.eniencheres.dal;

import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Articles;


public interface ArticleDAO {
	public List<Articles> selectAll() throws BusinessException;
	public Articles selectById(int noArticle) throws BusinessException;
	public void deleteArticle(int noArticle) throws BusinessException ;

	public void insert(Articles newArticle) throws BusinessException;
	public void updateArticle(Articles newArticle) throws BusinessException;
	
	public List<Articles> selectAllByEtatVente(String inputEtatVente) throws BusinessException;


}
