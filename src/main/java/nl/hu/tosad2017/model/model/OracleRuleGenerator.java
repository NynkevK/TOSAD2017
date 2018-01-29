package nl.hu.tosad2017.model.model;

import java.text.MessageFormat;

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
		Object[] testArgs = {rule.getName(), generateTriggerEvents(rule), rule.getTableName(),rule.GenerateCode(),rule.getMessageText()};
		
		String code = triggerCode.format(testArgs);
				System.out.println("Dit is de gegenereerde trigger code: \n"+ code);
				return code;
	}

	@Override
	public String visit(CompareRule rule) {
		Object[] testArgs = {rule.getName(), generateTriggerEvents(rule), rule.getTableName(),rule.GenerateCode(),rule.getMessageText()};
		
		String code = triggerCode.format(testArgs);
				System.out.println("Dit is de gegenereerde trigger code: \n"+ code);
				return code;
	}

	@Override
	public String visit(ListRule rule) {
		Object[] testArgs = {rule.getName(), generateTriggerEvents(rule), rule.getTableName(),rule.GenerateCode(),rule.getMessageText()};
		
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
