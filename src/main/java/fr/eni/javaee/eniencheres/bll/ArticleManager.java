package fr.eni.javaee.eniencheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.dal.ArticleDAO;
import fr.eni.javaee.eniencheres.dal.DAOFactory;

public class ArticleManager {
	private ArticleDAO articleDAO;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}

	public List<Articles> selectionnerListArticles() throws BusinessException {
		return this.articleDAO.selectAll();
	}

	public Articles selectionnerArticle(int noArticle) throws BusinessException {
		return this.articleDAO.selectById(noArticle);
	}

	public void supprimerArticle(int noArticle) throws BusinessException {
		this.articleDAO.deleteArticle(noArticle);
	}

	public void ajouterArticle(Articles newArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerArticle(newArticle);
		if (!businessException.hasErreurs()) {
			articleDAO.insert(newArticle);
		} else {
			throw businessException;
		}
	}

	public void miseAjourArticle(Articles newArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerArticle(newArticle);
		if (!businessException.hasErreurs()) {
			articleDAO.updateArticle(newArticle);
		} else {
			throw businessException;
		}
	}

	public List<Articles> selectionnerListArticleSelonEtatVente(String inputEtatVente) throws BusinessException {
		return this.articleDAO.selectAllByEtatVente(inputEtatVente);
	}

	public List<Articles> selectionnerListArticleSelonCategorie(Categorie inputCategorie) throws BusinessException {
		return this.articleDAO.selectAllByCategorie(inputCategorie);
	}
	
<<<<<<< HEAD
	public List<Articles> selectionnerListArticleSelonEtatVente(String inputEtatVente) throws BusinessException
	{
		return this.articleDAO.selectAllByEtatVente(inputEtatVente);
	}
	
	
	 private void validerArticle(Articles article) throws BusinessException{
		 BusinessException businessException = new BusinessException();
	    
	        
			if(article.getNomArticle()==null || article.getNomArticle().trim().length()>30) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_ERREUR);
