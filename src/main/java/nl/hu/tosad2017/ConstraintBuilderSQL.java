package nl.hu.tosad2017;

public class ConstraintBuilderSQL{

	public String buildCreatePart(String name, String tablename) {
		String code = "create or replace trigger "+ name + " \n"+
		 "before delete or insert or update \n"+
		 "on " + tablename +" \n"+
		 "for each row\n";
		return code;
	}	
	
	public String makeCodePart(int min, int max, String operator, String column) {
		String code = "declare\n"+
				"l_passed boolean := true;\n" +
				"l_error_stack varchar2 ( 4000 );\n" +
				"begin \n" +
				"l_passed := :new."+ column +" "+  operator + " " + min + " and "+ max +";\n";
		return code;
	}
	
	public String buildExceptionPart(String error) {
		String code = "if not l_passed \n" +
		 "then \n" +
		 "l_error_stack := l_error_stack || '"+error+"'; \n" +
		 "end if;\nend;";
		return code;

	}

	
	public String makeBusinessRule() {
		String code = "";
		code = code + buildCreatePart("vbmg_voorbeeld", "connection_test") + makeCodePart(20, 40, "between", "COLUMN1") + buildExceptionPart("mag alleen tussen min en max zijn");
		System.out.println(code);
		return code;
	}

}
