package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.Tournoi;

public class TournoiDaoImpl implements TournoiDao{
	
	private DaoFactory daofactory;
	
	public TournoiDaoImpl(DaoFactory daofactory) {
		super();
		this.daofactory = daofactory;
	}

	@Override
	public void ajouter(Tournoi tournoi) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "INSERT INTO tournoi (nom, code) VALUES (?, ?)";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, tournoi.getNom());
			statement.setString(2, tournoi.getCode());
			statement.executeUpdate();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public Tournoi lecture(Long id) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT * FROM tournoi WHERE id =?";
			statement = connexion.prepareStatement(strSql);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return new Tournoi(
						rs.getLong("id"),
						rs.getString("nom"),
						rs.getString("code")
				);
			} else {
				return null;
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void modifier(Tournoi tournoi) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "UPDATE tournoi SET nom=?, code=? WHERE id=?";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, tournoi.getNom());
			statement.setString(2, tournoi.getCode());
			statement.setLong(3, tournoi.getId());
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
        PreparedStatement pst3 = null;
        try {
			connexion = daofactory.getConnection();
			pst = connexion.prepareStatement("DELETE sV FROM SCORE_VAINQUEUR AS sV INNER JOIN MATCH_TENNIS AS m ON sV.ID_MATCH = m.ID INNER JOIN EPREUVE AS e ON m.ID_EPREUVE = e.ID WHERE e.ID_TOURNOI = ?");																		
            pst.setLong(1, id);
            pst.executeUpdate();
            //System.out.println("score deleted");
            
            pst1 = connexion.prepareStatement("DELETE m FROM MATCH_TENNIS AS m INNER JOIN EPREUVE AS e ON m.ID_EPREUVE = e.ID WHERE e.ID_TOURNOI = ?");
            pst1.setLong(1, id);
            pst1.executeUpdate();
            //System.out.println("match deleted");

            pst2 = connexion.prepareStatement("DELETE e FROM EPREUVE AS e WHERE e.ID_TOURNOI = ?");
            pst2.setLong(1, id);
            pst2.executeUpdate();
            //System.out.println("epreuve deleted");
            
            pst3 = connexion.prepareStatement("DELETE t FROM TOURNOI AS t WHERE t.ID = ?");
            pst3.setLong(1, id);
            pst3.executeUpdate();
            //System.out.println("tournoi deleted");

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public List<Tournoi> lister() {
		// TODO Auto-generated method stub
		List<Tournoi> tournois = new ArrayList<Tournoi>();
		
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT * FROM tournoi";
			statement = connexion.prepareStatement(strSql);
			
			ResultSet rs = statement.executeQuery();			
			while (rs.next()) {
				tournois.add(new Tournoi(rs.getLong("id"), rs.getString("nom"), rs.getString("code")));
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return tournois;
	}

	@Override
	public List<Tournoi> rechercher(String chaine) {
		// TODO Auto-generated method stub
		List<Tournoi> tournois = new ArrayList<Tournoi>();
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT * FROM tournoi WHERE NOM LIKE ? OR CODE LIKE ?";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");
			ResultSet rs = statement.executeQuery();			
			while (rs.next()) {
				tournois.add(new Tournoi(rs.getLong("id"), rs.getString("nom"), rs.getString("code")));
				//System.out.println(rs.getString("nom"));
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return tournois;
	}

}
