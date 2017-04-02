package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoBD{

	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
}
 

	public static Connection obtemConexao() throws SQLException
	{
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/sistemaPredial?user=root&password=1234" );//user=alunos / user=root
	}
}