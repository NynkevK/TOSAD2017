package nl.hu.tosad2017.model.model;

public class CompareRule extends BusinessRule {
    private String comparedColumn;
    private String comparedTable;
    private int value;
    
    public CompareRule (int id, String code, String name, String msg, String ruletype, String c_name, String c_type, String t_name
            ,String r_status, String r_operator, String r_triggerevents, String t_column, String t_table, int value) {
		super.id = id;
		super.code = code;
		super.name = name;
		super.messageText = msg;
		super.ruleType = ruletype;
		super.columnName = c_name;
		super.columnType = c_type;
		super.tableName = t_name;
		super.status = r_status;
		super.operator = r_operator;
		super.triggerEvents = r_triggerevents;
		this.comparedColumn = t_column;
		this.comparedTable = t_table;
		this.value = value;
		}

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
    
    public String GenerateCode() {
    	String code = "";
    	if (this.ruleType == "Attribute") {
    		code = "l_passed := :new."+ this.columnName +" "+  this.operator + " " + this.compareValue +";\n";
    	} else if (this.ruleType == "Tuple") {
    		code = "l_passed := :new."+ this.columnName +" "+  this.operator + " new." + this.comparedColumn +";\n";
    	} else if (this.ruleType == "Inter-Entity") {
    		code = "l_passed := :new."+ this.columnName +" "+  this.operator + " " + this.tableName + " " + this.comparedColumn +";\n";
    	} else {
    		System.out.println("Rule type niet herkend");
    	}
		return code;
    }
}
