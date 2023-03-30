package com.mycompany.servlets.tournoi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.beans.Tournoi;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.TournoiDao;
import com.mycompany.dao.TournoiDaoImpl;
import com.mycompany.utils.SessionUtils;

/**
 * Servlet implementation class ModifierTournoi
 */
@WebServlet("/modifiertournoi")
public class ModifierTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TournoiDao tournoiDao;
	private Long idl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierTournoi() {
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
		HttpSession session = request.getSession();
        Boolean result = (Boolean) session.getAttribute("admin");
        session.setAttribute("admin", result);
        
		if (!SessionUtils.isUserLoggedIn(request) || !result) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
		tournoiDao.lecture(Long.valueOf(request.getParameter("id")));
		String id = request.getParameter("id");
		idl = Long.parseLong(id);
		Tournoi tournoi = tournoiDao.lecture(idl);
		request.setAttribute("tournoi", tournoi);
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierTournoi.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom = request.getParameter("txtNom");
		String code = request.getParameter("txtCode");
		
		Tournoi newTournoi = new Tournoi(idl, nom, code);
		tournoiDao.modifier(newTournoi);
		request.setAttribute("tournois", tournoiDao.lister());
		response.sendRedirect("/AppJoueur/listtournoi");
	}

}
