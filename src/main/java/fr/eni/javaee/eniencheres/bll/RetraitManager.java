package fr.eni.javaee.eniencheres.bll;

import java.time.LocalDate;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.bo.Retrait;
import fr.eni.javaee.eniencheres.dal.ArticleDAO;
import fr.eni.javaee.eniencheres.dal.DAOFactory;
import fr.eni.javaee.eniencheres.dal.RetraitDAO;

public class RetraitManager {
	private RetraitDAO retraitDAO;

	public RetraitManager() {
		this.retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	
	public Retrait selectRetraitById(int noID) throws BusinessException {
		Retrait retrait = retraitDAO.selectRetraitByNoArticle(noID);
		return retrait;
	}

	public void ajouterRetrait(Retrait newRetrait) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerRetrait(newRetrait);
		if (!businessException.hasErreurs()) {
			retraitDAO.insertRetrait(newRetrait);
		} else {
			throw businessException;}
		}

	private void validerRetrait(Retrait retrait) throws BusinessException {
		BusinessException businessException = new BusinessException();

		if (retrait.getNoArticle() == null || retrait.getNoArticle().getNoArticle() <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_NO_ARTICLE_ERREUR);
		}
		if (retrait.getRue() == null || retrait.getRue().trim().isEmpty()
				|| retrait.getRue().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_RUE_ERREUR);
		}
		if (retrait.getCode_postal() == null || retrait.getCode_postal().trim().isEmpty()
				|| retrait.getCode_postal().trim().length() > 5) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_CODEPOSTAL_ERREUR);
		}
		
		if (retrait.getVille() == null || retrait.getVille().trim().isEmpty()
				|| retrait.getVille().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_VILLE_ERREUR);
		}

	}

}
