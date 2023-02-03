package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

@WebServlet("/MonProfil")

public class ServletAfficherProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int noUtilisateur = 0;

		HttpSession session = request.getSession();

		if (session.getAttribute("noUtilisateur") != null) {
			noUtilisateur = (int) session.getAttribute("noUtilisateur");

			UtilisateurManager utilisateurManager = new UtilisateurManager();
			try {
				Utilisateur user = utilisateurManager.selectUser(noUtilisateur);
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/JSP/profile.jsp").forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();

			}

		} else
			request.setAttribute("erreurNonConnecte",
					"Vous devez être connecté pour pouvoir accéder à cette fonctionnalité.");

		response.sendRedirect("Connexion");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
