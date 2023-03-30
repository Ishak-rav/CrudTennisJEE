package com.mycompany.dao;

import java.util.List;

import com.mycompany.beans.Match;


public interface MatchDao {
	void ajouter(Match match);
	Match lecture(Long id);
	void modifier(Match match);
	void supprimer(Long id);
	List<Match> lister();
	List<Match> rechercher(String chaine);
}
