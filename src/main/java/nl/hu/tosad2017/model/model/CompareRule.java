package nl.hu.tosad2017.model.model;

public class CompareRule extends BusinessRule{	
	private String comparedTable;
	private String comparedColumn;
	private int compareValue;
	
	public String getComparedTable { return comparedTable; }
	
	public void setComparedTable (String table) { this.comparedTable = table; }
	
	public String getComparedColumn { return comparedColumn; }
	
	public void setComparedColumn (String column) { this.comparedColumn = column; }
	
	public int getCompareValue { return compareValue; }
	
	public void setCompareValue (int value) { this.compareValue = value; }
}
