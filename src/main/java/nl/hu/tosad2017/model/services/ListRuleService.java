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
		
		return ToolDAO.readRule(id);
	}
	
	public boolean defineListRule (ListRule rule) throws SQLException {
		
		return ToolDAO.createRule(rule);
	}
	
	public boolean updateListRule (ListRule rule) throws SQLException {
		return ToolDAO.updateRule(rule);
	}
	
	public boolean deleteListRule (int id) throws SQLException {	
		return ToolDAO.deleteRule(id);
	}
}
