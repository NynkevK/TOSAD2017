package nl.hu.tosad2017.model.services;

import java.util.List;
import java.sql.SQLException;

import nl.hu.tosad2017.model.model.ModifyRule;
import nl.hu.tosad2017.model.model.OracleRuleGenerator;
import nl.hu.tosad2017.persistence.tool.ToolModifyRuleDAO;
import nl.hu.tosad2017.persistence.target.OracleTargetDao;

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
		if(rule.getStatus().equalsIgnoreCase("generated")){
			targetDAO.insertTrigger(rule.accept(generator));
		}
		return ToolDAO.updateRule(rule);
	}
	
	public boolean deleteModifyRule (int id) throws SQLException{
		ModifyRule modifyrule = ToolDAO.readRule(id);
		if(modifyrule.getStatus().equalsIgnoreCase("generated")){
			targetDAO.removeTrigger(modifyrule.getName());
		}
		return ToolDAO.deleteRule(id);
	}
}
