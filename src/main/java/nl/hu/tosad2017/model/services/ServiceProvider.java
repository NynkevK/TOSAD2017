package nl.hu.tosad2017.model.services;

public class ServiceProvider {
	private static RuleService ruleservice = new RuleService();
	private static RangeRuleService rangeruleservice = new RangeRuleService();	
	private static CompareRuleService compareRuleService = new CompareRuleService();
	private static ModifyRuleService modifyRuleService = new ModifyRuleService();
	private static ListRuleService listRuleService = new ListRuleService();
	
	public static RangeRuleService getRangeRuleService() {
		System.out.println(".. initialising ServiceProvider");
		return rangeruleservice;	
	}
	
	public static CompareRuleService getCompareRuleService() {
		System.out.println(".. initialising ServiceProvider");
		return compareRuleService;
	}
	
	public static ModifyRuleService getModifyRuleService() {
		System.out.println(".. initialising ServiceProvider");
		return modifyRuleService;
	}
	
	public static ListRuleService getListRuleService() {
		System.out.println(".. initialising ServiceProvider");
		return listRuleService;
	}
	
	
/*
	public static RuleService getRuleService() {
		System.out.println(".. initialising ServiceProvider");
		return ruleservice;	
	}*/
}
