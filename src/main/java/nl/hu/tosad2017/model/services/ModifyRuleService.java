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
	
	public List<ModifyRule> getAllModifyRules() throws SQLException {
		return ToolDAO.readAllRules();
	}
	
	public ModifyRule getModifyRuleById (int id) throws SQLException {
		
		return ToolDAO.readRule(id);
	}
	
	public boolean defineModifyRule (ModifyRule rule) throws SQLException {
		
		return ToolDAO.createRule(rule);
	}
	
	public ModifyRule updateModifyRule (int id) {
		//TODO Implement in DAO
		//ModifyRule updatedRule = ToolDAO.updateRule(rule);
		
		//return updatedRule;
		return null;
	}
	
	public boolean deleteModifyRule (int id) throws SQLException{
		
		return ToolDAO.deleteRule(id);
	}
}
