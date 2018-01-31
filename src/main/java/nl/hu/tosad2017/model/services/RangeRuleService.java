package nl.hu.tosad2017.model.services;

import java.sql.SQLException;
import java.util.List;

import nl.hu.tosad2017.model.model.OracleRuleGenerator;
import nl.hu.tosad2017.model.model.RangeRule;
import nl.hu.tosad2017.persistence.target.OracleTargetDao;
import nl.hu.tosad2017.persistence.tool.ToolRangeRuleDAO;

//import java.sql.*;

public class RangeRuleService {
	ToolRangeRuleDAO ToolDAO = new ToolRangeRuleDAO();
	
	OracleTargetDao targetDAO = new OracleTargetDao();
	OracleRuleGenerator generator = new OracleRuleGenerator(); 
	
	public RangeRuleService() {}
	
	public List<RangeRule> getAllRangeRules() throws SQLException {
		return ToolDAO.readAllRules(); 
	}
	
	public RangeRule getRangeRuleById(int id) throws SQLException {
		return ToolDAO.readRule(id);
	}
	
	public boolean defineRangeRule(RangeRule rule) throws SQLException {
		return ToolDAO.createRule(rule);
	}
	
	public boolean updateRangeRule(RangeRule rule) throws SQLException {
		targetDAO.insertTrigger(rule.accept(generator));
		return ToolDAO.updateRule(rule);
	}
	
	public boolean deleteRangeRule(int id) throws SQLException {
		RangeRule rangerule = ToolDAO.readRule(id);
		targetDAO.removeTrigger(rangerule.getName());
		return ToolDAO.deleteRule(id);
	}
}
