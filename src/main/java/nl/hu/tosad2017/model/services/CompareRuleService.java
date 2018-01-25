package nl.hu.tosad2017.model.services;

import nl.hu.tosad2017.persistence.tool.ToolCompareRuleDAO;

import java.sql.SQLException;
import java.util.List;

import nl.hu.tosad2017.model.model.CompareRule;
import nl.hu.tosad2017.persistence.target.TargetCompareRuleDAO;

public class CompareRuleService {
	ToolCompareRuleDAO ToolDAO = new ToolCompareRuleDAO();
	TargetCompareRuleDAO TargetDAO = new TargetCompareRuleDAO();
	
	public CompareRuleService() {}
	
	public List<CompareRule> getAllCompareRules() throws SQLException {
		return ToolDAO.readAllRules();
	}
	
	public CompareRule getCompareRuleById (int id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing CompareRule Service (GET) for " + id);	
		
		return ToolDAO.readRule(id);
	}
	
	public boolean defineCompareRule (CompareRule rule) throws SQLException {
		System.out.println(".. executing CompareRule Service (CREATE)");
		
		return ToolDAO.createRule(rule);
	}
	
	public boolean updateCompareRule (CompareRule rule) throws SQLException {
		System.out.println(".. executing CompareRule Service (UPDATE) for " + rule.getId());

		return ToolDAO.updateRule(rule);
	}
	
	public boolean deleteCompareRule (int id) throws SQLException {
		System.out.println(".. executing CompareRule Service (DELETE) for " + id);
		
		return ToolDAO.deleteRule(id);
	}
}
