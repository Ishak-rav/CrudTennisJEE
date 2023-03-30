package com.mycompany.dao;

import java.util.List;

import com.mycompany.beans.Joueur;

public interface JoueurDao {
	void ajouter(Joueur joueur);
	Joueur lecture(Long id);
	void modifier(Joueur joueur);
	void supprimer(Long id);
	List<Joueur> lister();
	List<Joueur> rechercher(String chaine);
}