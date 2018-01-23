package nl.hu.tosad2017.model.model;

public abstract class BusinessRule {
    protected int id;
    protected String code;
    protected String name;
    protected String messageText;
    protected String ruleType;
    protected String columnName;
    protected String columnType;
    protected String tableName;
    protected String status;
    protected String operator;
    protected String triggerEvents;


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    
    public String getGeneratedName() {
    	String name = "BRG_" + "VBMG_" + tableName + "_" + "TRG_" + code + "_"  + id;
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTriggerEvents() {
        return triggerEvents;
    }

    public void setTriggerEvents(String triggerEvents) {
        this.triggerEvents = triggerEvents;
    }
}
