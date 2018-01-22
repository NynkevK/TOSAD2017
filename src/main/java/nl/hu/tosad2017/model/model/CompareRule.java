package nl.hu.tosad2017.model.model;

<<<<<<< HEAD
public class CompareRule extends BusinessRule {
    private String comparedColumn;
    private String comparedTable;
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int compareValue;

    public String getComparedColumn() {
        return comparedColumn;
    }

    public void setComparedColumn(String comparedColumn) {
        this.comparedColumn = comparedColumn;
    }

    public String getComparedTable() {
        return comparedTable;
    }

    public void setComparedTable(String comparedTable) {
        this.comparedTable = comparedTable;
    }

    public int getCompareValue() {
        return compareValue;
    }

    public void setCompareValue(int compareValue) {
        this.compareValue = compareValue;
    }
=======
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
>>>>>>> REST
}
