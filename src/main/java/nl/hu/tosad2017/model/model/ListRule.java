package nl.hu.tosad2017.model.model;

public class ListRule extends BusinessRule {
	
    private String list;
    
    public ListRule(int id, String code, String name, String msg, String ruletype, String c_name, String c_type, String t_name
            ,String r_status, String r_operator, String r_triggerevents, String list) {
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
		this.list = list;
		}
    
    public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String GenerateCode() {
		String code = "l_passed := :new."+ this.columnName +" "+ this.operator +" ("+this.list+")\n";
//		String VList[] = this.getList().split(",");	
//		System.out.println(VList[0]);
//		for(int i = 0; i < VList.length; i++)
//		{
//			code = code + VList[i];
//		}
//		code = code + "\n";
		System.out.println(code);
		return code;
	}
}
