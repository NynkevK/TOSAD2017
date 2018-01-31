package nl.hu.tosad2017.model.model;

import java.text.MessageFormat;

import nl.hu.tosad2017.persistence.target.OracleTargetDao;

public class OracleRuleGenerator implements RuleGenerator {
	
	//Template voor de te genereren trigger code
	MessageFormat triggerCode = new MessageFormat("create or replace trigger {0} \n"+
			 "{1}  \n"+
			 "on {2} \n"+
			 "for each row\n" +
			"declare\n"+
			"l_passed boolean := true;\n" +
			"l_error_stack varchar2 ( 4000 );\n" +
			"{3} " +
			"if not l_passed \n" +
			"then \n" +
			"l_error_stack := l_error_stack || '' {4} ''; \n" +
			"end if;\n" +
			"if l_error_stack is not null\n" +
			"then\n" +
			"raise_application_error ( -20800, l_error_stack );\n" +
			"end if;\n" +
			"end;");
	

	@Override
	public String visit(RangeRule rule) {
		String l_passed = "begin\nl_passed := :new."+ rule.getColumnName() +" "+  generateOperator(rule) + " " + rule.getMinValue() + " and "+ rule.getMaxValue() +";\n";
		Object[] testArgs = {rule.getName(), generateTriggerEvents(rule), rule.getTableName(),l_passed,rule.getMessageText()};
		
		String code = triggerCode.format(testArgs);
				System.out.println("Dit is de gegenereerde trigger code: \n"+ code);
				return code;
	}

	@Override
	public String visit(CompareRule rule) {
	    String l_passed = "begin\n";
	    //Attribute compare rule generatie
	    if (rule.getRuleType().equalsIgnoreCase("Attribute")) {
	    	l_passed += "l_passed := :new."+ rule.getColumnName() +" "+  generateOperator(rule) + " " + rule.getCompareValue() +";\n";
	    }
	    //Tuple compare rule generatie
	    else if (rule.getRuleType().equalsIgnoreCase("Tuple")) {
	    	l_passed += "l_passed := :new."+ rule.getColumnName() +" "+  generateOperator(rule) + " new." + rule.getComparedColumn() +";\n";
	    } 
	    //Inter-Entity rule generatie
	    else if (rule.getRuleType().equalsIgnoreCase("Inter-Entity")) {
	    	OracleTargetDao dao = new OracleTargetDao();
            String constraintName = rule.getTableName().substring(0,8)+"_"+rule.getComparedTable().substring(5,8)+"_FK"; 
            l_passed = "cursor lc_tab is "+
				"\nselect tab."+ rule.getComparedColumn()+
				"\nfrom " + rule.getComparedTable() + " tab "+
				"\nwhere tab.id = :new."+dao.getForeignKey(constraintName)+" ;"+
				"\nl_compare "+rule.getComparedTable()+"."+rule.getComparedColumn()+"%type;"+
				"\nbegin "+
				"\nopen lc_tab; "+
				"\nfetch lc_tab into l_compare; "+
				"\nclose lc_tab;"+
				"\nl_passed := :new."+ rule.getColumnName() +" "+ generateOperator(rule) +" l_compare;";
	    } else {
	    	System.out.println("Rule type niet herkend");
	    }
	    
		Object[] testArgs = {rule.getName(), generateTriggerEvents(rule), rule.getTableName(),l_passed,rule.getMessageText()};
		String code = triggerCode.format(testArgs);
		System.out.println("Dit is de gegenereerde trigger code: \n"+ code);
		return code;
	}

	@Override
	public String visit(ListRule rule) {
		String VList[] = rule.getList().split(",");	
		String ret = "";
		try
	    {
	        Integer.parseInt(VList[0]);
	        ret = rule.getList();
	    } catch (NumberFormatException ex)
	    {
	    	for(int i = 0; i < VList.length; i++){
	        ret = ret +"'"+ VList[i]+"'";
	        if (VList.length > 0 && i+1 < VList.length){
				ret = ret + ", ";
			}
	    	}
	    }
		System.out.println(VList[0]);
	    String l_passed = "begin\nl_passed := :new."+ rule.getColumnName() +" "+ generateOperator(rule) +" ("+ret+");\n";
		
		Object[] testArgs = {rule.getName(), generateTriggerEvents(rule), rule.getTableName(),l_passed,rule.getMessageText()};
		
		String code = triggerCode.format(testArgs);
		System.out.println("Dit is de gegenereerde trigger code: \n"+ code);
		return code;
	}
	
	public String generateTriggerEvents(BusinessRule rule) {
		//De triggerEvents parsen
		String triggers[] = rule.getTriggerEvents().split("\\s+");
		String events = "before";
		
		for(int i = 0; i < triggers.length; i++)
		{
			if (triggers[i].equals("INS")) {
				events = events + " insert";
			} else if (triggers[i].equals("UPD")) {
				events = events + " update";
			} else if (triggers[i].equals("DEL")) {
				events = events + " delete";
			}
			if (triggers.length > 0 && i+1 < triggers.length){
				events = events + " or";
			}
		}
		return events;
	}
	
	public String generateOperator(BusinessRule rule) {
		//Operator bepalen voor generatie trigger
		String operator = rule.getOperator();
		if (operator.equals("equals")){operator = "=";}
		else if (operator.equalsIgnoreCase("notequals")){operator = "!=";}
		else if (operator.equalsIgnoreCase("lesthanorequal")){operator = "<=";}
		else if (operator.equalsIgnoreCase("greaterthanorequal")){operator = ">=";}
		else if (operator.equalsIgnoreCase("lesthan")){operator = "<";}
		else if (operator.equalsIgnoreCase("greaterthan")){operator = ">";}
		else if (operator.equalsIgnoreCase("between")){operator = "between";}
		else if (operator.equalsIgnoreCase("notbetween")){operator = "not between";}
		else if (operator.equalsIgnoreCase("in")){operator = "in";}
		else if (operator.equalsIgnoreCase("notin")){operator = "not in";}
		else {System.out.print("Operator not found");};
		
		return operator;
	}

	@Override
	public String visit(OtherRule rule) {
		String l_passed = "l_aantal pls_integer; "+
		"\nbegin"+
		"\nselect count(*)"+
		"\ninto l_aantal"+
		"\nfrom "+rule.getTableName()+
		"\nwhere "+rule.getColumnName()+" "+generateOperator(rule)+" "+ rule.getOtherColumn()+";"+
		"\nl_passed := l_aantal <= "+rule.getQuery()+";";
		
		Object[] testArgs = {rule.getName(), generateTriggerEvents(rule), rule.getTableName(),l_passed,rule.getMessageText()};
		
		String code = triggerCode.format(testArgs);
				System.out.println("Dit is de gegenereerde trigger code: \n"+ code);
				return code;
	}

	@Override
	public String visit(ModifyRule rule) {
		String l_passed = "begin"+ 
				 "if "+ rule.getColumnName() +" = "+ rule.getColumnType() +" and"+
				 "l_oper = 'UPD'"+ 
				 "then"+
				 ""+rule.getQuery()+
				 "end if;";

				
		Object[] testArgs = {rule.getName(), generateTriggerEvents(rule), rule.getTableName(),l_passed,rule.getMessageText()};
				
		String code = triggerCode.format(testArgs);
		System.out.println("Dit is de gegenereerde trigger code: \n"+ code);
		return code;
	}

}
