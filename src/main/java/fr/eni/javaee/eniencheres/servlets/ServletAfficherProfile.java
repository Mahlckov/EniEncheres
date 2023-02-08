package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
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
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

@WebServlet("/MonProfil")

public class ServletAfficherProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int noUtilisateur = 0;

		HttpSession session = request.getSession();
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		if(request.getParameter("noVendeur")!=null) {
			try {
				Utilisateur user = utilisateurManager.selectUserByPseudo(request.getParameter("noVendeur"));
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/JSP/profile.jsp").forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}
		else {
			
			if (session.getAttribute("noUtilisateur") != null) {
				noUtilisateur = (int) session.getAttribute("noUtilisateur");
	
				
				String test = request.getParameter("delete");
				try {
					Utilisateur user = utilisateurManager.selectUser(noUtilisateur);
					request.setAttribute("user", user);
					
				} catch (BusinessException e) {
					e.printStackTrace();
	
				}
				
				if(request.getParameter("delete")!=null) {
					try {
						utilisateurManager.deleteUser(noUtilisateur);
					} catch (BusinessException e) {
						e.printStackTrace();
					}
					session.invalidate();
					response.sendRedirect("Accueil");
				}
				else if(request.getParameter("Modifier")!=null) {//Parametre = dans URL
					request.setAttribute("Modifier", "yes"); //on renvoie une nouvelle requête de notre parametre modifié
					request.getRequestDispatcher("/WEB-INF/JSP/profile.jsp").forward(request, response);
				}
				else if(request.getParameter("delete")==null && request.getParameter("Modifier")==null) {
					request.getRequestDispatcher("/WEB-INF/JSP/profile.jsp").forward(request, response);
				}
				
	
			} else {
				request.setAttribute("erreurNonConnecte",
						"Vous devez être connecté pour pouvoir accéder à cette fonctionnalité.");
				response.sendRedirect("Connexion");		
			}
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 UtilisateurManager utilisateurManager = new UtilisateurManager();
		 Utilisateur utilisateur = new Utilisateur();
		 Utilisateur utilisateurAvantModif = new Utilisateur();
		 
		 String mail = request.getParameter("email");
		 
		 try {
			utilisateurAvantModif = utilisateurManager.selectUser((int)session.getAttribute("noUtilisateur"));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		 
		 List<String> errorList = new ArrayList();
		 errorList = utilisateurManager.verifierFormulaireModification(request);
		 
		 if(errorList.isEmpty()) {
			utilisateur.setNoUtilisateur((int)session.getAttribute("noUtilisateur"));
			utilisateur.setPseudo(request.getParameter("pseudo"));
			utilisateur.setPrenom(request.getParameter("prenom"));
			utilisateur.setNom(request.getParameter("nom"));
			utilisateur.setEmail(request.getParameter("email"));
			utilisateur.setTelephone(request.getParameter("telephone"));
			utilisateur.setRue(request.getParameter("rue"));
			utilisateur.setCodePostal(request.getParameter("codePostal"));
			utilisateur.setVille(request.getParameter("ville"));
			utilisateur.setMotDePasse(request.getParameter("newPassword"));
			utilisateur.setAdministrateur(utilisateurAvantModif.getAdministrateur());
			utilisateur.setCredit(utilisateurAvantModif.getCredit());

			try {
				utilisateurManager.updateUser(utilisateur);
				response.sendRedirect("MonProfil");
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		 }
		 else{
			 request.setAttribute("errorList", errorList);
			 request.setAttribute("user", utilisateurAvantModif);
			 request.getRequestDispatcher("/WEB-INF/JSP/profile.jsp").forward(request, response);
		 }
	}
}
