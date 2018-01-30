package nl.hu.tosad2017.model.services;

import java.sql.SQLException;

import nl.hu.tosad2017.model.model.CompareRule;
import nl.hu.tosad2017.model.model.ListRule;
import nl.hu.tosad2017.model.model.ModifyRule;
import nl.hu.tosad2017.model.model.OracleRuleGenerator;
import nl.hu.tosad2017.model.model.OtherRule;
import nl.hu.tosad2017.model.model.RangeRule;
import nl.hu.tosad2017.persistence.target.OracleTargetDao;
import nl.hu.tosad2017.persistence.tool.ToolCompareRuleDAO;
import nl.hu.tosad2017.persistence.tool.ToolListRuleDAO;
import nl.hu.tosad2017.persistence.tool.ToolModifyRuleDAO;
import nl.hu.tosad2017.persistence.tool.ToolOtherRuleDAO;
import nl.hu.tosad2017.persistence.tool.ToolRangeRuleDAO;

public class GenerateService {
	ToolCompareRuleDAO compareDAO = new ToolCompareRuleDAO();
	ToolRangeRuleDAO rangeDAO = new ToolRangeRuleDAO();
	ToolListRuleDAO listDAO = new ToolListRuleDAO();
	ToolModifyRuleDAO modifyDAO = new ToolModifyRuleDAO();
	ToolOtherRuleDAO otherDAO = new ToolOtherRuleDAO();
	
	OracleTargetDao targetDAO = new OracleTargetDao();
	OracleRuleGenerator generator = new OracleRuleGenerator();
	
	public GenerateService() {}
	
	public String generateRule(int id, String type) throws SQLException {
		if (type == "rangerule") {
			RangeRule rangerule = rangeDAO.readRule(id);
			targetDAO.insertTrigger(rangerule.accept(generator));
		} else if (type == "comparerule") {
			CompareRule comparerule = compareDAO.readRule(id);
			targetDAO.insertTrigger(comparerule.accept(generator));
		} else if (type == "listrule") {
			ListRule listrule = listDAO.readRule(id);
			targetDAO.insertTrigger(listrule.accept(generator));
		} else if (type == "modifyrule") {
			ModifyRule modifyrule = modifyDAO.readRule(id);
			targetDAO.insertTrigger(modifyrule.accept(generator));
		} else if (type == "otherrule") {
			OtherRule otherrule = otherDAO.readRule(id);
			targetDAO.insertTrigger(otherrule.accept(generator));
		}
		
		return null;
	}
}
