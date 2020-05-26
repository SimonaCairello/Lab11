package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore DB\n");
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public List<Flow> getFlowRiver(River river) {
		
		final String sql = "SELECT * " + 
				"FROM flow " + 
				"WHERE river = ?";

		List<Flow> flows = new LinkedList<Flow>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				flows.add(new Flow(res.getInt("id"), res.getDate("day").toLocalDate(), res.getDouble("flow"), river));
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore DB\n");
			throw new RuntimeException("SQL Error");
		}

		return flows;
	}
	
	public double getFlowAvg(River river) {
		
		final String sql = "SELECT AVG(flow) AS A " + 
				"FROM flow " + 
				"WHERE river = ?";

		double avg = 0;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				avg = res.getDouble("A");
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore DB\n");
			throw new RuntimeException("SQL Error");
		}

		return avg;
	}
}
