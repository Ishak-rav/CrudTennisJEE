package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mycompany.beans.User;
import com.mycompany.utils.HashClass;

public class UserDaoImpl {

	private DaoFactory daoFactory;

	public UserDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}
	

	public User isValidLogin(String login, String password) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        password = HashClass.sha1(password);
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM connexion WHERE login=? AND password=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next()) {
            	String pFName = rs.getString("profil");
            	System.out.println(pFName);
            	return new User(
            			rs.getInt("id"),
            			rs.getInt("profil"),
            			rs.getString("password"),
            			rs.getString("login")
            	);
            } else {
            	return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}
