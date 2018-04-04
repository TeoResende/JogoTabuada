package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class Conexao {
	
	public static Connection pegaConexao2() {
		
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/tabuada","root","root");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao conectar-se a base de dados"+e1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao conectar-se a base de dados"+e);
		}
		
		return c;
	}
	
	public static Connection pegaConexao(){
		
			Connection c = null;
			try
			{
				c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/tabuada","root","root");
																  // 127.0.0.1   192.168.10.32
				System.out.println("Banco Conectado!");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao conectar ao banco de dados!");
			}
		return c;
	}
	
}
