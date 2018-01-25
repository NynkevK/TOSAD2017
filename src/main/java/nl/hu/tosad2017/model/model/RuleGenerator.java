package nl.hu.tosad2017.model.model;

public interface RuleGenerator {

	public String visit(RangeRule rule);
	public String visit(CompareRule rule);
	public String visit(OtherRule rule);
	public String visit(ModifyRule rule);
	public String visit(ListRule rule);
}
