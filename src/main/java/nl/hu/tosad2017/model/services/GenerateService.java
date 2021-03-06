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
		if (type.equals("rangerule")) {
			RangeRule rangerule = rangeDAO.readRule(id);
			targetDAO.insertTrigger(rangerule.accept(generator));
			rangerule.setStatus("generated");
			rangeDAO.updateRule(rangerule);
		} else if (type.equals("comparerule")) {
			CompareRule comparerule = compareDAO.readRule(id);
			targetDAO.insertTrigger(comparerule.accept(generator));
			comparerule.setStatus("generated");
			compareDAO.updateRule(comparerule);
		} else if (type.equals("listrule")) {
			ListRule listrule = listDAO.readRule(id);
			targetDAO.insertTrigger(listrule.accept(generator));
			listrule.setStatus("generated");
			listDAO.updateRule(listrule);
		} else if (type.equals("modifyrule")) {
			ModifyRule modifyrule = modifyDAO.readRule(id);
			targetDAO.insertTrigger(modifyrule.accept(generator));
			modifyrule.setStatus("generated");
			modifyDAO.updateRule(modifyrule);
		} else if (type.equals("otherrule")) {
			OtherRule otherrule = otherDAO.readRule(id);
			targetDAO.insertTrigger(otherrule.accept(generator));
			otherrule.setStatus("generated");
			otherDAO.updateRule(otherrule);
		}
		
		return "service success";
	}
	
	public String deleteRule(int id, String type) throws SQLException {
		if (type.equals("rangerule")) {
			RangeRule rangerule = rangeDAO.readRule(id);
			targetDAO.removeTrigger(rangerule.getName());
			rangeDAO.deleteRule(id);
		} else if (type.equals("comparerule")) {
			CompareRule comparerule = compareDAO.readRule(id);
			targetDAO.removeTrigger(comparerule.getName());
			compareDAO.deleteRule(id);
		} else if (type.equals("listrule")) {
			ListRule listrule = listDAO.readRule(id);
			targetDAO.removeTrigger(listrule.getName());
			listDAO.deleteRule(id);
		} else if (type.equals("modifyrule")) {
			ModifyRule modifyrule = modifyDAO.readRule(id);
			targetDAO.removeTrigger(modifyrule.getName());
			modifyDAO.deleteRule(id);
		} else if (type.equals("otherrule")) {
			OtherRule otherrule = otherDAO.readRule(id);
			targetDAO.removeTrigger(otherrule.getName());
			otherDAO.deleteRule(id);
		}
		
		return "service success";
	} 
}
