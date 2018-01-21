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
import nl.hu.tosad2017.model.services.ServiceProvider;;


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
		
		//TODO Add all paramters for rangerule in constructor below
		RangeRule newRule = new RangeRule();
		
		if(rangeruleservice.getRangeRuleByCode(idInt) == null){
			RangeRule returnedRule = rangeruleservice.defineRangeRule(newRule);
			int a =  returnedRule.getCode();
			return Response.ok(a).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}
	
	@PUT
	@Path("{code}")
	@Produces("application/json")
	public String updateRangeRule(@PathParam("code") String code,
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
		System.out.println(".. executing RangeRule Resource (PUT) for " + code);
		
		Integer idInt = Integer.parseInt(code);
		
		//TODO Add params to constructor and method 
		RangeRule oldRule = rangeruleservice.getRangeRuleByCode(idInt);
		
		oldRule.setCode(idInt);
		oldRule.setName(name);
		//TODO add unimplemented methods below in rangerule model
		//oldRule.setMessage(message);
		//oldRule.setRuleType(type);
		//oldRule.setColumnName(columnName);
		//oldRule.setColumnType(columnType);
		//oldRule.setTable(table);
		//oldRule.setStatus(status);
		//oldRule.setOperator(operator);
		//oldRule.setTriggerEvents(triggerEvetns);
		//oldRule.setMinValue(minValue);
		//oldRule.setMaxValue(maxValue);
		
		RangeRule newRule = rangeruleservice.updateRangeRule(oldRule);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		//Add all rule attributes to a json object
		job.add("code", newRule.getCode());
		//TODO Add above for each of the attributes of rangerule
		
		return job.build().toString();
		
	}
	
	@DELETE
	@Path("{code}")
	public String deleteRangeRule(int code) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (DELETE) for " + code);
		
		RangeRule rule = rangeruleservice.getRangeRuleByCode(code);
		try {
			rangeruleservice.deleteRangeRule(rule);
			return "Success";
		} catch (Exception e) {
			return "Failed DELETE";
		}
	}
}