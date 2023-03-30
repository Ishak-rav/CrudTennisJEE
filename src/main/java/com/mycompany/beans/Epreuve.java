package com.mycompany.beans;

public class Epreuve {
	
	Long id, id_tournoi, annee;
	String type, nom, code;
	
	public Epreuve(Long id, Long id_tournoi, Long annee, String type, String nom, String code) {
		super();
		this.id = id;
		this.id_tournoi = id_tournoi;
		this.annee = annee;
		this.type = type;
		this.nom = nom;
		this.code = code;
	}

	public Epreuve(Long id, Long id_tournoi, Long annee, String type) {
		super();
		this.id = id;
		this.id_tournoi = id_tournoi;
		this.annee = annee;
		this.type = type;
	}
	
	public Epreuve(Long id_tournoi, Long annee, String type) {
		super();
		this.id_tournoi = id_tournoi;
		this.annee = annee;
		this.type = type;
	}
	
	public Epreuve() {
		super();
	}

	public String getNom() { return nom; }

	public void setNom(String nom) { this.nom = nom; }

	public String getCode() { return code; }

	public void setCode(String code) { this.code = code; }

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public Long getId_tournoi() { return id_tournoi; }

	public void setId_tournoi(Long id_tournoi) { this.id_tournoi = id_tournoi; }

	public Long getAnnee() { return annee; }

	public void setAnnee(Long annee) { this.annee = annee; }

	public String getType() { return type; }

	public void setType(String type) { this.type = type; }
}
