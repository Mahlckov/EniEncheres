package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = new ArticleManager();
		UtilisateurManager utilisateurManager= new UtilisateurManager();
		
		//RECUPERATION DES PARAMETRES DU FORMULAIRE
		
		String nomArticle = request.getParameter("nomArticle");
		String descriptionArticle = request.getParameter("descriptionArticle");
		String categorieForm = request.getParameter("categorie");
		

		
		Categorie categorie = new Categorie(categorieForm);
		
		String Prix = request.getParameter("miseAPrix");
		int miseAPrix = Integer.parseInt(Prix);
		String DebutEnchere = request.getParameter("dateDebutEnchere");
		String FinEnchere = request.getParameter("dateFinEnchere");

			
		LocalDate dateDebutEnchere = LocalDate.parse(DebutEnchere);
		LocalDate dateFinEnchere = LocalDate.parse(FinEnchere);
		
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("codePostal");
		
		//RECUPERATION DE l'IDENTIFIANT EN COURS DE SESSION
		
		HttpSession session = request.getSession();	
		
		int noUtilisateur=0;


		noUtilisateur = (int) session.getAttribute("noUtilisateur");
		
		Utilisateur utilisateur = new Utilisateur();
		
		
		if (noUtilisateur==0){response.sendRedirect("Connexion");};
		
			
			try {
				utilisateur = utilisateurManager.selectUser(noUtilisateur);

			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	

		
		
	Articles article = new Articles(nomArticle,descriptionArticle,dateDebutEnchere,dateFinEnchere,miseAPrix,miseAPrix,"EN COURS",categorie,utilisateur,null);
	
	try {
		articleManager.ajouterArticle(article);
	} catch (BusinessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	}


