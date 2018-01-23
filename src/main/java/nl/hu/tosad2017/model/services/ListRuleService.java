package nl.hu.tosad2017.model.services;

import nl.hu.tosad2017.model.model.ListRule;
import nl.hu.tosad2017.persistence.tool.ToolListRuleDAO;
import nl.hu.tosad2017.persistence.target.TargetListRuleDAO;

public class ListRuleService {
	ToolListRuleDAO ToolDAO = new ToolListRuleDAO();
	TargetListRuleDAO TargetDAO = new TargetListRuleDAO();
	
	public ListRuleService() {}
	
	public ListRule getListRuleByCode (int code) {
		// logging for Heroku application server
		System.out.println(".. executing ListRule Service (GET) for " + code);	
		
		//TODO Implement updateRule in DAO
		//return ToolDAO.getListRuleByCode(code);
		return null;
	}
	
	public ListRule defineListRule (ListRule rule) {
		System.out.println(".. executing ListRule Service (CREATE)");
		//TODO Implement updateRule in DAO
		//ListRule definedRule = ToolDAO.defineRule(rule);
		
		//return definedRule;
		return null;
	}
	
	public ListRule updateListRule (ListRule rule) {
		System.out.println(".. executing ListRule Service (UPDATE) for " + rule.getCode());
		//TODO Implement updateRule in DAO
		//ListRule updatedRule = ToolDAO.updateRule(rule);
		
		//return updatedRule;
		return null;
	}
	
	public boolean deleteListRule (ListRule rule) {
		System.out.println(".. executing ListRule Service (DELETE) for " + rule.getCode());
		//TODO Implement updateRule in DAO
		//ToolDAO.deleteListRule(rule);
		//TargetDAO.deleteListRule(rule);
		
		return true;
	}
}
