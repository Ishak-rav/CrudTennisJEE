package com.mycompany.servlets.tournoi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.TournoiDao;
import com.mycompany.dao.TournoiDaoImpl;
import com.mycompany.utils.SessionUtils;

/**
 * Servlet implementation class ListTournoi
 */
@WebServlet("/listtournoi")
public class ListTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TournoiDao tournoiDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListTournoi() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		DaoFactory daoFactory = DaoFactory.getInstance();
		tournoiDao = new TournoiDaoImpl(daoFactory);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
		if (!SessionUtils.isUserLoggedIn(request)) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
		request.setAttribute("tournois", tournoiDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listTournoi.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		if (session.getAttribute("connectedUser") == null) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
		if (request.getParameter("action1").equals("rechercher")) {
			String chaine = request.getParameter("txtSearch");
			request.setAttribute("tournois", tournoiDao.rechercher(chaine));
			this.getServletContext().getRequestDispatcher("/WEB-INF/listTournoi.jsp").forward(request, response);
		} else if (request.getParameter("action1").equals("deconnexion")) {
			session.setAttribute("connectedUser", null);
			response.sendRedirect("/AppJoueur/login");
			return;
		}
	}

}
