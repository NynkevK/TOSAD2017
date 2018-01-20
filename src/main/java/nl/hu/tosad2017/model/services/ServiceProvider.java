package nl.hu.tosad2017.model.services;

public class ServiceProvider {
	private static RangeRuleService rangeruleservice = new RangeRuleService();
	private static RuleService ruleservice = new RuleService();
	
	public static RangeRuleService getRangeRuleService() {
		System.out.println(".. initialising ServiceProvider");
		return rangeruleservice;	
	}

	public static RuleService getRuleService() {
		System.out.println(".. initialising ServiceProvider");
		return ruleservice;	
	}
}
