package com.mycompany.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mycompany.beans.User;

public class SessionUtils {
	public static boolean isUserLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		return (session != null && session.getAttribute("connectedUser") != null);
	}
	
	public static boolean admin(User user) {
		if (user.getProfil() == 1) {
			return true;
		} else {
			return false;
		}
	}
}
