package nl.hu.tosad2017.webservices;

import nl.hu.tosad2017.model.model.ListRule;
import nl.hu.tosad2017.model.services.ServiceProvider;
import nl.hu.tosad2017.model.services.ListRuleService;

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

@Path( "/listrule")
public class ListRuleResource {
	// initialise service
	ListRuleService listRuleService = ServiceProvider.getListRuleService();

	@GET
	@Produces("application/json")
	public String getAllListRules() throws SQLException {
		// logging for Heroku application server 	
		System.out.println(".. executing ListRule Resource (GET) for all");
				
		JsonArrayBuilder jab = Json.createArrayBuilder();
			
		//Add each ListRule to a json object
		for (ListRule r : listRuleService.getAllListRules()) {
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
			job.add("list", r.getList());
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
	public String getListRuleById(@PathParam("id") String id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing ListRule Resource (GET) for " + id);
		
		Integer idInt = Integer.parseInt(id);
		ListRule r = listRuleService.getListRuleById(idInt);
		
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
		job.add("list", r.getList());
		return job.build().toString();
	}

	@POST
	@Produces("application/json")
	public Response defineListRule(@FormParam("id") String id,
								@FormParam("code") String code,
								@FormParam("name") String name,
								@FormParam("message") String message,
								@FormParam("type") String type,
								@FormParam("columnName") String columnName,
								@FormParam("columnType") String columnType,
								@FormParam("table") String table,
								@FormParam("status") String status,
								@FormParam("operator") String operator,
								@FormParam("triggerEvents") String triggerEvents,
								@FormParam("list") String list) throws SQLException {

		// logging for Heroku application server
		System.out.println(".. executing ListRule Resource (POST)");

		Integer idInt = Integer.parseInt(id);
		String listString = list;

		ListRule newRule = new ListRule(idInt, code, name, message, type, 
											columnName, columnType, table, status, 
											operator, triggerEvents, listString);

		if(listRuleService.getListRuleById(idInt) == null){
			boolean returnedRule = listRuleService.defineListRule(newRule);
			return Response.ok(returnedRule).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}

	@PUT
	@Path("{id}")
	@Produces("application/json")
	public String updateListRule(@PathParam("id") String id,
								@FormParam("code") String code,
								@FormParam("name") String name,
								@FormParam("message") String message,
								@FormParam("type") String type,
								@FormParam("columnName") String columnName,
								@FormParam("columnType") String columnType,
								@FormParam("table") String table,
								@FormParam("status") String status,
								@FormParam("operator") String operator,
								@FormParam("triggerEvents") String triggerEvents,
								@FormParam("list") String list) throws SQLException {

		// logging for Heroku application server
		System.out.println(".. executing ListRule Resource (PUT) for " + id);

		Integer idInt = Integer.parseInt(id);
		String listString = list;
		
		ListRule oldRule = listRuleService.getListRuleById(idInt);

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
		oldRule.setList(listString);

		ListRule newRule = listRuleService.updateListRule(idInt);
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		//Add all rule attributes to a json object
		job.add("id", newRule.getId());
		job.add("code", newRule.getCode());
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
		job.add("list", newRule.getList());

		return job.build().toString();

	}

	@DELETE
	@Path("{id}")
	public boolean deleteListRule(@PathParam("id") String id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing ListRule Resource (DELETE) for " + id);
		Integer idInt = Integer.parseInt(id);
		return listRuleService.deleteListRule(idInt);

	}
}