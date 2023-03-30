package com.mycompany.servlets.match;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.MatchDao;
import com.mycompany.dao.MatchDaoImpl;
import com.mycompany.utils.SessionUtils;

/**
 * Servlet implementation class ListMatch
 */
@WebServlet("/listmatch")
public class ListMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MatchDao matchDao; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListMatch() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		DaoFactory daoFactory = DaoFactory.getInstance();
		matchDao = new MatchDaoImpl(daoFactory);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
		if (!SessionUtils.isUserLoggedIn(request)) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
		request.setAttribute("matchs", matchDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listMatch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("connectedUser") == null) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
		if (request.getParameter("action1").equals("rechercher")) {
			String chaine = request.getParameter("txtSearch");
			request.setAttribute("matchs", matchDao.rechercher(chaine));
			this.getServletContext().getRequestDispatcher("/WEB-INF/listMatch.jsp").forward(request, response);		
		} else if (request.getParameter("action1").equals("deconnexion")) {
			session.setAttribute("connectedUser", null);
			response.sendRedirect("/AppJoueur/login");
			return;
		}
	}
}
