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
			job.add("otherTable", r.getOtherTable());
			job.add("otherColumn", r.getOtherColumn());
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
		job.add("otherTable", r.getOtherTable());
		job.add("otherColumn", r.getOtherColumn());
		
		return job.build().toString();
	}
	
	@POST
	@Produces("application/json")
	public Response defineModifyRule(@QueryParam("id") String id,
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
								@QueryParam("query") String query,
								@QueryParam("otherTable") String otherTable,
								@QueryParam("otherColumn") String otherColumn) throws SQLException {
		
		
		Integer idInt = Integer.parseInt(code);
		String queryString = query;
		String otherTableString = otherTable;
		String otherColumnString = otherColumn;
		
		//TODO add all other parameters for ModifyRule in constructor below
		ModifyRule newRule = new ModifyRule(idInt, code, name, message, type,
											columnName, columnType, table,
											status, operator, triggerEvents,
											queryString, otherTableString, otherColumnString);
		
		if(modifyRuleService.getModifyRuleById(idInt) == null) {
			boolean returnedRule = modifyRuleService.defineModifyRule(newRule);
			return Response.ok(returnedRule).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}
	
	@PUT
	@Path("{id}")
	@Produces("application/json")
	public String updateModifyRule(@PathParam("id") String id,
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
								@QueryParam("ModifydTable") String ModifydTable,
								@QueryParam("ModifydColumn") String ModifydColumn,
								@QueryParam("ModifyValue") String ModifyValue) throws SQLException {
		
		Integer idInt = Integer.parseInt(id);
		String queryString = ModifyValue;
		String otherTableString = ModifydTable;
		String otherColumnString = ModifydColumn;
		
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
		oldRule.setOtherTable(otherTableString);
		oldRule.setOtherColumn(otherColumnString);
		
		ModifyRule newRule = modifyRuleService.updateModifyRule(idInt);		
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		//Add all rule attributes to a json object
		job.add("id", newRule.getId());
		job.add("code", newRule.getCode());
		job.add("name", newRule.getName());
		job.add("message", newRule.getMessageText());
		job.add("type", newRule.getRuleType());
		job.add("columnName", newRule.getColumnName());
		job.add("columnType", newRule.getColumnType());
		job.add("table", newRule.getTableName());
		job.add("status", newRule.getStatus());
		job.add("operator", newRule.getOperator());
		job.add("triggerEvents", newRule.getTriggerEvents());
		job.add("query", newRule.getQuery());
		job.add("otherTable", newRule.getOtherTable());
		job.add("otherColumn", newRule.getOtherColumn());
		
		return job.build().toString();
	}
	
	@DELETE
	@Path("{id}")
	public boolean deleteModifyRule (@PathParam("id") String id) throws SQLException {	
		Integer idInt = Integer.parseInt(id);
		return modifyRuleService.deleteModifyRule(idInt);
	}
}