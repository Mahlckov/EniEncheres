package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.ArticleManager;
import fr.eni.javaee.eniencheres.bll.EnchereManager;
import fr.eni.javaee.eniencheres.bll.RetraitManager;
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.Articles;
import fr.eni.javaee.eniencheres.bo.Encheres;
import fr.eni.javaee.eniencheres.bo.Retrait;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/DetailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Retrait retrait = null;

		
		//SI UTILISATEUR NON CONNECTE REDIRECTION VERS PAGE CONNEXION
		
		if(session.getAttribute("noUtilisateur")==null) {
			
			response.sendRedirect("Connexion");
			
		} else {
		
		//RECUPERATION DE L'ID ARTICLE DANS L'URL, CREATION DE L'ARTICLE EN PARAMETRE ET DISPATCH VERS URL
		
		ArticleManager articleManager = new ArticleManager();
		Articles article = new Articles();
		
		if(request.getParameter("id")!=null) {
			
			int noArticle = Integer.parseInt(request.getParameter("id"));	
			
		try {
			article =  articleManager.selectionnerArticle(noArticle);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		//RECUPERATION DU RETRAIT
		RetraitManager retraitManager= new RetraitManager();

		try {
			retrait = retraitManager.selectRetraitById(article.getNoArticle());
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		}
		request.setAttribute("article", article);
		request.setAttribute("retrait", retrait);

		
		request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp").forward(request, response);}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager enchereManager = new EnchereManager();

		UtilisateurManager utilisateurManager= new UtilisateurManager();
		
		ArticleManager articleManager = new ArticleManager();
				
		HttpSession session = request.getSession();
		
		Retrait retrait = null;

		
		//RECUPERATION DU MONTANT DE L ENCHERE, DU NO DE L'ACHETEUR ( = utilisateur en session) ET DU NO ARTICLE
		int noUtilisateur = (int) session.getAttribute("noUtilisateur");
		
		int propositionEnchere= Integer.parseInt(request.getParameter("propositionEnchere"));
		
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		

		
		//RECUPERATION DE L'UTILISATEUR EN COURS
		Utilisateur noAcheteur = new Utilisateur();
		
		try {
			noAcheteur = utilisateurManager.selectUser(noUtilisateur);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		//RECUPERATION DE L'ARTICLE
		
		Articles article = new Articles();
		
		try {
			article = articleManager.selectionnerArticle(noArticle);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		Articles articleAvantModifications = article;
		
		//RECUPERATION DU RETRAIT
		
		RetraitManager retraitManager= new RetraitManager();

		try {
			retrait = retraitManager.selectRetraitById(article.getNoArticle());
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//CREATION LOCALDATE
		
		LocalDate localDate;
		
		localDate = LocalDate.now();
		
		//CREATION ENCHERE
		BusinessException businessException = null;
		Encheres enchere = new Encheres(noAcheteur,article,localDate,propositionEnchere);
		try {
			 businessException = enchereManager.validerEncheres(enchere);
			
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		if(businessException.hasErreurs()) {
			request.setAttribute("listError", businessException.getListeCodesErreur());
			request.setAttribute("article", articleAvantModifications);
			request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp").forward(request, response);
			
		}
		
		try {
			//MODIF NOMBRE DE CREDITS DE L'ACHETEUR
			utilisateurManager.retirerCredit(article.getNoArticle(), noAcheteur.getNoUtilisateur(), enchere);
			
				enchereManager.insertEnchere(enchere);
				//S'EXECUTE UNIQUEMENT SI DEJA UNE PREMIERE ENCHERE
				if(article.getNoAcheteur()!=null) {
					utilisateurManager.restituerCredit(article.getPrixVente(),article.getNoAcheteur().getNoUtilisateur());
				};
				
				
				//SI INSERT REUSSI, MISE A JOUR DE l'ARTICLE(NO ACHETEUR ET PRIX VENTE)
				article.setNoAcheteur(noAcheteur);
				article.setPrixVente(enchere.getMontant_enchere());
				articleManager.miseAjourArticle(article);

			
			//REDIRECTION VERS LA PAGE DE VENTE ACTUALISEE
			try {
				article =  articleManager.selectionnerArticle(noArticle);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			request.setAttribute("article", article);
			request.setAttribute("retrait", retrait);

			
			request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp").forward(request, response);
			
			//SI ECHEC INSERT REDIRECTION VERS LA PAGE DE VENTE
		} catch (BusinessException e) {
			request.setAttribute("article", articleAvantModifications);
			request.getRequestDispatcher("/WEB-INF/JSP/DetailVente.jsp").forward(request, response);

			e.printStackTrace();
		}
		
	}

}
