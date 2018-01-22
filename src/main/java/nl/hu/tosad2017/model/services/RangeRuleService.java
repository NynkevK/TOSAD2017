package nl.hu.tosad2017.model.services;

import java.sql.SQLException;
import java.util.ArrayList;

import nl.hu.tosad2017.model.model.RangeRule;
import nl.hu.tosad2017.persistence.target.TargetRangeRuleDAO;
import nl.hu.tosad2017.persistence.tool.ToolRangeRuleDAO;

//import java.sql.*;

public class RangeRuleService {
	ToolRangeRuleDAO ToolDAO = new ToolRangeRuleDAO();
	TargetRangeRuleDAO TargetDAO = new TargetRangeRuleDAO();
	
	public RangeRuleService() {}
	
	public RangeRule getRangeRuleByCode(int id) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (GET) for " + id);
		
		try {
			return ToolDAO.readRule(id);
		} catch (SQLException e) {
			e.getMessage();
		}
		return null;
	}
	
	public boolean defineRangeRule(RangeRule rule) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (CREATE)");
		try {
			return ToolDAO.createRule(rule);
		} catch (SQLException e) { 
			e.getMessage();
		}
		
		
		
		return null;
	}
	
	public RangeRule updateRangeRule(int id) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (UPDATE) for " + id);
		//TODO Implement updateRule in DAO
		RangeRule updatedRule = ToolDAO.updateRule(id);
		
		return updatedRule;
	}
	
	public boolean deleteRangeRule(int id) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (DELETE) for " + id);
		ToolDAO.deleteRule(id);
		//TODO Implement updateRule in DAO
		TargetDAO.deleteRule(id);
		
		return true;
	}
}
