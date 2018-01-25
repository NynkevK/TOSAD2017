package nl.hu.tosad2017.webservices;

import nl.hu.tosad2017.model.model.ModifyRule;
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

@Path("/Modifyrule")
public class ModifyRuleResource {
	
	ModifyRuleService modifyRuleService = ServiceProvider.getModifyRuleService();
	
	private JsonObjectBuilder ruleToJson (ModifyRule rule) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		return job.add("code", rule.getCode());
	}
	
	//TODO Write getModifyRuleByCode
	
	//TODO Write getAllModifyRules
	
	@POST
	@Produces("application/json")
	public Response defineModifyRule(@FormParam("code") String code,
								@FormParam("name") String name, 
								@FormParam("message") String message,
								@FormParam("type") String type,
								@FormParam("columnName") String columnName,
								@FormParam("columnType") String columnType,
								@FormParam("table") String table,
								@FormParam("status") String status, 
								@FormParam("operator") String operator,
								@FormParam("triggerEvents") String triggerEvents,
								@FormParam("query") String query) {
		
		// logging for Heroku application server
		System.out.println(".. executing ModifyRule Resource (POST)");
		
		Integer idInt = Integer.parseInt(code);
		
		//TODO add all other parameters for ModifyRule in constructor below
		ModifyRule newRule = new ModifyRule();
		
		if(modifyRuleService.getModifyRuleByCode(idInt) == null) {
			ModifyRule returnedRule = modifyRuleService.defineModifyRule(newRule);
			int a = returnedRule.getId();
			return Response.ok(a).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}
	
	@PUT
	@Path("{code}")
	@Produces("application/json")
	public String updateModifyRule(@FormParam("code") String code,
								@FormParam("name") String name, 
								@FormParam("message") String message,
								@FormParam("type") String type,
								@FormParam("columnName") String columnName,
								@FormParam("columnType") String columnType,
								@FormParam("table") String table,
								@FormParam("status") String status, 
								@FormParam("operator") String operator,
								@FormParam("triggerEvents") String triggerEvents,
								@FormParam("ModifydTable") String ModifydTable,
								@FormParam("ModifydColumn") String ModifydColumn,
								@FormParam("ModifyValue") String ModifyValue) {
		
		// logging for Heroku application server
		System.out.println(".. executing ModifyRule Resource (PUT) for " + code);
		
		Integer idInt = Integer.parseInt(code);
		
		//TODO add all other parameters to constructor and method
		ModifyRule oldRule = modifyRuleService.getModifyRuleByCode(idInt);
		
		oldRule.setId(idInt);
		oldRule.setName(name);
		//TODO add all other params
		
		ModifyRule newRule = modifyRuleService.updateModifyRule(oldRule);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		//Add all rule attributes to a json object
		job.add("code", newRule.getCode());
		//TODO Add above for each of the attributes of Modifyrule
		
		return job.build().toString();
	}
	
	@DELETE
	@Path("{code}")
	public String deleteModifyRule (int code) {
		// logging for Heroku application server
		System.out.println(".. executing ModifyRule Resource (DELETE) for " + code);
		
		ModifyRule rule = modifyRuleService.getModifyRuleByCode(code);
		try {
			modifyRuleService.deleteModifyRule(rule);
			return "Success";
		} catch (Exception e) {
			return "Failed DELETE";
		}
	}
}