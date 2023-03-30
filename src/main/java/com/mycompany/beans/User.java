package com.mycompany.beans;

public class User {
	
	private int id, profil;
	private String login, password;
	
	public User(int id, int profil, String login, String password) {
		super();
		this.id = id;
		this.profil = profil;
		this.login = login;
		this.password = password;
	}

	public User(int profil, String login, String password) {
		super();
		this.profil = profil;
		this.login = login;
		this.password = password;
	}

	public User() {
		super();
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public int getProfil() { return profil; }

	public void setProfil(int profil) { this.profil = profil; }

	public String getLogin() { return login; }

	public void setLogin(String login) { this.login = login; }

	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }
}
