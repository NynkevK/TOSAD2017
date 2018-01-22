package nl.hu.tosad2017.model.model;

public class RangeRule extends BusinessRule {
    private int minValue;
    private int maxValue;

    public RangeRule (int id, String code, String name, String msg, String ruletype, String c_name, String c_type, String t_name
                        ,String r_status, String r_operator, String r_triggerevents, int minval, int maxval) {
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
        this.minValue = minval;
        this.maxValue = maxval;
    }

    public RangeRule (String code, String name, String msg, String ruletype, String c_name, String c_type, String t_name
            ,String r_status, String r_operator, String r_triggerevents, int minval, int maxval) {
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
        this.minValue = minval;
        this.maxValue = maxval;
    }

    public RangeRule() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return "RangeRule{" +
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
                "minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }

    public int getMinValue() { return minValue; }

    public void setMinValue(int minValue) { this.minValue = minValue; }

    public int getMaxValue() { return maxValue; }

    public void setMaxValue(int maxValue) { this.maxValue = maxValue; }
}
