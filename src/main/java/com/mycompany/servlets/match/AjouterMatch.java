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
 * Servlet implementation class AjouterMatch
 */
@WebServlet("/ajoutermatch")
public class AjouterMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MatchDao matchDao; 
	private JoueurDao joueurDao;
	private EpreuveDao epreuveDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterMatch() {
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

		request.setAttribute("joueurs", joueurDao.lister());
		request.setAttribute("epreuves", epreuveDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterMatch.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long idEpreuve = Long.parseLong(request.getParameter("opEpreuve"));
		Long idVainqueur = Long.parseLong(request.getParameter("opVainqueur"));
		Long idFinaliste = Long.parseLong(request.getParameter("opFinaliste"));
		
		Match newMatch = new Match(idEpreuve, idVainqueur, idFinaliste);
		matchDao.ajouter(newMatch);
		
		request.setAttribute("matchs", matchDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listMatch.jsp").forward(request, response);
	}

}
