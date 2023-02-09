package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.ArticleManager;
import fr.eni.javaee.eniencheres.bll.RetraitManager;
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.bo.Retrait;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletPageNouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletPageNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPageNouvelleVente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// SI VOLONTE DE SUPPRIMER UNE VENTE AVANT QU'ELLE DEBUTE DEPUIS JSP DETAILVENTE

		if (request.getParameter("delete") != null) {
			ArticleManager articleManager = new ArticleManager();
			int noArticle = Integer.parseInt(request.getParameter("noArticle"));
			try {
				articleManager.supprimerArticle(noArticle);

				response.sendRedirect("Accueil");
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// SI VOLONTE DE MODIFIER UNE VENTE AVANT QU'ELLE DEBUTE DEPUIS JSP DETAILVENTE

		else if (request.getParameter("modifier") != null) {
			ArticleManager articleManager = new ArticleManager();
			RetraitManager retraitManager = new RetraitManager();
			int noArticle = Integer.parseInt(request.getParameter("noArticle")); // RECUP noARTICLE DE L'URL
			Articles article = new Articles();
			Retrait retrait = null;
			try {
				article = articleManager.selectionnerArticle(noArticle);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			try {
				retrait = retraitManager.selectRetraitById(noArticle);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("article", article);
			request.setAttribute("retrait", retrait);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
			rd.forward(request, response);

		}

		// PAR DEFAUT QUAND ON ARRIVE SUR LA SERVLET POUR UNE NOUVELLE VENTE

		else {

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleManager articleManager = new ArticleManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		// RECUPERATION DES PARAMETRES DU FORMULAIRE

		String nomArticle = request.getParameter("nomArticle");
		String descriptionArticle = request.getParameter("descriptionArticle");
		String categorieForm = request.getParameter("categorie");

		Categorie categorie = new Categorie(categorieForm);

		String Prix = request.getParameter("miseAPrix");
		Integer miseAPrix = Integer.parseInt(Prix);
		String DebutEnchere = request.getParameter("dateDebutEnchere");
		String FinEnchere = request.getParameter("dateFinEnchere");

		LocalDate dateDebutEnchere = LocalDate.parse(DebutEnchere);
		LocalDate dateFinEnchere = LocalDate.parse(FinEnchere);

		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String etat_Vente = null;

		LocalDate localDate = LocalDate.now();

		if (localDate.isBefore(dateDebutEnchere)) {

			etat_Vente = "NON DEBUTEE";

		}

		else {
			etat_Vente = "EN COURS";
		}

		// RECUPERATION DE l'IDENTIFIANT EN COURS DE SESSION

		HttpSession session = request.getSession();

		int noUtilisateur = 0;

		noUtilisateur = (int) session.getAttribute("noUtilisateur");

		Utilisateur utilisateur = new Utilisateur();

		if (noUtilisateur == 0) {
			response.sendRedirect("Connexion");
		}
		;

		try {
			utilisateur = utilisateurManager.selectUser(noUtilisateur);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// CREATION DE L ARTICLE

		Articles article = new Articles(nomArticle, descriptionArticle, dateDebutEnchere, dateFinEnchere, miseAPrix,
				miseAPrix, etat_Vente, categorie, utilisateur, null);
		
		
		//VERIFICATION DE L ARTICLE
		try {
			articleManager.validerArticle(article);
			
			
		} catch (BusinessException e1) {
			
			
			request.setAttribute("errorList", e1.getListeCodesErreur());
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
			rd.forward(request, response);		}
		

		
	//////////////////////////////////////////////////////////////////////////////	
		//SI MISE A JOUR D UN ARTICLE EXISTANT DONT LA VENTE N A PAS DEBUTEE
		if (request.getParameter("articleModif") != null) {
			
			article.setNoArticle(Integer.parseInt(request.getParameter("articleModif")));
			try {
				articleManager.miseAjourArticle(article);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//CREATION DU RETRAIT
			if (rue == null | rue.isBlank()) {
				rue = utilisateur.getRue();
			}
			;
			if (codePostal == null | codePostal.isBlank()) {
				codePostal = utilisateur.getCodePostal();
			}
			if (ville == null | ville.isBlank()) {
				ville = utilisateur.getVille();
			}
			;

			Retrait retrait = new Retrait(article, rue, codePostal, ville);

			RetraitManager retraitManager = new RetraitManager();
			
			//MISE A JOUR DU RETRAIT
			
			try {
				retraitManager.miseAJourRetrait(retrait);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		
		//////////////////////////////////////////////////////////////////////////////////////
		//////////////// SI CREATION D UN NOUVEL ARTICLE  /////////////////////////////////
		
		else {

		// INSERTION DE L ARTICLE

		try {
			articleManager.ajouterArticle(article);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		// RECUPERATION DE l'ARTICLE AVEC SON ID

		try {
			article = articleManager.selectionnerArticle(article.getNoArticle());
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		// CREATION DU RETRAIT

		if (rue == null | rue.isBlank()) {
			rue = utilisateur.getRue();
		}
		;
		if (codePostal == null | codePostal.isBlank()) {
			codePostal = utilisateur.getCodePostal();
		}
		if (ville == null | ville.isBlank()) {
			ville = utilisateur.getVille();
		}
		;

		Retrait retrait = new Retrait(article, rue, codePostal, ville);

		RetraitManager retraitManager = new RetraitManager();

		try {
			retraitManager.ajouterRetrait(retrait);
		} catch (BusinessException e) {
			e.printStackTrace();
		}}
/////////////////////////////////////////////////////////////////
		// REDIRECTION ACCUEIL
		response.sendRedirect("Accueil");
	}
}
