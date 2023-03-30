package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.Epreuve;

public class EpreuveDaoImpl implements EpreuveDao{
	
	private DaoFactory daofactory;

	public EpreuveDaoImpl(DaoFactory daoFactory) {
		super();
		this.daofactory = daoFactory;
	}

	@Override
	public void ajouter(Epreuve epreuve) {
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "INSERT INTO epreuve (annee, type_epreuve, id_tournoi) VALUES (?, ?, ?)";
			statement = connexion.prepareStatement(strSql);
			statement.setLong(1, epreuve.getAnnee());
			statement.setString(2, epreuve.getType());
			statement.setLong(3, epreuve.getId_tournoi());
			statement.executeUpdate();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		
	}

	@Override
	public Epreuve lecture(Long id) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT * FROM EPREUVE WHERE id =?";
			statement = connexion.prepareStatement(strSql);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return new Epreuve(
						rs.getLong("id"),
						rs.getLong("id_tournoi"),
						rs.getLong("annee"),
						rs.getString("type_epreuve")
				);
			} else {
				return null;
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void modifier(Epreuve epreuve) {
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "UPDATE epreuve SET annee = ?, type_epreuve = ?, id_tournoi = ? WHERE id = ?";
			statement = connexion.prepareStatement(strSql);
			statement.setLong(1, epreuve.getAnnee());
			statement.setString(2, epreuve.getType());
			statement.setLong(3, epreuve.getId_tournoi());
			statement.setLong(4, epreuve.getId());
			statement.executeUpdate();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void supprimer(Long id) {
        Connection connexion = null;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        try {
			connexion = daofactory.getConnection();
			String strSql = "DELETE sV FROM SCORE_VAINQUEUR AS sV INNER JOIN MATCH_TENNIS AS m ON sV.ID_MATCH = m.ID WHERE m.ID_EPREUVE = ?";
			pst = connexion.prepareStatement(strSql);
            pst.setLong(1, id);
            pst.executeUpdate();
            
            pst1 = connexion.prepareStatement("DELETE m FROM MATCH_TENNIS AS m WHERE m.ID_EPREUVE = ?");
            pst1.setLong(1, id);
            pst1.executeUpdate();

            pst2 = connexion.prepareStatement("DELETE e FROM EPREUVE AS e WHERE e.ID = ?");
            pst2.setLong(1, id);
            pst2.executeUpdate();

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
	}

	@Override
	public List<Epreuve> lister() {
		List<Epreuve> epreuves = new ArrayList<Epreuve>();
		
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT * FROM epreuve e INNER JOIN tournoi t ON e.ID_TOURNOI = t.ID";
			statement = connexion.prepareStatement(strSql);
			
			ResultSet rs = statement.executeQuery();			
			while (rs.next()) {
				epreuves.add(
					new Epreuve(
						rs.getLong("id"),
						rs.getLong("id_tournoi"),
						rs.getLong("annee"),
						rs.getString("type_epreuve"), 
						rs.getString("nom"),
						rs.getString("code")
					)
				);
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return epreuves;
	}

	@Override
	public List<Epreuve> rechercher(String chaine) {
		List<Epreuve> epreuves = new ArrayList<Epreuve>();
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT * FROM epreuve WHERE annee LIKE ? OR type_epreuve LIKE ?";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");
			ResultSet rs = statement.executeQuery();			
			while (rs.next()) {
				epreuves.add(
					new Epreuve(
						rs.getLong("id"),
						rs.getLong("id_tournoi"),
						rs.getLong("annee"),
						rs.getString("type_epreuve")
					)
				);
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return epreuves;
	}

}
