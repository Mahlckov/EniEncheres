package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.Utilisateur;
import fr.eni.javaee.eniencheres.BusinessException;

/**
 * Servlet implementation class ServletPageInscription
 */
@WebServlet("/Inscription")
public class ServletPageInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtilisateurManager manager = new UtilisateurManager();
		
		List<String> errorList = manager.verifierFormulaireInscription(request);
		
		
		if(errorList.isEmpty()) {
			
			
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("code_postal");
			String ville = request.getParameter("ville");
			String motDePasse= request.getParameter("motDePasse");
			int credit = 0;
			boolean administrateur = false;
			
			//n'a pas d'id car c'est la bdd qui le crée
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
			
			
			try {
				
			manager.insertUser(utilisateur);
			
						
				} catch (BusinessException e) {
				e.printStackTrace();
			}
			
			try {
				//l'utilisateur créé n'a pas d'id dans la servlet donc on refait un select pour avoir l'utilisateur avec un id que la bdd a attribué
				utilisateur = manager.selectUserByPseudo(request.getParameter("pseudo"));
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			//on récupère l'id par un intermédiaire
			int noUtilisateur = utilisateur.getNoUtilisateur();

			HttpSession session = request.getSession();
			
			session.setAttribute("noUtilisateur", noUtilisateur);
	        

	        response.sendRedirect("Accueil");
		
		}
		
		else {request.setAttribute("errorList", errorList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
		rd.forward(request, response);		}
		
		
		
		
	

}
	
}
