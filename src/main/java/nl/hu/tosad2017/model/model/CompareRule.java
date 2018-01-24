package nl.hu.tosad2017.model.model;

public class CompareRule extends BusinessRule {
    private String comparedColumn;
    private String comparedTable;
    private int comparedValue;
    private int value;

    public CompareRule(int id, String code, String name, String ruletype, String status, String columnname, String columntype,
                       String tablename, int value, String comparedColumn, String comparedTable, int comapredValue, String operator,
                       String triggerevents, String messagetext) {
        super.id = id;
        super.code = code;
        super.name = name;
        super.ruleType = ruletype;
        super.status = status;
        super.columnName = columnname;
        super.columnType = columntype;
        super.tableName = tablename;
        this.value = value;
        this.comparedColumn = comparedColumn;
        this.comparedTable = comparedTable;
        this.comparedValue = comapredValue;
        super.operator = operator;
        super.triggerEvents = triggerevents;
        super.messageText = messagetext;
    }

    public CompareRule(String code, String name, String ruletype, String status, String columnname, String columntype,
                       String tablename, int value, String comparedColumn, String comparedTable, int comapredValue, String operator,
                       String triggerevents, String messagetext) {
        super.code = code;
        super.name = name;
        super.ruleType = ruletype;
        super.status = status;
        super.columnName = columnname;
        super.columnType = columntype;
        super.tableName = tablename;
        this.value = value;
        this.comparedColumn = comparedColumn;
        this.comparedTable = comparedTable;
        this.comparedValue = comapredValue;
        super.operator = operator;
        super.triggerEvents = triggerevents;
        super.messageText = messagetext;
    }

    public CompareRule(int id, String code, String name, String ruletype, String status, String columnname, String columntype,
                       String tablename, int value, int comapredValue, String operator,
                       String triggerevents, String messagetext) {
        super.id = id;
        super.code = code;
        super.name = name;
        super.ruleType = ruletype;
        super.status = status;
        super.columnName = columnname;
        super.columnType = columntype;
        super.tableName = tablename;
        this.value = value;
        this.comparedValue = comapredValue;
        super.operator = operator;
        super.triggerEvents = triggerevents;
        super.messageText = messagetext;
    }

    public CompareRule(String code, String name, String ruletype, String status, String columnname, String columntype,
                       String tablename, int value, int comapredValue, String operator,
                       String triggerevents, String messagetext) {
        super.code = code;
        super.name = name;
        super.ruleType = ruletype;
        super.status = status;
        super.columnName = columnname;
        super.columnType = columntype;
        super.tableName = tablename;
        this.value = value;
        this.comparedValue = comapredValue;
        super.operator = operator;
        super.triggerEvents = triggerevents;
        super.messageText = messagetext;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
                        ", value='" + value + '\'' +
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
                        ", value='" + value + '\'' +
                        ", comparedValue='" + comparedValue + '\'' +
                        '}';
            }
        return text; 
    }
}
