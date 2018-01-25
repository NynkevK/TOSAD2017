package nl.hu.tosad2017.model.services;

import java.sql.SQLException;
import java.util.List;

import nl.hu.tosad2017.model.model.OtherRule;
import nl.hu.tosad2017.persistence.target.TargetOtherRuleDAO;
import nl.hu.tosad2017.persistence.tool.ToolOtherRuleDAO;

public class OtherRuleService {
	ToolOtherRuleDAO ToolDAO = new ToolOtherRuleDAO();
	TargetOtherRuleDAO TargetDAO = new TargetOtherRuleDAO();
	
	public OtherRuleService() {}
	
	public List<OtherRule> getAllOtherRules() throws SQLException {
		return  null; //ToolDAO.readAllRules(); 
	}
	
	public OtherRule getOtherRuleById(int id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing OtherRule Service (GET) for " + id);
		return null; //ToolDAO.readRule(id);
	}
	
	public boolean defineOtherRule(OtherRule rule) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing OtherRule Service (CREATE)");
		return true; //ToolDAO.createRule(rule);
	}
	
	public OtherRule updateOtherRule(int id) {
		// logging for Heroku application server
		System.out.println(".. executing OtherRule Service (UPDATE) for " + id);
		//TODO Implement updateRule in DAO
		//RangeRule updatedRule = ToolDAO.updateRule(id);
		return null;
		//return updatedRule;
	}
	
	public boolean deleteOtherRule(int id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing OtherRule Service (DELETE) for " + id);
		return true; //ToolDAO.deleteRule(id);
	}
}

