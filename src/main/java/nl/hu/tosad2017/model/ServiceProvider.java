package nl.hu.tosad2017.model;

public class ServiceProvider {
	private static RangeRuleService service = new RangeRuleService();
	
	public static RangeRuleService getService() {
		System.out.println(".. initialising ServiceProvider");
		return service;	
	}
}
