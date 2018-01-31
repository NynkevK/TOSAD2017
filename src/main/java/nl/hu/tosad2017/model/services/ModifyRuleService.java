package nl.hu.tosad2017.model.services;

import java.util.List;
import java.sql.SQLException;

import nl.hu.tosad2017.model.model.ModifyRule;
import nl.hu.tosad2017.model.model.OracleRuleGenerator;
import nl.hu.tosad2017.model.model.OtherRule;
import nl.hu.tosad2017.persistence.tool.ToolModifyRuleDAO;
import nl.hu.tosad2017.persistence.target.OracleTargetDao;
import nl.hu.tosad2017.persistence.target.TargetModifyRuleDAO;

public class ModifyRuleService {
	ToolModifyRuleDAO ToolDAO = new ToolModifyRuleDAO();
	
	OracleTargetDao targetDAO = new OracleTargetDao();
	OracleRuleGenerator generator = new OracleRuleGenerator(); 
	
	public ModifyRuleService() {}
	
	public List<ModifyRule> getAllModifyRules() throws SQLException {
		return ToolDAO.readAllRules();
	}
	
	public ModifyRule getModifyRuleById (int id) throws SQLException {
		return ToolDAO.readRule(id);
	}
	
	public boolean defineModifyRule (ModifyRule rule) throws SQLException {
		
		return ToolDAO.createRule(rule);
	}
	
	public boolean updateModifyRule (ModifyRule rule) throws SQLException {
		targetDAO.insertTrigger(rule.accept(generator));
		return ToolDAO.updateRule(rule);
	}
	
	public boolean deleteModifyRule (int id) throws SQLException{
		ModifyRule modifyrule = ToolDAO.readRule(id);
		targetDAO.removeTrigger(modifyrule.getName());
		return ToolDAO.deleteRule(id);
	}
}
