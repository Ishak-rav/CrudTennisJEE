package com.mycompany.dao;

import java.util.List;

import com.mycompany.beans.Tournoi;

public interface TournoiDao {
	void ajouter(Tournoi tournoi);
	Tournoi lecture(Long id);
	void modifier(Tournoi tournoi);
	void supprimer(Long id);
	List<Tournoi> lister();
	List<Tournoi> rechercher(String chaine);
}
