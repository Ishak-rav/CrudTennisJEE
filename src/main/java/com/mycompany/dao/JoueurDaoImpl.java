package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.Joueur;

public class JoueurDaoImpl implements JoueurDao{
	
	private DaoFactory daofactory;
	
	public JoueurDaoImpl(DaoFactory daofactory) {
		super();
		this.daofactory = daofactory;
	}

	@Override
	public void ajouter(Joueur joueur) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "INSERT INTO joueur (nom, prenom, sexe) VALUES (?, ?, ?)";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, joueur.getNom());
			statement.setString(2, joueur.getPrenom());
			statement.setString(3, joueur.getSexe());
			statement.executeUpdate();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@Override
	public Joueur lecture(Long id) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT * FROM joueur WHERE id =?";
			statement = connexion.prepareStatement(strSql);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return new Joueur(
						rs.getInt("id"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("sexe")
				);
			} else {
				return null;
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void modifier(Joueur joueur) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "UPDATE joueur SET nom=?, prenom=?, sexe=? WHERE id=?";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, joueur.getNom());
			statement.setString(2, joueur.getPrenom());
			statement.setString(3, joueur.getSexe());
			statement.setLong(4, joueur.getId());
			statement.executeUpdate();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void supprimer(Long id) {
		// TODO Auto-generated method stub
        Connection connexion = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst1 = null;
        try {
			connexion = daofactory.getConnection();
			String strSql = "DELETE sV FROM score_vainqueur AS sV INNER JOIN match_tennis AS m ON sV.ID_MATCH = m.ID where m.ID_VAINQUEUR = ? OR m.ID_FINALISTE = ?";
			pst = connexion.prepareStatement(strSql);
            pst.setLong(1, id);
            pst.setLong(2, id);
            pst.executeUpdate();
            //System.out.println("score deleted");
            
            pst1 = connexion.prepareStatement("DELETE m FROM match_tennis AS m where m.ID_VAINQUEUR = ? OR m.ID_FINALISTE = ?");
            pst1.setLong(1, id);
            pst1.setLong(2, id);
            pst1.executeUpdate();
            //System.out.println("match deleted");

            pst2 = connexion.prepareStatement("DELETE j FROM joueur AS j where id = ?");
            pst2.setLong(1, id);
            pst2.executeUpdate();
            //System.out.println("joueur deleted");

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public List<Joueur> lister() {
		// TODO Auto-generated method stub
		List<Joueur> joueurs = new ArrayList<Joueur>();
		
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT * FROM joueur";
			statement = connexion.prepareStatement(strSql);
			
			ResultSet rs = statement.executeQuery();			
			while (rs.next()) {
				joueurs.add(new Joueur(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe")));
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return joueurs;
	}

	@Override
	public List<Joueur> rechercher(String chaine) {
		// TODO Auto-generated method stub
		List<Joueur> joueurs = new ArrayList<Joueur>();
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT * FROM joueur WHERE NOM LIKE ? OR PRENOM LIKE ?";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");
			ResultSet rs = statement.executeQuery();			
			while (rs.next()) {
				joueurs.add(new Joueur(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe")));
				// System.out.println(rs.getString("nom"));
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return joueurs;
	}
}
