package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;

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
 * Servlet implementation class ServletVenteGagnee
 */
@WebServlet("/ServletVenteGagnee")
public class ServletVenteGagnee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVenteGagnee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//page d'accueil connait l'idArticle
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));
		ArticleManager articleManager = new ArticleManager();
		try {
			Articles article = articleManager.selectionnerArticle(noArticle);
			request.setAttribute("article", article);

			request.getRequestDispatcher("/WEB-INF/JSP/VenteGagnee.jsp").forward(request, response);

			request.getRequestDispatcher("/WEB-INF/JSP/VenteGagnee.jsp").forward(request, response);

		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/VenteGagnee.jsp");
//		rd.forward(request, response);
	}

}
