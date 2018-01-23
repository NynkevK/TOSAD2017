package nl.hu.tosad2017.model.services;

import nl.hu.tosad2017.persistence.tool.ToolCompareRuleDAO;
import nl.hu.tosad2017.model.model.CompareRule;
import nl.hu.tosad2017.persistence.target.TargetCompareRuleDAO;

public class CompareRuleService {
	ToolCompareRuleDAO ToolDAO = new ToolCompareRuleDAO();
	TargetCompareRuleDAO TargetDAO = new TargetCompareRuleDAO();
	
	public CompareRuleService() {}
	
	public CompareRule getCompareRuleByCode (int code) {
		// logging for Heroku application server
		System.out.println(".. executing CompareRule Service (GET) for " + code);	
		//TODO Implement updateRule in DAO
		//return ToolDAO.getCompareRuleByCode(code);
		return null;
	}
	
	public CompareRule defineCompareRule (CompareRule rule) {
		System.out.println(".. executing CompareRule Service (CREATE)");
		//TODO Implement updateRule in DAO
		//CompareRule definedRule = ToolDAO.defineRule(rule);
		
		//return definedRule;
		return null;
	}
	
	public CompareRule updateCompareRule (CompareRule rule) {
		System.out.println(".. executing CompareRule Service (UPDATE) for " + rule.getCode());
		//TODO Implement updateRule in DAO
		//CompareRule updatedRule = ToolDAO.updateRule(rule);
		
		//return updatedRule;
		return null;
	}
	
	public boolean deleteCompareRule (CompareRule rule) {
		System.out.println(".. executing CompareRule Service (DELETE) for " + rule.getCode());
		//TODO Implement updateRule in DAO
		//ToolDAO.deleteCompareRule(rule);
		//TargetDAO.deleteCompareRule(rule);
		
		return true;
	}
}
