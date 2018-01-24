package nl.hu.tosad2017.model.model;

public class OtherRule extends BusinessRule {
    private String query;
    private String otherColumn;


    public OtherRule (int id, String code, String name, String ruletype, String query, String status, String columnname, String columntype,
                      String tablename, String otherColumn, String operator, String triggerevents, String messagetext) {
        super.id = id;
        super.code = code;
        super.name = name;
        super.ruleType = ruletype;
        this.query = query;
        super.status = status;
        super.columnName = columnname;
        super.columnType = columntype;
        super.tableName = tablename;
        this.otherColumn = otherColumn;
        super.operator = operator;
        super.triggerEvents = triggerevents;
        super.messageText = messagetext;
    }

    public OtherRule (String code, String name, String ruletype, String query, String status, String columnname, String columntype,
                      String tablename, String otherColumn, String operator, String triggerevents, String messagetext) {
        super.code = code;
        super.name = name;
        super.ruleType = ruletype;
        this.query = query;
        super.status = status;
        super.columnName = columnname;
        super.columnType = columntype;
        super.tableName = tablename;
        this.otherColumn = otherColumn;
        super.operator = operator;
        super.triggerEvents = triggerevents;
        super.messageText = messagetext;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOtherColumn() {
        return otherColumn;
    }

    public void setOtherColumn(String otherColumn) {
        this.otherColumn = otherColumn;
    }


    @Override
    public String toString() {
        return "OtherRule{" +
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
                "query='" + query + '\'' +
                ", otherColumn='" + otherColumn + '\'' +
                '}';
    }
}
