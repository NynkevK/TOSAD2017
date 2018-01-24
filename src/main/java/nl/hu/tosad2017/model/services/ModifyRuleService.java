package nl.hu.tosad2017.model.services;

import java.util.List;
import java.sql.SQLException;

import nl.hu.tosad2017.model.model.ModifyRule;
import nl.hu.tosad2017.persistence.tool.ToolModifyRuleDAO;
import nl.hu.tosad2017.persistence.target.TargetModifyRuleDAO;

public class ModifyRuleService {
	ToolModifyRuleDAO ToolDAO = new ToolModifyRuleDAO();
	TargetModifyRuleDAO TargetDAO = new TargetModifyRuleDAO();
	
	public ModifyRuleService() {}
	
	public List<ModifyRule> getAllRangeRules() throws SQLException {
		return ToolDAO.readAllRules();
	}
	
	public ModifyRule getModifyRuleByCode (int id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing ModifyRule Service (GET) for " + id);	
		
		return ToolDAO.readRule(id);
	}
	
	public boolean defineModifyRule (ModifyRule rule) throws SQLException {
		System.out.println(".. executing ModifyRule Service (CREATE)");
		
		return ToolDAO.createRule(rule);
	}
	
	public ModifyRule updateModifyRule (int id) {
		System.out.println(".. executing ModifyRule Service (UPDATE) for " + id);
		//TODO Implement in DAO
		//ModifyRule updatedRule = ToolDAO.updateRule(rule);
		
		//return updatedRule;
		return null;
	}
	
	public boolean deleteModifyRule (int id) throws SQLException{
		System.out.println(".. executing ModifyRule Service (DELETE) for " + id);
		
		return ToolDAO.deleteRule(id);
	}
}
