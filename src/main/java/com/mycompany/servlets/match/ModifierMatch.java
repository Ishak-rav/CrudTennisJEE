package com.mycompany.servlets.match;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.beans.Match;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.EpreuveDao;
import com.mycompany.dao.EpreuveDaoImpl;
import com.mycompany.dao.JoueurDao;
import com.mycompany.dao.JoueurDaoImpl;
import com.mycompany.dao.MatchDao;
import com.mycompany.dao.MatchDaoImpl;
import com.mycompany.utils.SessionUtils;

/**
 * Servlet implementation class ModifierMatch
 */
@WebServlet("/modifiermatch")
public class ModifierMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MatchDao matchDao;
	private JoueurDao joueurDao;
	private EpreuveDao epreuveDao;
	private Long idl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierMatch() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		DaoFactory daoFactory = DaoFactory.getInstance();
		matchDao = new MatchDaoImpl(daoFactory);
		joueurDao = new JoueurDaoImpl(daoFactory);
		epreuveDao = new EpreuveDaoImpl(daoFactory);
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
		matchDao.lecture(Long.valueOf(request.getParameter("id")));
		String id = request.getParameter("id");
		idl = Long.parseLong(id);
		request.setAttribute("match", matchDao.lecture(idl));
		request.setAttribute("epreuves", epreuveDao.lister());
		request.setAttribute("joueurs", joueurDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierMatch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idEpreuve = Long.parseLong(request.getParameter("opEpreuve"));
		Long idVainqueur = Long.parseLong(request.getParameter("opVainqueur"));
		Long idFinaliste = Long.parseLong(request.getParameter("opFinaliste"));
		System.out.println(idl);
		Match newMatch = new Match(idl, idEpreuve, idVainqueur, idFinaliste);
		matchDao.modifier(newMatch);
		request.setAttribute("matchs", matchDao.lister());
		response.sendRedirect("/AppJoueur/listmatch");
	}
}
