package nl.hu.tosad2017.model.services;

public class ServiceProvider {
	private static RangeRuleService rangeruleservice = new RangeRuleService();	
	private static CompareRuleService compareRuleService = new CompareRuleService();
	private static ModifyRuleService modifyRuleService = new ModifyRuleService();
	private static ListRuleService listRuleService = new ListRuleService();
	private static OtherRuleService otherRuleService = new OtherRuleService();
	
	public static RangeRuleService getRangeRuleService() {
		System.out.println(".. initialising RangeRule ServiceProvider");
		return rangeruleservice;	
	}
	
	public static CompareRuleService getCompareRuleService() {
		System.out.println(".. initialising CompareRule ServiceProvider");
		return compareRuleService;
	}
	
	public static ModifyRuleService getModifyRuleService() {
		System.out.println(".. initialising ModifyRule ServiceProvider");
		return modifyRuleService;
	}
	
	public static ListRuleService getListRuleService() {
		System.out.println(".. initialising ListRule ServiceProvider");
		return listRuleService;
	}

	public static OtherRuleService getOtherRuleService() {
		System.out.println(".. initialising OtherRule ServiceProvider");
		return otherRuleService;
	}
}