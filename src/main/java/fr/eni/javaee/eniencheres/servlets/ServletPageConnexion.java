package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletPageConnexion
 */
@WebServlet("/ServletPageConnexion")
public class ServletPageConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurManager manager = new UtilisateurManager();

		boolean connexionAutorisee = manager.seConnecter(request);

		if (connexionAutorisee == true) {
			
			String identifiant = request.getParameter("pseudo");

			HttpSession session = request.getSession();

			session.setAttribute("identifiant", identifiant);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);

		}
		
		else {
			String messageConnexion = "'Identifiant / Mot de passe'inconnus ou incorrects.";
			
			request.setAttribute("messageConnexion", messageConnexion);
			RequestDispatcher rd = request.getRequestDispatcher("/Connexion");
			rd.forward(request, response);
	}

}
}
