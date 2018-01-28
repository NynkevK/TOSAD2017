package nl.hu.tosad2017.model.services;

import java.sql.SQLException;
import java.util.List;

import nl.hu.tosad2017.model.model.RangeRule;
import nl.hu.tosad2017.persistence.target.TargetRangeRuleDAO;
import nl.hu.tosad2017.persistence.tool.ToolRangeRuleDAO;

//import java.sql.*;

public class RangeRuleService {
	ToolRangeRuleDAO ToolDAO = new ToolRangeRuleDAO();
	TargetRangeRuleDAO TargetDAO = new TargetRangeRuleDAO();
	
	public RangeRuleService() {}
	
	public List<RangeRule> getAllRangeRules() throws SQLException {
		return ToolDAO.readAllRules(); 
	}
	
	public RangeRule getRangeRuleById(int id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (GET) for " + id);
		return ToolDAO.readRule(id);
	}
	
	public boolean defineRangeRule(RangeRule rule) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (CREATE)");
		return ToolDAO.createRule(rule);
	}
	
	public boolean updateRangeRule(RangeRule rule) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (UPDATE) for " + rule.getId());
		
		//RangeRule updatedRule = 
		return ToolDAO.updateRule(rule);
	}
	
	public boolean deleteRangeRule(int id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (DELETE) for " + id);
		return ToolDAO.deleteRule(id);
	}
}
