package nl.hu.tosad2017.model.services;

public class ServiceProvider {
	private static RangeRuleService rangeruleservice = new RangeRuleService();	
	private static CompareRuleService compareRuleService = new CompareRuleService();
	private static ModifyRuleService modifyRuleService = new ModifyRuleService();
	private static ListRuleService listRuleService = new ListRuleService();
	private static OtherRuleService otherRuleService = new OtherRuleService();
	private static DatabaseService databaseService = new DatabaseService();
	
	public static RangeRuleService getRangeRuleService() {
		return rangeruleservice;	
	}
	
	public static CompareRuleService getCompareRuleService() {
		return compareRuleService;
	}
	
	public static ModifyRuleService getModifyRuleService() {
		return modifyRuleService;
	}
	
	public static ListRuleService getListRuleService() {
		return listRuleService;
	}

	public static OtherRuleService getOtherRuleService() {
		return otherRuleService;
	}

	public static DatabaseService getDatabaseService() {
		return databaseService;
	}
}