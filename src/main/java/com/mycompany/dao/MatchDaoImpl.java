package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.Match;

public class MatchDaoImpl implements MatchDao {
	
	private DaoFactory daofactory;

	public MatchDaoImpl(DaoFactory daofactory) {
		super();
		this.daofactory = daofactory;
	}

	@Override
	public void ajouter(Match match) {
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "INSERT INTO match_tennis (id_epreuve, id_vainqueur, id_finaliste) VALUES (?, ?, ?)";
			statement = connexion.prepareStatement(strSql);
			statement.setLong(1, match.getId_epreuve());
			statement.setLong(2, match.getId_vainqueur());
			statement.setLong(3, match.getId_finaliste());
			statement.executeUpdate();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public Match lecture(Long id) {
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT * FROM match_tennis WHERE id =?";
			statement = connexion.prepareStatement(strSql);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return new Match(
						rs.getLong("id"),
						rs.getLong("id_epreuve"),
						rs.getLong("id_vainqueur"),
						rs.getLong("id_finaliste")
				);
			} else {
				return null;
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void modifier(Match match) {
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "UPDATE match_tennis SET id_epreuve = ?, id_vainqueur = ?, id_finaliste = ? WHERE id = ?";
			statement = connexion.prepareStatement(strSql);
			statement.setLong(1, match.getId_epreuve());
			statement.setLong(2, match.getId_vainqueur());
			statement.setLong(3, match.getId_finaliste());
			statement.setLong(4, match.getId());
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
        try {
			connexion = daofactory.getConnection();
			String strSql = "DELETE FROM SCORE_VAINQUEUR WHERE ID_MATCH = ?";
			pst = connexion.prepareStatement(strSql);
            pst.setLong(1, id);
            pst.executeUpdate();
            
            pst1 = connexion.prepareStatement("DELETE FROM MATCH_TENNIS WHERE ID = ?");
            pst1.setLong(1, id);
            pst1.executeUpdate();

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public List<Match> lister() {
		List<Match>	matchs = new ArrayList<Match>();
		
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = 
					"SELECT m.ID, m.ID_EPREUVE, m.ID_VAINQUEUR, m.ID_FINALISTE, t.NOM AS TOURNOI, e.TYPE_EPREUVE, e.ANNEE, j1.NOM AS VAINQUEUR, j2.NOM AS FINALISTE\r\n"
					+ "FROM MATCH_TENNIS AS m \r\n"
					+ "INNER JOIN EPREUVE AS e ON m.ID_EPREUVE = e.ID\r\n"
					+ "INNER JOIN JOUEUR AS j1 ON m.ID_VAINQUEUR = j1.ID\r\n"
					+ "INNER JOIN JOUEUR AS j2 ON m.ID_FINALISTE = j2.ID\r\n"
					+ "INNER JOIN TOURNOI AS t ON e.ID_TOURNOI = t.ID";
			statement = connexion.prepareStatement(strSql);
			
			ResultSet rs = statement.executeQuery();			
			while (rs.next()) {
				matchs.add(
					new Match(
						rs.getLong("id"),
						rs.getLong("id_epreuve"),
						rs.getLong("id_vainqueur"),
						rs.getLong("id_finaliste"),
						rs.getString("tournoi"),
						rs.getString("type_epreuve"),
						rs.getString("annee"),
						rs.getString("vainqueur"),
						rs.getString("finaliste")
					)
				);
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return matchs;
	}

	@Override
	public List<Match> rechercher(String chaine) {
		List<Match> matchs = new ArrayList<Match>();
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daofactory.getConnection();
			String strSql = "SELECT m.ID, m.ID_EPREUVE, m.ID_VAINQUEUR, m.ID_FINALISTE, t.NOM AS TOURNOI, e.TYPE_EPREUVE, e.ANNEE, j1.NOM AS VAINQUEUR, j2.NOM AS FINALISTE\r\n"
					+ "FROM MATCH_TENNIS AS m \r\n"
					+ "INNER JOIN EPREUVE AS e ON m.ID_EPREUVE = e.ID\r\n"
					+ "INNER JOIN JOUEUR AS j1 ON m.ID_VAINQUEUR = j1.ID\r\n"
					+ "INNER JOIN JOUEUR AS j2 ON m.ID_FINALISTE = j2.ID\r\n"
					+ "INNER JOIN TOURNOI AS t ON e.ID_TOURNOI = t.ID\r\n"
					+ "WHERE j1.NOM LIKE ? OR j1.PRENOM LIKE ? OR j2.NOM LIKE ? OR j2.PRENOM LIKE ?";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");
			statement.setString(3, "%" + chaine + "%");
			statement.setString(4, "%" + chaine + "%");
			ResultSet rs = statement.executeQuery();			
			while (rs.next()) {
				matchs.add(
					new Match(
						rs.getLong("id"),
						rs.getLong("id_epreuve"),
						rs.getLong("id_vainqueur"),
						rs.getLong("id_finaliste"),
						rs.getString("tournoi"),
						rs.getString("type_epreuve"),
						rs.getString("annee"),
						rs.getString("vainqueur"),
						rs.getString("finaliste")
					)
				);
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return matchs;
	}
}
