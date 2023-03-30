package com.mycompany.servlets.epreuve;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.beans.Epreuve;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.EpreuveDao;
import com.mycompany.dao.EpreuveDaoImpl;
import com.mycompany.dao.TournoiDao;
import com.mycompany.dao.TournoiDaoImpl;
import com.mycompany.utils.SessionUtils;

/**
 * Servlet implementation class ModifierEpreuve
 */
@WebServlet("/modifierepreuve")
public class ModifierEpreuve extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EpreuveDao epreuveDao;
    private TournoiDao tournoiDao;
    private Long idl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierEpreuve() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		DaoFactory daoFactory = DaoFactory.getInstance();
		epreuveDao = new EpreuveDaoImpl(daoFactory);
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
		epreuveDao.lecture(Long.valueOf(request.getParameter("id")));
		String id = request.getParameter("id");
		idl = Long.parseLong(id);
		request.setAttribute("epreuve", epreuveDao.lecture(idl));
		request.setAttribute("tournois", tournoiDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierEpreuve.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long annee = Long.parseLong(request.getParameter("txtAnnee"));
		String type = request.getParameter("opType");
		Long idTournoi = Long.parseLong(request.getParameter("opTournoi"));
		
		Epreuve newEpreuve = new Epreuve(idTournoi, annee, type);
		epreuveDao.modifier(newEpreuve);
		request.setAttribute("epreuves", epreuveDao.lister());
		response.sendRedirect("/AppJoueur/listepreuve");
	}

}
