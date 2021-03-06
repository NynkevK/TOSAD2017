package nl.hu.tosad2017.webservices;

import nl.hu.tosad2017.model.model.ModifyRule;
import nl.hu.tosad2017.model.model.RangeRule;
import nl.hu.tosad2017.model.services.ServiceProvider;
import nl.hu.tosad2017.model.services.ModifyRuleService;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/modifyrule")
public class ModifyRuleResource {
	
	ModifyRuleService modifyRuleService = ServiceProvider.getModifyRuleService();	
	
	@GET
	@Produces("application/json")
	public String getAllModifyRules() throws SQLException {
				
		JsonArrayBuilder jab = Json.createArrayBuilder();
			
		//Add each modifyRule to a json object
		for (ModifyRule r : modifyRuleService.getAllModifyRules()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", r.getId());
			job.add("code", r.getCode());
			job.add("name", r.getName());
			job.add("message", r.getMessageText());
			job.add("type", r.getRuleType());
			job.add("columnName", r.getColumnName());
			job.add("columnType", r.getColumnType());
			job.add("table", r.getTableName());
			job.add("status", r.getStatus());
			job.add("operator", r.getOperator());
			job.add("triggerEvents", r.getTriggerEvents());
			job.add("query", r.getQuery());
			jab.add(job);
		}
		
		if (jab == null ) {
			throw new WebApplicationException ("No rules found!");
			}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getModifyRuleById(@PathParam("id") String id) throws SQLException {
		
		Integer idInt = Integer.parseInt(id);
		ModifyRule r = modifyRuleService.getModifyRuleById(idInt);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", r.getId());
		job.add("code", r.getCode());
		job.add("name", r.getName());
		job.add("message", r.getMessageText());
		job.add("type", r.getRuleType());
		job.add("columnName", r.getColumnName());
		job.add("columnType", r.getColumnType());
		job.add("table", r.getTableName());
		job.add("status", r.getStatus());
		job.add("operator", r.getOperator());
		job.add("triggerEvents", r.getTriggerEvents());
		job.add("query", r.getQuery());
		
		return job.build().toString();
	}
	
	@POST
	@Produces("application/json")
	public Response defineModifyRule(
								@QueryParam("code") String code,
								@QueryParam("name") String name, 
								@QueryParam("message") String message,
								@QueryParam("type") String type,
								@QueryParam("columnName") String columnName,
								@QueryParam("columnType") String columnType,
								@QueryParam("table") String table,
								@QueryParam("status") String status, 
								@QueryParam("operator") String operator,
								@QueryParam("triggerEvents") String triggerEvents,
								@QueryParam("query") String query) throws SQLException {
		
		String queryString = query;
		
		ModifyRule newRule = new ModifyRule(code, name, message, type,
											columnName, columnType, table,
											status, operator, triggerEvents,
											queryString);
		
		if(modifyRuleService.defineModifyRule(newRule) == true) {
			return Response.ok(true).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}
	
	@PUT
	@Path("{id}")
	@Produces("application/json")
	public boolean updateModifyRule(@PathParam("id") String id,
								@QueryParam("code") String code,
								@QueryParam("name") String name, 
								@QueryParam("message") String message,
								@QueryParam("type") String type,
								@QueryParam("columnName") String columnName,
								@QueryParam("columnType") String columnType,
								@QueryParam("table") String table,
								@QueryParam("status") String status, 
								@QueryParam("operator") String operator,
								@QueryParam("triggerEvents") String triggerEvents,
								@QueryParam("ModifyValue") String ModifyValue) throws SQLException {
		
		Integer idInt = Integer.parseInt(id);
		String queryString = ModifyValue;
		
		ModifyRule oldRule = modifyRuleService.getModifyRuleById(idInt);
		
		oldRule.setName(name);
		oldRule.setCode(code);
		oldRule.setMessageText(message);
		oldRule.setRuleType(type);
		oldRule.setColumnName(columnName);
		oldRule.setColumnType(columnType);
		oldRule.setTableName(table);
		oldRule.setStatus(status);
		oldRule.setOperator(operator);
		oldRule.setTriggerEvents(triggerEvents);
		oldRule.setQuery(queryString);
		
		return modifyRuleService.updateModifyRule(oldRule);		
		
	}
	
	@DELETE
	@Path("{id}")
	public boolean deleteModifyRule (@PathParam("id") String id) throws SQLException {	
		Integer idInt = Integer.parseInt(id);
		return modifyRuleService.deleteModifyRule(idInt);
	}
}