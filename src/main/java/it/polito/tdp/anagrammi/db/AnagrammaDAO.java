package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AnagrammaDAO {
	Set<String> dizionario;
	
	
	public AnagrammaDAO() {
		super();
		dizionario = new HashSet<String>();
		this.getAllWords();
	}


	private void getAllWords() {
		
		String sql = "SELECT parola.nome FROM parola";
	
		try {
			Connection conn = DbConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
						
			while(rs.next()) {
				String nome = rs.getString("nome");
				
				dizionario.add(nome);
			}
			
			st.close();
			conn.close();
			rs.close();
			return;
			
		} catch (SQLException e) {
			System.err.println("Errore nel DAO anagramma");
			e.printStackTrace();
			return;
		}
	
	}
	
	public Set<String> getDizionario() {
		return dizionario;
	}
	
	public boolean isCorretto(String s) {
		String sql = "SELECT parola.nome FROM parola WHERE nome = ?";
		boolean ret = false;
		
		try {
			Connection conn = DbConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, s);
			ResultSet rs = st.executeQuery();
						
			while(rs.next()) {
				ret = true;
			}
			
			st.close();
			conn.close();
			rs.close();
			return ret;
			
		} catch (SQLException e) {
			System.err.println("Errore nel DAO anagramma");
			e.printStackTrace();
			return (Boolean) null;
		}
	}
	
}
