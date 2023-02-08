package fr.eni.javaee.eniencheres.dal;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.bo.Retrait;

public interface RetraitDAO {
	public Retrait selectRetraitByNoArticle(int noArticle) throws BusinessException;

	public void insertRetrait(Retrait retrait) throws BusinessException;

}
