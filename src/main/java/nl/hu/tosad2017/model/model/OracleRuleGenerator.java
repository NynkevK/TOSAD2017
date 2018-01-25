package nl.hu.tosad2017.model.model;

public class OracleRuleGenerator implements RuleGenerator {

	@Override
	public String visit(RangeRule rule) {
		String code = "create or replace trigger "+ rule.getName() + " \n"+
				 generateTriggerEvents(rule) +"  \n"+
				 "on " + rule.getTableName() +" \n"+
				 "for each row\n" +
				"declare\n"+
				"l_passed boolean := true;\n" +
				"l_error_stack varchar2 ( 4000 );\n" +
				"begin \n" +
				 rule.GenerateCode() +
				 "if not l_passed \n" +
				 "then \n" +
				 "l_error_stack := l_error_stack || '"+rule.getMessageText()+"'; \n" +
				 "end if;\n" +
				 "if l_error_stack is not null\n" +
				 "then\n" +
				 "raise_application_error ( -20800, l_error_stack );\n" +
				 "end if;\n" +
				 "end;";
				System.out.println(code);
				return code;
	}

	@Override
	public String visit(CompareRule rule) {
		String code = "create or replace trigger "+ rule.getName() + " \n"+
				 generateTriggerEvents(rule) +"  \n"+
				 "on " + rule.getTableName() +" \n"+
				 "for each row\n" +
				"declare\n"+
				"l_passed boolean := true;\n" +
				"l_error_stack varchar2 ( 4000 );\n" +
				"begin \n" +
				rule.GenerateCode() +
				"if not l_passed \n" +
				 "then \n" +
				 "l_error_stack := l_error_stack || '"+rule.getMessageText()+"'; \n" +
				 "end if;\n" +
				 "if l_error_stack is not null\n" +
				 "then\n" +
				 "raise_application_error ( -20800, l_error_stack );\n" +
				 "end if;\n" +
				 "end;";
				System.out.println(code);
				return code;
	}

	@Override
	public String visit(OtherRule rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ModifyRule rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ListRule rule) {
		String code = "create or replace trigger "+ rule.getName() + " \n"+
				 generateTriggerEvents(rule) +"  \n"+
				 "on " + rule.getTableName() +" \n"+
				 "for each row\n" +
				"declare\n"+
				"l_passed boolean := true;\n" +
				"l_error_stack varchar2 ( 4000 );\n" +
				"begin \n" +
				rule.GenerateCode() +
				"if not l_passed \n" +
				 "then \n" +
				 "l_error_stack := l_error_stack || '"+rule.getMessageText()+"'; \n" +
				 "end if;\n" +
				 "if l_error_stack is not null\n" +
				 "then\n" +
				 "raise_application_error ( -20800, l_error_stack );\n" +
				 "end if;\n" +
				 "end;";
				System.out.println(code);
				return code;
	}
	
	public String generateTriggerEvents(BusinessRule rule) {
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

}
