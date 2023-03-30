package com.mycompany.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.beans.User;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.UserDaoImpl;
import com.mycompany.utils.SessionUtils;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userdaoimpl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		DaoFactory daoFactory = DaoFactory.getInstance();
		userdaoimpl = new UserDaoImpl(daoFactory);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("txtLogin");
		String password = request.getParameter("txtPassword");
		User connectedUser = null;
		
		//System.out.println(HashClass.sha1(password));
        connectedUser = userdaoimpl.isValidLogin(login, password);
        if (connectedUser!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("connectedUser", connectedUser);
            int profil = connectedUser.getProfil();
            boolean result = SessionUtils.admin(connectedUser);
            session.setAttribute("admin", result);
            response.sendRedirect(request.getContextPath() + "/listjoueur");
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        }

	}

}
