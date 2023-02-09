package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.ArticleManager;
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletPageAccueil
 */
@WebServlet("/Accueil")
public class ServletPageAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleManager articleManager = new ArticleManager();
		UtilisateurManager userManager = new UtilisateurManager();
		List<Articles> listArticle = new ArrayList();
		List<Articles> listRecherche = new ArrayList<>();
		HttpSession session = request.getSession();

		
		
		//SI COOKIES RECUPERATION DU LOGIN ET CONNEXION

		if (request.getCookies() != null && session.getAttribute("noUtilisateur")==null) {
			String identifiant = null;
			String motDePasse = null;
			Cookie[] Cookies = request.getCookies();

			for (Cookie cookies : Cookies) {

				if (cookies.getName().equals("EniCookie1")) {
					identifiant = cookies.getValue();
				}
				if (cookies.getName().equals("EniCookie2")) {
					motDePasse = cookies.getValue();
				}

			}




			if (identifiant != null && motDePasse != null) {
				boolean connectionAutorise = userManager.seConnecterAvecCookie(identifiant, motDePasse);

				if (connectionAutorise == true) {

					Utilisateur utilisateur = new Utilisateur();

					if (identifiant.contains("@")) {

						try {
							utilisateur = userManager.selectUserByMail(identifiant);

						} catch (BusinessException e) {
							e.printStackTrace();
						}

					} else {
						try {
							utilisateur = userManager.selectUserByPseudo(identifiant);

						} catch (BusinessException e) {
							e.printStackTrace();
						}
					}

					int noUtilisateur = utilisateur.getNoUtilisateur();


					session.setAttribute("noUtilisateur", noUtilisateur);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
					rd.forward(request, response);				}
			}

		}

		// MISE A JOUR DANS LA BASE DE DONNEE DES ETATS DE VENTE EN FONCTION DE LA DATE

		try {
			articleManager.miseAJourEtatVente();
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// PAR DEFAUT => PAS DE RECHERCHE, PAS DE CATEGORIE SELECTIONNE TOUS LES
		// ARTICLES \\

		// RECUPERATION PARAMETRE DE FILTRE PAR CATEGORIE
		String cat = null;
		Categorie categorie = null;

		if (request.getParameter("encheresOuvertes") != null | request.getParameter("mesEncheresEnCours") != null
				| request.getParameter("mesEncheresRemportees") != null
				| request.getParameter("mesVentesEnCours") != null | request.getParameter("ventesNonDebutees") != null
				| request.getParameter("ventesTerminees") != null) {

			try {
				listArticle = articleManager.Filtrage(request);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// SI PARAMETRE CATEGORIE EST EXISTANT MAIS PAS D'AUTRES

		else if (request.getParameter("categorie") != null) {
			cat = request.getParameter("categorie");
			categorie = new Categorie(cat);

			// SI PARAMETRE CATEGORIE = TOUTES

			if (cat.equals("Toutes")) {
				try {

					listArticle = articleManager.selectionnerListArticles();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				///// SI RECHERCHE EFFECTUE DANS LA BARRE DE RECHERCHE

				if (request.getParameter("search") != null) {
					
					String alreadySearched = request.getParameter("search");

					request.setAttribute("alreadySearched", alreadySearched);

					for (

					Articles articles : listArticle) {

						if (articles.getNomArticle().contains(request.getParameter("search"))) {
							listRecherche.add(articles);
						}
					}

					listArticle = listRecherche;
				}
			}

			// SI PARAMETRE CATEGORIE = TYPES CATEGORIES

			else {

				try {
					listArticle = articleManager.selectionnerListArticleSelonCategorie(categorie);

				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				///// SI RECHERCHE EFFECTUE DANS LA BARRE DE RECHERCHE

				if (request.getParameter("search") != null) {

					String alreadySearched = request.getParameter("search");

					request.setAttribute("alreadySearched", alreadySearched);

					for (

					Articles articles : listArticle) {

						if (articles.getNomArticle().contains(request.getParameter("search"))) {
							listRecherche.add(articles);
						}
					}

					listArticle = listRecherche;
				}

			}
		}

		// SI PAS DE PARAMETRE CATEGORIE => PAR DEFAUT "TOUTES"

		else {

			try {

				listArticle = articleManager.selectionnerListArticles();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		request.setAttribute("listeArticleEnCours", listArticle);
		request.setAttribute("categorie", cat);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
