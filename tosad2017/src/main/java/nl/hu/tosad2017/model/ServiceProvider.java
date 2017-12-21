package nl.hu.tosad2017.model;

public class ServiceProvider {
	private static Service service = new Service();
	
	public static Service getService() {
		return service;	
	}
}
