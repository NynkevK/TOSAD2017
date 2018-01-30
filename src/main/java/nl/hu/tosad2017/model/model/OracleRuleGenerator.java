package nl.hu.tosad2017.model.model;

import java.text.MessageFormat;

import nl.hu.tosad2017.persistence.target.OracleTargetDao;

public class OracleRuleGenerator implements RuleGenerator {
	
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
		String l_passed = "begin\nl_passed := :new."+ rule.getColumnName() +" "+  rule.getOperator() + " " + rule.getMinValue() + " and "+ rule.getMaxValue() +";\n";
		Object[] testArgs = {rule.getName(), generateTriggerEvents(rule), rule.getTableName(),l_passed,rule.getMessageText()};
		
		String code = triggerCode.format(testArgs);
				System.out.println("Dit is de gegenereerde trigger code: \n"+ code);
				return code;
	}

	@Override
	public String visit(CompareRule rule) {
	    String l_passed = "begin\n";
	    if (rule.getRuleType() == "Attribute") {
	    	l_passed += "l_passed := :new."+ rule.getColumnName() +" "+  rule.getOperator() + " " + rule.getValue() +";\n";
	    } else if (rule.getRuleType() == "Tuple") {
	    	l_passed += "l_passed := :new."+ rule.getColumnName() +" "+  rule.getOperator() + " new." + rule.getComparedColumn() +";\n";
	    } else if (rule.getRuleType() == "Inter-Entity") {
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
				"\nl_passed := :new."+ rule.getColumnName() +" "+ rule.getOperator() +" l_compare;";
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
	    String l_passed = "begin\nl_passed := :new."+ rule.columnName +" "+ rule.operator +" ("+rule.getList()+");\n";
		
		Object[] testArgs = {rule.getName(), generateTriggerEvents(rule), rule.getTableName(),l_passed,rule.getMessageText()};
		
		String code = triggerCode.format(testArgs);
				System.out.println("Dit is de gegenereerde trigger code: \n"+ code);
				return code;
	}
	
	public String generateTriggerEvents(BusinessRule rule) {
		String triggers[] = rule.getTriggerEvents().split("\\s+");
		String events = "before";
		
		//De triggerEvents parsen
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

	@Override
	public String visit(OtherRule rule) {
		// Nog niet geïmplementeerd
		return null;
	}

	@Override
	public String visit(ModifyRule rule) {
		// Nog niet geïmplementeerd
		return null;
	}

}
