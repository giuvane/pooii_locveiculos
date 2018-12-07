package utfpr.giuvane.locadoraveiculos.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersistenciaJDBC {
	static Connection conexao;
	
	static
	{
		try {
			
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			
			conexao = DriverManager
                     .getConnection("jdbc:mysql://localhost/locadora_veiculos?zeroDateTimeBehavior=convertToNull","root", "12345");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	static Connection getConexao() {
		return conexao;
	}
}