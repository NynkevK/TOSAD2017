package nl.hu.tosad2017.model.model;

public class ModifyRule extends BusinessRule {
    private String query;

    public ModifyRule (int id, String code, String name, String msg, String ruletype, String c_name, String c_type, String t_name
            ,String r_status, String r_operator, String r_triggerevents, String query) {
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
        this.query = query;
    }

    public ModifyRule (String code, String name, String msg, String ruletype, String c_name, String c_type, String t_name
            , String r_status, String r_operator, String r_triggerevents, String query) {
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
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "ModifyRule{" +
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
                '}';
    }
    
    @Override
	public String accept(RuleGenerator ruleGenerator) {
		return ruleGenerator.visit(this);	
	}

}
