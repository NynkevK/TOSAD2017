package nl.hu.tosad2017.model.services;

import nl.hu.tosad2017.persistence.tool.ToolCompareRuleDAO;

import java.sql.SQLException;
import java.util.List;

import nl.hu.tosad2017.model.model.CompareRule;
import nl.hu.tosad2017.model.model.OracleRuleGenerator;
import nl.hu.tosad2017.persistence.target.OracleTargetDao;

public class CompareRuleService {
	ToolCompareRuleDAO ToolDAO = new ToolCompareRuleDAO();
	
	OracleTargetDao targetDAO = new OracleTargetDao();
	OracleRuleGenerator generator = new OracleRuleGenerator(); 
	
	public CompareRuleService() {}
	
	public List<CompareRule> getAllCompareRules() throws SQLException {
		return ToolDAO.readAllRules();
	}
	
	public CompareRule getCompareRuleById (int id) throws SQLException {
		
		return ToolDAO.readRule(id);
	}
	
	public boolean defineCompareRule (CompareRule rule) throws SQLException {
		
		return ToolDAO.createRule(rule);
	}
	
	public boolean updateCompareRule (CompareRule rule) throws SQLException {
		targetDAO.insertTrigger(rule.accept(generator));
		return ToolDAO.updateRule(rule);
	}
	
	public boolean deleteCompareRule (int id) throws SQLException {
		CompareRule comparerule = ToolDAO.readRule(id);
		targetDAO.removeTrigger(comparerule.getName());
		return ToolDAO.deleteRule(id);
	}
}
