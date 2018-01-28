package nl.hu.tosad2017.persistence.target;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TargetBaseDAO {
	protected static final String DB_URL = "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl";
	protected static final String DB_USER = "tosad_2017_2b_team1";
	protected static final String DB_PASS = "tosad_2017_2b_team1";

	protected static Connection conn;

	public static Connection getConn() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		return conn;
	}
}
