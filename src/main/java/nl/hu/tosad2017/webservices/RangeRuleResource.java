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
	//TODO Implement method in serviceprovider, see row below
	RangeRuleService rangeruleservice = ServiceProvider.getRangeRuleService();

	private JsonObjectBuilder ruleToJson(RangeRule rule) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		return job.add("value", rule.getCode());
	}


	//TODO Make get rangerule by code and get all rangerules functions

	@POST
	@Produces("application/json")
	public Response defineRangeRule(@FormParam("code") String code,
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
								@FormParam("maxValue") String maxValue) {

		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (POST)");

		Integer idInt = Integer.parseInt(code);
		Integer minv = Integer.parseInt(minValue);
		Integer maxv = Integer.parseInt(maxValue);

		RangeRule newRule = new RangeRule(idInt, code, name, message, type, 
											columnName, columnType, table, status, 
											operator, triggerEvents, minv, maxv);

		if(rangeruleservice.getRangeRuleByCode(idInt) == null){
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
								@FormParam("maxValue") String maxValue) {

		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (PUT) for " + id);

		Integer idInt = Integer.parseInt(id);
		Integer minv = Integer.parseInt(minValue);
		Integer maxv = Integer.parseInt(maxValue);
		
		RangeRule oldRule = rangeruleservice.getRangeRuleByCode(idInt);

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
		job.add("code", newRule.getCode());
		//TODO Add above for each of the attributes of rangerule

		return job.build().toString();

	}

	@DELETE
	@Path("{code}")
	public String deleteRangeRule(int id) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (DELETE) for " + id);

		RangeRule rule = rangeruleservice.getRangeRuleByCode(id);
		try {
			rangeruleservice.deleteRangeRule(id);
			return "Success";
		} catch (Exception e) {
			return "Failed DELETE";
		}
	}
}
