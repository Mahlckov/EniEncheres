package fr.eni.javaee.eniencheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.bo.Encheres;
import fr.eni.javaee.eniencheres.bo.Utilisateur;
import fr.eni.javaee.eniencheres.dal.DAOFactory;
import fr.eni.javaee.eniencheres.dal.EnchereDAO;

public class EnchereManager {
	private EnchereDAO enchereDAO;
	
	
	public EnchereManager() {
		this.enchereDAO=DAOFactory.getEnchereDAO();
		
	}
	
	public void insertEnchere(Encheres enchere) throws BusinessException {

		enchereDAO.insertEnchere(enchere);
	}

	private void validerEncheres(Encheres enchere) throws BusinessException{
		 BusinessException businessException = new BusinessException();
	     
	        
			if(enchere.getMontant_enchere() < enchere.getNoArticle().getPrixVente()) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERES_MONTANT_ERREUR);
			}
			
			if(enchere.getNoArticle()==null){
				businessException.ajouterErreur(CodesResultatBLL.REGLE_NO_ARTICLE_NULL);
				
			}
			if(enchere.getNoAcheteur()==null) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_NO_ACHETEUR_NULL);
			}
			if (enchere.getDate_enchere()==null || enchere.getDate_enchere().isBefore(LocalDate.now())){
				businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ENCHERE_ERREUR);
			}
			
			
		}	
	
	
	private List<Encheres> selectByNoArticle(int noArticle) {
		List<Encheres> listEncheres = new ArrayList<>();
			try {
	
				 listEncheres=enchereDAO.selectAllEncheresByNoArticle(noArticle);
			} catch (BusinessException e) {
				
				e.printStackTrace();
			}
			return listEncheres;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public List<Encheres> validateDateEnchere(LocalDate date_enchere) throws BusinessException
//	{
//		LocalDate currentDate = LocalDate.now();
//		List<Encheres> liste = new ArrayList<Encheres>();
//	    
//	    if(date_enchere.isAfter(currentDate)||date_enchere.isEqual(currentDate)) {
//	    	liste.add((Encheres) this.enchereDAO.selectAll());
//	    }
//	    return liste;
//		
//	
//}}

	
}