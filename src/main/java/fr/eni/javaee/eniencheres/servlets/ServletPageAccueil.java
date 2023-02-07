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

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.ArticleManager;
import fr.eni.javaee.eniencheres.bo.Articles;

/**
 * Servlet implementation class ServletPageAccueil
 */
@WebServlet("/Accueil")

public class ServletPageAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = new ArticleManager();
		List <Articles> listArticle = new ArrayList();		
		
		try {
			listArticle = articleManager.selectionnerListArticleSelonEtatVente("EN COURS");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("listeArticleEnCours", listArticle);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
