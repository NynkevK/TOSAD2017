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
    
    public String GenerateCode() {
    	String code = "";
    		code = "l_passed := :new."+ this.getColumnName() +" "+  this.getOperator() + " " + this.getMinValue() + " and "+ this.getMaxValue() +";\n";
		return code;
    }
    
    @Override
	public String accept(RuleGenerator ruleGenerator) {
		return ruleGenerator.visit(this);	
	}

//	public String generateTriggerEvents() {
//		String triggers[] = this.getTriggerEvents().split("\\s+");
//		String events = "before";
//		for(int i = 0; i < triggers.length; i++)
//		{
//			if (triggers[i].equals("INS")) {
//				events = events + " insert";
//			} else if (triggers[i].equals("UPD")) {
//				events = events + " update";
//			} else if (triggers[i].equals("DEL")) {
//				events = events + " delete";
//			}
//			if (triggers.length > 0 && i+1 < triggers.length){
//				events = events + " or";
//			}
//		    System.out.println(triggers[i]);
//		}
//		return events;
//	}
}
