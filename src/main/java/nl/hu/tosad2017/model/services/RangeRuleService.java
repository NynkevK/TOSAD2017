package nl.hu.tosad2017.model.services;

import java.util.ArrayList;

import nl.hu.tosad2017.model.model.RangeRule;
import nl.hu.tosad2017.persistence.tool.RangeRuleDAO;

//import java.sql.*;

public class RangeRuleService {
	ToolRangeRuleDAO ToolDAO = new ToolRangeRuleDAO();
	TargetRangeRuleDAO TargetDAO = new TargetRangeRuleDAO();
	
	public RangeRuleService() {}
	
	/* Move following method to ruleservice
	public void getRangeRules() {		
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (GET)");
	} */
	
	/* Move following method to ruleservice
	public void getRuleById(String Id) {
	} */
	
	public RangeRule defineRangeRule(RangeRule rule) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (CREATE)");
		RangeRule definedRule = ToolDAO.defineRule(rule);
		
		return definedRule;
	}
	
	public RangeRule updateRangeRule(RangeRule rule) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (UPDATE) for " + rule.getCode());
		RangeRule updatedRule = ToolDAO.updateRule(rule);
		
		return updatedRule;
	}
	
	public boolean deleteRangeRule(RangeRule rule) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Service (DELETE) for " + rule.getCode());
		ToolDAO.deleteRangeRule(rule);
		TargetDAO.deleteRangeRule(rule);
		
		return true;
	}
}
