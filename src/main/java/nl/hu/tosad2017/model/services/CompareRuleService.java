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
		
		return ToolDAO.getCompareRuleByCode(code);
	}
	
	public CompareRule defineCompareRule (CompareRule rule) {
		System.out.println(".. executing CompareRule Service (CREATE)");
		CompareRule definedRule = ToolDAO.defineRule(rule);
		
		return definedRule;
	}
	
	public CompareRule updateCompareRule (CompareRule rule) {
		System.out.println(".. executing CompareRule Service (UPDATE) for " + rule.getCode());
		CompareRule updatedRule = ToolDAO.updateRule(rule);
		
		return updatedRule;
	}
	
	public boolean deleteCompareRule (CompareRule rule) {
		System.out.println(".. executing CompareRule Service (DELETE) for " + rule.getCode());
		ToolDAO.deleteCompareRule(rule);
		TargetDAO.deleteCompareRule(rule);
		
		return true;
	}
}