=======
	public void miseAJourEtatVente() throws BusinessException {
		ArticleManager articleManager = new ArticleManager();
		List <Articles> listArticle = new ArrayList<>();
		LocalDate localDate = LocalDate.now();
		
		listArticle = articleManager.selectionnerListArticleSelonEtatVente("NON DEBUTEE");
		for (Articles articles : listArticle) {
			
			if(articles.getDateDebutEncheres().isEqual(localDate) ) {
				
				articles.setEtatVente("EN COURS");
				articleManager.miseAjourArticle(articles);
>>>>>>> branch 'main' of https://github.com/Mahlckov/EniEncheres.git
			}
			
		}
		
		listArticle = articleManager.selectionnerListArticleSelonEtatVente("EN COURS");
		for (Articles articles : listArticle) {
			
			if(articles.getDateFinEncheres().isEqual(localDate) ) {
				
				articles.setEtatVente("TERMINEE");
				articleManager.miseAjourArticle(articles);
			}
			
		}
		
		
		
		
	}

	public List<Articles> Filtrage(HttpServletRequest request) throws BusinessException {
		HttpSession session = request.getSession();
		List<Articles> listArticles = new ArrayList<>();
		List<Articles> listArticlesFiltree = new ArrayList<>();
		List<Articles> listRecherche = new ArrayList<>();

		ArticleManager articleManager = new ArticleManager();
		Categorie categorie = null;
		// RECUPERATION DES PARAMETRES DANS L'URL
		String search = request.getParameter("search");
		String cat = request.getParameter("categorie");
		String encheresOuvertes = request.getParameter("encheresOuvertes");
		String mesEnchereEnCours = request.getParameter("enchereEnCours");
		String mesEnchereRemportees = request.getParameter("mesEnchereRemportees");

		String mesVentesEnCours = request.getParameter("mesVentesEnCours");
		String ventesNonDebutees = request.getParameter("ventesNonDebutees");
		String ventesTerminees = request.getParameter("ventesTerminees");

		// RECUPERATION DE LA LISTE SELON CATEGORIE

		if (cat.equals("Toutes")) {
			listArticles = articleManager.selectionnerListArticles();
		}

		else {
			categorie = new Categorie(cat);
			listArticles = articleManager.selectionnerListArticleSelonCategorie(categorie);
		}

		// SELECTION DES ARTICLES DANS LA LISTE OBTENUE PAR CATEGORIE DONT L ETAT VENTE
		// EST EN COURS

		if (encheresOuvertes != null && encheresOuvertes.equals("true")) {
			for (Articles articles : listArticles) {

				if (articles.getEtatVente().equals("EN COURS")) {
					listArticlesFiltree.add(articles);
				}

			}

		}

		// SELECTION DES ARTICLES DANS LA LISTE OBTENUE PAR CATEGORIE DONT L ETAT VENTE
		// EST 'EN COURS' ET noACHETEUR = noUTILISATEUR EN SESSION

		if (mesEnchereEnCours!=null && mesEnchereEnCours.equals("true")) {
			for (Articles articles : listArticles) {

				if (articles.getEtatVente().equals("EN COURS") && articles.getNoAcheteur()
						.getNoUtilisateur() == ((int) session.getAttribute("noUtilisateur"))) {
					listArticlesFiltree.add(articles);
				}

			}

		}

		// SELECTION DES ARTICLES DANS LA LISTE OBTENUE PAR CATEGORIE DONT L ETAT VENTE
		// EST 'TERMINEE' ET noACHETEUR = noUTILISATEUR EN SESSION

		if (mesEnchereRemportees!= null && mesEnchereRemportees.equals("true")) {
			for (Articles articles : listArticles) {

				if (articles.getEtatVente().equals("TERMINEE") && articles.getNoAcheteur()
						.getNoUtilisateur() == ((int) session.getAttribute("noUtilisateur"))) {
					listArticlesFiltree.add(articles);
				}

			}

		}
		///////////////////////////////////////////////////////////////////////////////////////////
		// SELECTION DES ARTICLES DANS LA LISTE OBTENUE PAR CATEGORIE DONT L ETAT VENTE
		// EST 'EN COURS' ET noVENDEUR = noUTILISATEUR EN SESSION

		if (mesVentesEnCours!=null && mesVentesEnCours.equals("true")) {
			for (Articles articles : listArticles) {

				if (articles.getEtatVente().equals("EN COURS") && articles.getNoVendeur()
						.getNoUtilisateur() == ((int) session.getAttribute("noUtilisateur"))) {
					listArticlesFiltree.add(articles);
				}

			}

		}

		// SELECTION DES ARTICLES DANS LA LISTE OBTENUE PAR CATEGORIE DONT L ETAT VENTE
		// EST 'NON DEBUTEE' ET noVENDEUR = noUTILISATEUR EN SESSION

		if (ventesNonDebutees!=null && ventesNonDebutees.equals("true")) {
			for (Articles articles : listArticles) {

				if (articles.getEtatVente().equals("NON DEBUTEE") && articles.getNoVendeur()
						.getNoUtilisateur() == ((int) session.getAttribute("noUtilisateur"))) {
					listArticlesFiltree.add(articles);
				}

			}

		}

		// SELECTION DES ARTICLES DANS LA LISTE OBTENUE PAR CATEGORIE DONT L ETAT VENTE
		// EST 'NON DEBUTEE' ET noVENDEUR = noUTILISATEUR EN SESSION

		if (ventesTerminees!=null && ventesTerminees.equals("true")) {
			for (Articles articles : listArticles) {

				if (articles.getEtatVente().equals("TERMINEE") && articles.getNoVendeur()
						.getNoUtilisateur() == ((int) session.getAttribute("noUtilisateur"))) {
					listArticlesFiltree.add(articles);
				}

			}}

			// SELECTION DES ARTICLES DANS LA LISTE FILTREE DONT LE NOM D'ARTICLE EST EGALE
			// A L INPUT DE LA BARRE DE RECHERCHE

			if (search != null) {

				for (

				Articles articles : listArticlesFiltree) {

					if (articles.getNomArticle().contains(search)) {
						listRecherche.add(articles);
					}
				}
				listArticlesFiltree = listRecherche;
			}

		
		return listArticlesFiltree;

	}

	private void validerArticle(Articles article) throws BusinessException {
		BusinessException businessException = new BusinessException();

		if (article.getNomArticle() == null || article.getNomArticle().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_ERREUR);
		}
		if (article.getDescription() == null || article.getDescription().trim().isEmpty()
				|| article.getDescription().trim().length() > 300) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DESCRIPTION_ERREUR);
		}
		if (article.getEtatVente() == null || article.getEtatVente().trim().isEmpty()
				|| article.getDescription().trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_ETAT_ERREUR);
		}
		if (article.getMiseAprix() < 0 || article.getMiseAprix() == 0 || article.getPrixVente() < 0
				|| article.getPrixVente() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_PRIX_ERREUR);
		}
		if ((article.getDateDebutEncheres() == null && article.getDateDebutEncheres().isBefore(LocalDate.now()))
				|| (article.getDateFinEncheres() == null && article.getDateFinEncheres().isBefore(LocalDate.now()))) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ARTICLE_ERREUR);
		}
	}

}
