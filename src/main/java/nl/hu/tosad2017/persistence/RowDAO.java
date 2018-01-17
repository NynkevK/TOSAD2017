package nl.hu.tosad2017.persistence;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//TODO: Waarom werkt dit niet?
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.tosad2017.model.Row;

public class RowDAO extends BaseDAO{
	
	public Row findById(String Id) {
		List<Row> results = selectRows("SELECT * FROM CONNECTION_TEST WHERE ID='" + Id + "'");
		if (results.size() == 0) {
			return null;
		} else {
			return selectRows("SELECT * FROM CONNECTION_TEST WHERE ID='" + Id + "'").get(0);
		}
	}

	private List<Row> selectRows(String query) {
		List<Row> results = new ArrayList<Row>();
		
		try (Connection con = super.getConnection()) {
			//TODO: Zelfde als boven, maar waarom willen ze dit casten?
			Statement stmt = (Statement) con.createStatement();
			ResultSet dbResultSet = ((java.sql.Statement) stmt).executeQuery(query);
			
			while (dbResultSet.next()) {
				String value = dbResultSet.getString("value");
				String id = dbResultSet.getString("id");
				
				Row newRow = new Row(value, id);
				
				results.add(newRow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}

	public Row save(Row row) {
		String query = "INSERT INTO CONNECTION_TEST VALUES(value, id) VALUES('"
				+ row.getValue() + "', '"
				+ row.getId() + "');";
		
		try (Connection con = super.getConnection()) {
			//TODO: Zelfde als boven, maar waarom willen ze dit casten?
			Statement stmt = (Statement) con.createStatement();
			((java.sql.Statement) stmt).executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return findById(row.getId());
	}

	// TODO replace string type with business rule class type
	public ArrayList<String> getRangeRules() {
		System.out.println(".. initialising DAO");
		ArrayList<String> test = new ArrayList<String>();
		test.add("test");
		test.add("test2");
		
		return test;
	}
}
