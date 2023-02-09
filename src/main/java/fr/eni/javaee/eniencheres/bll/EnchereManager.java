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

	public List<String> validerEncheres(Encheres enchere) throws BusinessException{
		 BusinessException businessException = new BusinessException();
	     List <String> errorList = new ArrayList<>();
	        
			if(enchere.getMontant_enchere() < enchere.getNoArticle().getPrixVente()) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERES_MONTANT_ERREUR);
				errorList.add("Le montant de l'enchère ne peut pas être inférieur au prix de vente en cours ("+enchere.getNoArticle().getPrixVente()+" points).");
			}
			if (enchere.getNoAcheteur().getCredit()< enchere.getMontant_enchere()) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_ENCHERES_CREDIT_ERREUR);
				errorList.add("Vos crédits sont insuffisants pour pouvoir effectuer une enchère d'un montant de "+enchere.getMontant_enchere()+" points. (Vos crédits : "+enchere.getNoAcheteur().getCredit()+" points.)" );


			}
			
			if(enchere.getNoArticle()==null){
				businessException.ajouterErreur(CodesResultatBLL.REGLE_NO_ARTICLE_NULL);
				errorList.add("Une erreur concernant l'article est survenue.");

				
			}
			if(enchere.getNoAcheteur()==null) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_NO_ACHETEUR_NULL);
				errorList.add("Vous devez être connecté pour pouvoir effectuer une enchère.");

			}
			return errorList;
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
}