package com.mycompany.beans;

public class Match {
	Long id, id_epreuve, id_vainqueur, id_finaliste;
	String tournoi, type_epreuve, annee_epreuve, nom_vainqueur, nom_finaliste;

	public Match(Long id, Long id_epreuve, Long id_vainqueur, Long id_finaliste, String tournoi, String type_epreuve,
			String annee_epreuve, String nom_vainqueur, String nom_finaliste) {
		super();
		this.id = id;
		this.id_epreuve = id_epreuve;
		this.id_vainqueur = id_vainqueur;
		this.id_finaliste = id_finaliste;
		this.tournoi = tournoi;
		this.type_epreuve = type_epreuve;
		this.annee_epreuve = annee_epreuve;
		this.nom_vainqueur = nom_vainqueur;
		this.nom_finaliste = nom_finaliste;
	}

	public Match(Long id, String nom_vainqueur, String nom_finaliste) {
		super();
		this.id = id;
		this.nom_vainqueur = nom_vainqueur;
		this.nom_finaliste = nom_finaliste;
	}

	public Match(Long id, Long id_epreuve, Long id_vainqueur, Long id_finaliste) {
		super();
		this.id = id;
		this.id_epreuve = id_epreuve;
		this.id_vainqueur = id_vainqueur;
		this.id_finaliste = id_finaliste;
	}

	public Match(Long id_epreuve, Long id_vainqueur, Long id_finaliste) {
		super();
		this.id_epreuve = id_epreuve;
		this.id_vainqueur = id_vainqueur;
		this.id_finaliste = id_finaliste;
	}

	public Match() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_epreuve() {
		return id_epreuve;
	}

	public void setId_epreuve(Long id_epreuve) {
		this.id_epreuve = id_epreuve;
	}

	public Long getId_vainqueur() {
		return id_vainqueur;
	}

	public void setId_vainqueur(Long id_vainqueur) {
		this.id_vainqueur = id_vainqueur;
	}

	public Long getId_finaliste() {
		return id_finaliste;
	}

	public void setId_finaliste(Long id_finaliste) {
		this.id_finaliste = id_finaliste;
	}
	public String getTournoi() {
		return tournoi;
	}

	public void setTournoi(String tournoi) {
		this.tournoi = tournoi;
	}

	public String getType_epreuve() {
		return type_epreuve;
	}

	public void setType_epreuve(String type_epreuve) {
		this.type_epreuve = type_epreuve;
	}

	public String getAnnee_epreuve() {
		return annee_epreuve;
	}

	public void setAnnee_epreuve(String annee_epreuve) {
		this.annee_epreuve = annee_epreuve;
	}

	public String getNom_vainqueur() {
		return nom_vainqueur;
	}

	public void setNom_vainqueur(String nom_vainqueur) {
		this.nom_vainqueur = nom_vainqueur;
	}

	public String getNom_finaliste() {
		return nom_finaliste;
	}

	public void setNom_finaliste(String nom_finaliste) {
		this.nom_finaliste = nom_finaliste;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", id_epreuve=" + id_epreuve + ", id_vainqueur=" + id_vainqueur + ", id_finaliste="
				+ id_finaliste + "]";
	}
	
}
