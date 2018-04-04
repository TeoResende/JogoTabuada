package Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDRanking {
	
	Connection c = new Conexao().pegaConexao();
	
	public boolean novoJogador(String nome,int pontos) {
		boolean resposta = true;
		String sql = "INSERT INTO ranking (nome,pontos) VALUES (?,?)";
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1,nome);
			stmt.setInt(2,pontos);
			resposta = stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resposta;
	}

	public ResultSet SelecionaRanking() {
		ResultSet ranking = null;
		String sql = "SELECT * FROM ranking ORDER BY pontos";
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			ranking = stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ranking;
	}
	
	public boolean zeraRanking(String senha) {
		boolean resposta = true;
		String sql = "DELETE FROM ranking";
		if(senha.equalsIgnoreCase("masterkey")) {
			try {
				PreparedStatement stmt = c.prepareStatement(sql);
				resposta = stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resposta;
	}
	
	public ResultSet consultar(){
		String sql = "SELECT * FROM ranking";
		ResultSet dados = null;
		try {
			PreparedStatement consulta = c.prepareStatement(sql);
			dados = consulta.executeQuery();
			consulta.execute();
			consulta.close();
			System.out.println("Dados selecionados");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro na consulta!");
			return null;
		}			
		return dados;
	}
	
	public boolean atualizaPontos(int id,int acertos){
		boolean resposta = true;
		String sql = "UPDATE ranking SET pontos=? WHERE idRanking = ?";
		try {
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, acertos);
			stmt.setInt(2, id);
			resposta = stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return resposta;
	}
}
