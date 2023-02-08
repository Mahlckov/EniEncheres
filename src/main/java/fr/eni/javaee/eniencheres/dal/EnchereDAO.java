package fr.eni.javaee.eniencheres.dal;


import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Encheres;


public interface EnchereDAO {

	public List<Encheres> selectAllEncheresByNoArticle(int noArticle) throws BusinessException;

	public void insertEnchere(Encheres enchere) throws BusinessException;
	
	
	
}
