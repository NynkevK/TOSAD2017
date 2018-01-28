package nl.hu.tosad2017.model.services;

import java.sql.SQLException;
import java.util.List;

import nl.hu.tosad2017.model.model.ListRule;
import nl.hu.tosad2017.persistence.tool.ToolListRuleDAO;
import nl.hu.tosad2017.persistence.target.TargetListRuleDAO;

public class ListRuleService {
	ToolListRuleDAO ToolDAO = new ToolListRuleDAO();
	TargetListRuleDAO TargetDAO = new TargetListRuleDAO();
	
	public ListRuleService() {}
	
	public List<ListRule> getAllListRules() throws SQLException {
		return ToolDAO.readAllRules();
	}
	
	public ListRule getListRuleById (int id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing ListRule Service (GET) for " + id);	
		
		return ToolDAO.readRule(id);
	}
	
	public boolean defineListRule (ListRule rule) throws SQLException {
		System.out.println(".. executing ListRule Service (CREATE)");
		
		return ToolDAO.createRule(rule);
	}
	
	public ListRule updateListRule (int id) {
		System.out.println(".. executing ListRule Service (UPDATE) for " + id);
		//TODO Implement updateRule in DAO
		//ListRule updatedRule = ToolDAO.updateRule(rule
		
		//return updatedRule;
		return null;
	}
	
	public boolean deleteListRule (int id) throws SQLException {
		System.out.println(".. executing ListRule Service (DELETE) for " + id);		
		return ToolDAO.deleteRule(id);
	}
}
