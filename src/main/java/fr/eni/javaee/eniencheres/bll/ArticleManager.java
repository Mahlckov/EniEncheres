package fr.eni.javaee.eniencheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.dal.ArticleDAO;
import fr.eni.javaee.eniencheres.dal.DAOFactory;



public class ArticleManager {
private ArticleDAO articleDAO;
	
	public ArticleManager() {
		this.articleDAO=DAOFactory.getArticleDAO();
	}
	
	public List<Articles> selectionnerListArticles() throws BusinessException
	{
		return this.articleDAO.selectAll();
	}

	public Articles selectionnerArticle(int noArticle) throws BusinessException {
		return this.articleDAO.selectById(noArticle);
	}
	
	public void supprimerArticle(int noArticle) throws BusinessException{
		this.articleDAO.deleteArticle(noArticle);
	}
	
	public void ajouterArticle(Articles newArticle) throws BusinessException{
		BusinessException businessException = new BusinessException();
		this.validerArticle(newArticle);
		if(!businessException.hasErreurs()){
			articleDAO.insert(newArticle);
		}
		else{
			throw businessException;
		}
	}
	
	public void miseAjourArticle(Articles newArticle) throws BusinessException{
		BusinessException businessException = new BusinessException();
		this.validerArticle(newArticle);
		if(!businessException.hasErreurs()){
			articleDAO.updateArticle(newArticle);
		}
		else{
			throw businessException;
		}
	}
	
	public List<Articles> selectionnerListArticleSelonEtatVente(String inputEtatVente) throws BusinessException
	{
		return this.articleDAO.selectAllByEtatVente(inputEtatVente);
	}
	
	
	 private void validerArticle(Articles article) throws BusinessException{
		 BusinessException businessException = new BusinessException();
	    
	        
			if(article.getNomArticle()==null || article.getNomArticle().trim().length()>30) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_ERREUR);
			}
			if(article.getDescription()==null|| article.getDescription().trim().isEmpty()|| article.getDescription().trim().length()>300){
				businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DESCRIPTION_ERREUR);
			}
			if(article.getEtatVente()==null|| article.getEtatVente().trim().isEmpty()|| article.getDescription().trim().length()>30) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_ETAT_ERREUR);
			}
			if(article.getMiseAprix()<0 ||article.getMiseAprix()==0 ||article.getPrixVente()<0 ||article.getPrixVente()==0) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_PRIX_ERREUR);
			}
			if ((article.getDateDebutEncheres()==null && article.getDateDebutEncheres().isBefore(LocalDate.now()) )|| (article.getDateFinEncheres()==null && article.getDateFinEncheres().isBefore(LocalDate.now())) ){
				businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ARTICLE_ERREUR);
			}
		}	
}
