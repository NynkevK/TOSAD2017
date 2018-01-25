package nl.hu.tosad2017.model.model;

public interface RuleGenerator {

	public String GenerateRangeRule(RangeRule rule);
	public String GenerateCompareRule(CompareRule rule);
	public String GenerateOtherRule(OtherRule rule);
	public String GenerateModifyRule(ModifyRule rule);
	public String GenerateListRule(ListRule rule);
}
