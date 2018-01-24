package nl.hu.tosad2017.webservices;

import java.sql.SQLException;

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

import nl.hu.tosad2017.model.model.RangeRule;
import nl.hu.tosad2017.model.services.RangeRuleService;
import nl.hu.tosad2017.model.services.ServiceProvider;

@Path("/rangerule")
public class RangeRuleResource {
	// initialise service
	RangeRuleService rangeruleservice = ServiceProvider.getRangeRuleService();

	@GET
	@Produces("application/json")
	public String getAllRangeRules() throws SQLException {
		// logging for Heroku application server 	
		System.out.println(".. executing RangeRule Resource (GET) for all");
				
		JsonArrayBuilder jab = Json.createArrayBuilder();
			
		//Add each rangerule to a json object
		for (RangeRule r : rangeruleservice.getAllRangeRules()) {
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
			job.add("minValue", r.getMinValue());
			job.add("maxValue", r.getMaxValue());
			jab.add(job);
		}
		
		if (jab == null ) {
			throw new WebApplicationException ("No rues found!");
			}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getRangeRuleById(@PathParam("id") String id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (GET) for " + id);
		
		Integer idInt = Integer.parseInt(id);
		RangeRule r = rangeruleservice.getRangeRuleById(idInt);
		
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
		job.add("minValue", r.getMinValue());
		job.add("maxValue", r.getMaxValue());
		return job.build().toString();
	}

	@POST
	@Produces("application/json")
	public Response defineRangeRule(@FormParam("id") String id,
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
								@FormParam("minValue") String minValue,
								@FormParam("maxValue") String maxValue) throws SQLException {

		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (POST)");

		Integer idInt = Integer.parseInt(id);
		Integer minv = Integer.parseInt(minValue);
		Integer maxv = Integer.parseInt(maxValue);

		RangeRule newRule = new RangeRule(idInt, code, name, message, type, 
											columnName, columnType, table, status, 
											operator, triggerEvents, minv, maxv);

		if(rangeruleservice.getRangeRuleById(idInt) == null){
			boolean returnedRule = rangeruleservice.defineRangeRule(newRule);
			return Response.ok(returnedRule).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}

	@PUT
	@Path("{id}")
	@Produces("application/json")
	public String updateRangeRule(@PathParam("id") String id,
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
								@FormParam("minValue") String minValue,
								@FormParam("maxValue") String maxValue) throws SQLException {

		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (PUT) for " + id);

		Integer idInt = Integer.parseInt(id);
		Integer minv = Integer.parseInt(minValue);
		Integer maxv = Integer.parseInt(maxValue);
		
		RangeRule oldRule = rangeruleservice.getRangeRuleById(idInt);

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
		oldRule.setMinValue(minv);
		oldRule.setMaxValue(maxv);

		RangeRule newRule = rangeruleservice.updateRangeRule(idInt);
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
		job.add("minValue", newRule.getMinValue());
		job.add("maxValue", newRule.getMaxValue());

		return job.build().toString();

	}

	@DELETE
	@Path("{id}")
	public boolean deleteRangeRule(@PathParam("id") String id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (DELETE) for " + id);
		Integer idInt = Integer.parseInt(id);
		return rangeruleservice.deleteRangeRule(idInt);

	}
}
