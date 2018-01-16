package nl.hu.tosad2017.model;

public class Row {
	private String value;
	private String id;
	
	public Row (String value, String id) {
		this.value = value;
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getId() {
		return id;
	}

}
