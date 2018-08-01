package classes_de_conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
	
	public static Connection faz_conexao() throws SQLException{
		
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/db_chaves","root","admpmoises");
			
		} catch (Exception e) {
			throw new SQLException(e.getLocalizedMessage());
			
		
		}
		
	}
	
	

}
