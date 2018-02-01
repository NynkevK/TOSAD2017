package nl.hu.tosad2017.model.services;

import java.sql.SQLException;
import java.util.List;

import nl.hu.tosad2017.model.model.OracleRuleGenerator;
import nl.hu.tosad2017.model.model.OtherRule;
import nl.hu.tosad2017.persistence.target.OracleTargetDao;
import nl.hu.tosad2017.persistence.tool.ToolOtherRuleDAO;

public class OtherRuleService {
	ToolOtherRuleDAO ToolDAO = new ToolOtherRuleDAO();
	OracleTargetDao targetDAO = new OracleTargetDao();
	OracleRuleGenerator generator = new OracleRuleGenerator(); 
	
	public OtherRuleService() {}
	
	public List<OtherRule> getAllOtherRules() throws SQLException {
		return  ToolDAO.readAllRules(); 
	}
	
	public OtherRule getOtherRuleById(int id) throws SQLException {
		return ToolDAO.readRule(id);
	}
	
	public boolean defineOtherRule(OtherRule rule) throws SQLException {
		return ToolDAO.createRule(rule);
	}
	
	public boolean updateOtherRule(OtherRule rule) throws SQLException {
		targetDAO.insertTrigger(rule.accept(generator));
		return ToolDAO.updateRule(rule);
	}
	
	public boolean deleteOtherRule(int id) throws SQLException {
		OtherRule otherrule = ToolDAO.readRule(id);
		if(otherrule.getStatus().equalsIgnoreCase("generated")){
			targetDAO.removeTrigger(otherrule.getName());
		}
		return ToolDAO.deleteRule(id);
	}
}

