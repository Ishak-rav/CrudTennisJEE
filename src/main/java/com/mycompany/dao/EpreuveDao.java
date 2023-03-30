package com.mycompany.dao;

import java.util.List;
import com.mycompany.beans.Epreuve;

public interface EpreuveDao {
	void ajouter(Epreuve epreuve);
	Epreuve lecture(Long id);
	void modifier(Epreuve epreuve);
	void supprimer(Long id);
	List<Epreuve> lister();
	List<Epreuve> rechercher(String chaine);
}
