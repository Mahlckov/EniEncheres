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
@WebServlet("/ServletPageInscription")
public class ServletPageInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPageInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			
			
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
			
			
			try {
				
			manager.insertUser(utilisateur);
			
						
	        HttpSession session = request.getSession();

	        session.setAttribute("identifiant",pseudo );
	        
	        
	    	RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);	
			
				} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		
		else {request.setAttribute("errorList", errorList);
		RequestDispatcher rd = request.getRequestDispatcher("/Inscription");
		rd.forward(request, response);		}
		
		
		
		
	

}
	
}
