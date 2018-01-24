package nl.hu.tosad2017.model.services;

import nl.hu.tosad2017.persistence.tool.ListRuleDAO;
import nl.hu.tosad2017.persistence.tool.ToolListRuleDAO;
import nl.hu.tosad2017.model.model.BusinessRule;
import nl.hu.tosad2017.persistence.target.ListRuleDAO;
import nl.hu.tosad2017.persistence.target.TargetListRuleDAO;

public class ListRuleService {
	ToolListRuleDAO ToolDAO = new ToolListRuleDAO();
	TargetListRuleDAO TargetDAO = new TargetListRuleDAO();
	
	public ListRuleService() {}
	
	public BusinessRule getListRuleByCode (int code) {
		// logging for Heroku application server
		System.out.println(".. executing ListRule Service (GET) for " + code);	
		
		return ToolDAO.getListRuleByCode(code);
	}
	
	public BusinessRule defineListRule (BusinessRule rule) {
		System.out.println(".. executing ListRule Service (CREATE)");
		BusinessRule definedRule = ToolDAO.defineRule(rule);
		
		return definedRule;
	}
	
	public BusinessRule updateListRule (BusinessRule rule) {
		System.out.println(".. executing ListRule Service (UPDATE) for " + rule.getCode());
		BusinessRule updatedRule = ToolDAO.updateRule(rule);
		
		return updatedRule;
	}
	
	public boolean deleteListRule (BusinessRule rule) {
		System.out.println(".. executing ListRule Service (DELETE) for " + rule.getCode());
		ToolDAO.deleteListRule(rule);
		TargetDAO.deleteListRule(rule);
		
		return true;
	}
}
