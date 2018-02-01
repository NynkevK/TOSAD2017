package nl.hu.tosad2017.model.services;

import java.sql.SQLException;
import java.util.List;

import nl.hu.tosad2017.model.model.ListRule;
import nl.hu.tosad2017.model.model.OracleRuleGenerator;
import nl.hu.tosad2017.persistence.tool.ToolListRuleDAO;
import nl.hu.tosad2017.persistence.target.OracleTargetDao;

public class ListRuleService {
	ToolListRuleDAO ToolDAO = new ToolListRuleDAO();
	
	OracleTargetDao targetDAO = new OracleTargetDao();
	OracleRuleGenerator generator = new OracleRuleGenerator(); 
	
	public ListRuleService() {}
	
	public List<ListRule> getAllListRules() throws SQLException {
		return ToolDAO.readAllRules();
	}
	
	public ListRule getListRuleById (int id) throws SQLException {
		
		return ToolDAO.readRule(id);
	}
	
	public boolean defineListRule (ListRule rule) throws SQLException {
		
		return ToolDAO.createRule(rule);
	}
	
	public boolean updateListRule (ListRule rule) throws SQLException {
		targetDAO.insertTrigger(rule.accept(generator));
		return ToolDAO.updateRule(rule);
	}
	
	public boolean deleteListRule (int id) throws SQLException {
		ListRule listrule = ToolDAO.readRule(id);
		if(listrule.getStatus().equalsIgnoreCase("generated")){
			targetDAO.removeTrigger(listrule.getName());
		}
		return ToolDAO.deleteRule(id);
	}
}
