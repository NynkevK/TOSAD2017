package nl.hu.tosad2017.model.model;

public class CompareRule extends BusinessRule {
    private String comparedColumn;
    private String comparedTable;
    private int comparedValue;
    
    public CompareRule (int id, String code, String name, String msg, String ruletype, String c_name, String c_type, String t_name
            ,String r_status, String r_operator, String r_triggerevents, String t_column, String t_table, int t_value) {
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
		}

    public CompareRule(int id, String code, String name, String ruletype, String status, String columnname, String columntype,
                       String tablename, String comparedColumn, String comparedTable, int comapredValue, String operator,
                       String triggerevents, String messagetext) {
        super.id = id;
        super.code = code;
        super.name = name;
        super.ruleType = ruletype;
        super.status = status;
        super.columnName = columnname;
        super.columnType = columntype;
        super.tableName = tablename;
        this.comparedColumn = comparedColumn;
        this.comparedTable = comparedTable;
        this.comparedValue = comapredValue;
        super.operator = operator;
        super.triggerEvents = triggerevents;
        super.messageText = messagetext;
    }

    public CompareRule(String code, String name, String ruletype, String status, String columnname, String columntype,
                       String tablename, String comparedColumn, String comparedTable, int comapredValue, String operator,
                       String triggerevents, String messagetext) {
        super.code = code;
        super.name = name;
        super.ruleType = ruletype;
        super.status = status;
        super.columnName = columnname;
        super.columnType = columntype;
        super.tableName = tablename;
        this.comparedColumn = comparedColumn;
        this.comparedTable = comparedTable;
        this.comparedValue = comapredValue;
        super.operator = operator;
        super.triggerEvents = triggerevents;
        super.messageText = messagetext;
    }

    public CompareRule(int id, String code, String name, String ruletype, String status, String columnname, String columntype,
                       String tablename, int comapredValue, String operator,
                       String triggerevents, String messagetext) {
        super.id = id;
        super.code = code;
        super.name = name;
        super.ruleType = ruletype;
        super.status = status;
        super.columnName = columnname;
        super.columnType = columntype;
        super.tableName = tablename;
        this.comparedValue = comapredValue;
        super.operator = operator;
        super.triggerEvents = triggerevents;
        super.messageText = messagetext;
    }

    public CompareRule(String code, String name, String ruletype, String status, String columnname, String columntype,
                       String tablename, int comapredValue, String operator,
                       String triggerevents, String messagetext) {
        super.code = code;
        super.name = name;
        super.ruleType = ruletype;
        super.status = status;
        super.columnName = columnname;
        super.columnType = columntype;
        super.tableName = tablename;
        this.comparedValue = comapredValue;
        super.operator = operator;
        super.triggerEvents = triggerevents;
        super.messageText = messagetext;
    }

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
        return comparedValue;
    }

    public void setCompareValue(int comparedValue) {
        this.comparedValue = comparedValue;
    }

    @Override
    public String toString() {
        String text = "";
            if(ruleType.equals("tuple") || ruleType.equals("Tuple")){
                text = text + "CompareRule{" +
                        "id=" + id +
                        ", code='" + code + '\'' +
                        ", name='" + name + '\'' +
                        ", messageText='" + messageText + '\'' +
                        ", ruleType='" + ruleType + '\'' +
                        ", columnName='" + columnName + '\'' +
                        ", columnType='" + columnType + '\'' +
                        ", tableName='" + tableName + '\'' +
                        ", status='" + status + '\'' +
                        ", operator='" + operator + '\'' +
                        ", triggerEvents='" + triggerEvents + '\'' +
                        ", comparedValue='" + comparedValue + '\'' +
                        ", comapredColumn=" + comparedColumn + '\'' +
                        ", comparedTable=" + comparedTable + '\'' +
                        '}';
            } else {
                text = text +  "CompareRule{" +
                        "id=" + id +
                        ", code='" + code + '\'' +
                        ", name='" + name + '\'' +
                        ", messageText='" + messageText + '\'' +
                        ", ruleType='" + ruleType + '\'' +
                        ", columnName='" + columnName + '\'' +
                        ", columnType='" + columnType + '\'' +
                        ", tableName='" + tableName + '\'' +
                        ", status='" + status + '\'' +
                        ", operator='" + operator + '\'' +
                        ", triggerEvents='" + triggerEvents + '\'' +
                        ", comparedValue='" + comparedValue + '\'' +
                        '}';
            }
        return text; 
    }
    
    public String GenerateCode() {
    	String code = "begin";
    	if (this.ruleType == "Attribute") {
    		code = "l_passed := :new."+ this.columnName +" "+  this.operator +";\n";
    	} else if (this.ruleType == "Tuple") {
    		code = "l_passed := :new."+ this.columnName +" "+  this.operator + " new." + this.comparedColumn +";\n";
    	} else if (this.ruleType == "Inter-Entity") {
    		code = "cursor lc_ord is "+
				"\nselect ord."+ this.getComparedColumn()+
				"\nfrom " + this.getComparedTable() + " ord "+
				"\nwhere ord.id = :new.ord_id ;"+
				"\nl_orderdatum "+this.getComparedTable()+"."+this.getComparedColumn()+"%type;"+
				"\nbegin "+
				"\nopen lc_ord; "+
				"\nfetch lc_ord into l_orderdatum; "+
				"\nclose lc_ord;"+
				"\nl_passed := :new."+ this.getColumnName() +" >= l_orderdatum;";
    	} else {
    		System.out.println("Rule type niet herkend");
    	}
		return code;
    }

    @Override
	public String accept(RuleGenerator ruleGenerator) {
		return ruleGenerator.visit(this);	
	}
}
