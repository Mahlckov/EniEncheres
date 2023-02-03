package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletPageConnexion
 */
@WebServlet("/Connexion")
public class ServletPageConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String identifiant = request.getParameter("identifiant");

		UtilisateurManager manager = new UtilisateurManager();

		boolean connexionAutorisee = manager.seConnecter(request);

		if (connexionAutorisee == true) {

			Utilisateur utilisateur = new Utilisateur();

			if (identifiant.contains("@")) {

				try {
					utilisateur = manager.selectUserByMail(identifiant);
				} catch (BusinessException e) {
					e.printStackTrace();
				}

			} else {
				try {
					utilisateur = manager.selectUserByPseudo(identifiant);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
			
			int noUtilisateur = utilisateur.getNoUtilisateur();

			HttpSession session = request.getSession();
			
			session.setAttribute("noUtilisateur", noUtilisateur);


			response.sendRedirect("Accueil");

		}

		else {
			String messageConnexion = "'Identifiant / Mot de passe'inconnus ou incorrects.";

			request.setAttribute("messageConnexion", messageConnexion);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Connexion.jsp");
			rd.forward(request, response);
		}

	}
}
