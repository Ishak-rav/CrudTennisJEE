package com.mycompany.servlets.epreuve;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.EpreuveDao;
import com.mycompany.dao.EpreuveDaoImpl;
import com.mycompany.utils.SessionUtils;

/**
 * Servlet implementation class SupprimerEpreuve
 */
@WebServlet("/supprimerepreuve")
public class SupprimerEpreuve extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EpreuveDao epreuveDao;
    private Long idl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerEpreuve() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		DaoFactory daoFactory = DaoFactory.getInstance();
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
		epreuveDao.lecture(Long.valueOf(request.getParameter("id")));
		String id = request.getParameter("id");
		idl = Long.parseLong(id);
		
		epreuveDao.supprimer(idl);
		response.sendRedirect("/AppJoueur/listepreuve");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
