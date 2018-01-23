package nl.hu.tosad2017.model.services;

import nl.hu.tosad2017.model.model.ModifyRule;
import nl.hu.tosad2017.persistence.tool.ToolModifyRuleDAO;
import nl.hu.tosad2017.persistence.target.TargetModifyRuleDAO;

public class ModifyRuleService {
	ToolModifyRuleDAO ToolDAO = new ToolModifyRuleDAO();
	TargetModifyRuleDAO TargetDAO = new TargetModifyRuleDAO();
	
	public ModifyRuleService() {}
	
	public ModifyRule getModifyRuleByCode (int code) {
		// logging for Heroku application server
		System.out.println(".. executing ModifyRule Service (GET) for " + code);	
		//TODO Implement updateRule in DAO
		//return ToolDAO.getModifyRuleByCode(code);
		return null;
	}
	
	public ModifyRule defineModifyRule (ModifyRule rule) {
		System.out.println(".. executing ModifyRule Service (CREATE)");
		//TODO Implement updateRule in DAO
		//ModifyRule definedRule = ToolDAO.defineRule(rule);
		
		//return definedRule;
		return null;
	}
	
	public ModifyRule updateModifyRule (ModifyRule rule) {
		System.out.println(".. executing ModifyRule Service (UPDATE) for " + rule.getCode());
		//TODO Implement in DAO
		//ModifyRule updatedRule = ToolDAO.updateRule(rule);
		
		//return updatedRule;
		return null;
	}
	
	public boolean deleteModifyRule (ModifyRule rule) {
		System.out.println(".. executing ModifyRule Service (DELETE) for " + rule.getCode());
		//TODO Implement updateRule in DAO
		//ToolDAO.deleteModifyRule(rule);
		//TargetDAO.deleteModifyRule(rule);
		
		return true;
	}
}
