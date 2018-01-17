package nl.hu.tosad2017.model;

import java.util.ArrayList;
import nl.hu.tosad2017.persistence.RowDAO;

//import java.sql.*;

public class RangeRuleService {
	RowDAO rowDAO = new RowDAO();
	
	public RangeRuleService() {}

	public ArrayList<String> getRangeRules() {		
		System.out.println(".. initialising RangeRule Service");
		return rowDAO.getRangeRules();
	}
	
	public Row getRowById(String Id) {
		return rowDAO.findById(Id);
	}
	
	public Row addRow (Row r) {
		return rowDAO.save(r);
	}
}
