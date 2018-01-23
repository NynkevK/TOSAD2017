package nl.hu.tosad2017.model.model;

public class OracleRuleGenerator implements RuleGenerator {

	@Override
	public String GenerateRangeRule(RangeRule rule) {
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
				 "end if;\nend;";
				System.out.println(code);
				return code;
	}

	@Override
	public String GenerateCompareRule(CompareRule rule) {
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
				 "end if;\nend;";
				System.out.println(code);
				return code;
	}

	@Override
	public String GenerateOtherRule(OtherRule rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GenerateModifyRule(ModifyRule rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GenerateListRule(ListRule rule) {
		// TODO Auto-generated method stub
		return null;
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
		    System.out.println(triggers[i]);
		}
		return events;
	}

}
