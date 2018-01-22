package nl.hu.tosad2017.webservices;

import nl.hu.tosad2017.model.model.CompareRule;
import nl.hu.tosad2017.model.services.ServiceProvider;
import nl.hu.tosad2017.model.services.CompareRuleService;

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

@Path("/comparerule")
public class CompareRuleResource {
	
	CompareRuleService compareRuleService = ServiceProvider.getCompareRuleService();
	
	private JsonObjectBuilder ruleToJson (CompareRule rule) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		return job.add("code", rule.getCode());
	}
	
	//TODO Write getCompareRuleByCode
	
	//TODO Write getAllCompareRules
	
	@POST
	@Produces("application/json")
	public Response defineCompareRule(@FormParam("code") String code,
								@FormParam("name") String name, 
								@FormParam("message") String message,
								@FormParam("type") String type,
								@FormParam("columnName") String columnName,
								@FormParam("columnType") String columnType,
								@FormParam("table") String table,
								@FormParam("status") String status, 
								@FormParam("operator") String operator,
								@FormParam("triggerEvents") String triggerEvents,
								@FormParam("comparedTable") String comparedTable,
								@FormParam("comparedColumn") String comparedColumn,
								@FormParam("compareValue") String compareValue) {
		
		// logging for Heroku application server
		System.out.println(".. executing CompareRule Resource (POST)");
		
		Integer idInt = Integer.parseInt(code);
		
		//TODO add all other parameters for compareRule in constructor below
		CompareRule newRule = new CompareRule();
		
		if(compareRuleService.getCompareRuleByCode(idInt) == null) {
			CompareRule returnedRule = compareRuleService.defineCompareRule(newRule);
			int a = returnedRule.getCode();
			return Response.ok(a).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}
	
	@PUT
	@Path("{code}")
	@Produces("application/json")
	public String updateCompareRule(@FormParam("code") String code,
								@FormParam("name") String name, 
								@FormParam("message") String message,
								@FormParam("type") String type,
								@FormParam("columnName") String columnName,
								@FormParam("columnType") String columnType,
								@FormParam("table") String table,
								@FormParam("status") String status, 
								@FormParam("operator") String operator,
								@FormParam("triggerEvents") String triggerEvents,
								@FormParam("comparedTable") String comparedTable,
								@FormParam("comparedColumn") String comparedColumn,
								@FormParam("compareValue") String compareValue) {
		
		// logging for Heroku application server
		System.out.println(".. executing CompareRule Resource (PUT) for " + code);
		
		Integer idInt = Integer.parseInt(code);
		
		//TODO add all other parameters to constructor and method
		CompareRule oldRule = compareRuleService.getCompareRuleByCode(idInt);
		
		oldRule.setCode(idInt);
		oldRule.setName(name);
		//TODO add all other params
		
		CompareRule newRule = compareRuleService.updateCompareRule(oldRule);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		//Add all rule attributes to a json object
		job.add("code", newRule.getCode());
		//TODO Add above for each of the attributes of comparerule
		
		return job.build().toString();
	}
	
	@DELETE
	@Path("{code}")
	public String deleteCompareRule (int code) {
		// logging for Heroku application server
		System.out.println(".. executing CompareRule Resource (DELETE) for " + code);
		
		CompareRule rule = compareRuleService.getCompareRuleByCode(code);
		try {
			compareRuleService.deleteCompareRule(rule);
			return "Success";
		} catch (Exception e) {
			return "Failed DELETE";
		}
	}
}