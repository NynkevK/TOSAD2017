package nl.hu.tosad2017.webservices;

import nl.hu.tosad2017.model.model.BusinessRule;
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

@Path("/listrule")
public class ListRuleResource {
	
	ListRuleService listRuleService = ServiceProvider.getListRuleService();
	
	private JsonObjectBuilder ruleToJson (BusinessRule rule) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		return job.add("code", rule.getCode());
	}
	
	//TODO Write getListRuleByCode
	
	//TODO Write getAllListRules
	
	@POST
	@Produces("application/json")
	public Response defineListRule(@FormParam("code") String code,
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
		System.out.println(".. executing ListRule Resource (POST)");
		
		Integer idInt = Integer.parseInt(code);
		
		//TODO add all other parameters for ListRule in constructor below
		BusinessRule newRule = new ListRule();
		
		if(listRuleService.getListRuleByCode(idInt) == null) {
			BusinessRule returnedRule = listRuleService.defineListRule(newRule);
			int a = returnedRule.getId();
			return Response.ok(a).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}
	
	@PUT
	@Path("{code}")
	@Produces("application/json")
	public String updateListRule(@FormParam("code") String code,
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
		System.out.println(".. executing ListRule Resource (PUT) for " + code);
		
		Integer idInt = Integer.parseInt(code);
		
		//TODO add all other parameters to constructor and method
		BusinessRule oldRule = listRuleService.getListRuleByCode(idInt);
		
		oldRule.setId(idInt);
		oldRule.setName(name);
		//TODO add all other params
		
		BusinessRule newRule = listRuleService.updateListRule(oldRule);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		//Add all rule attributes to a json object
		job.add("code", newRule.getCode());
		//TODO Add above for each of the attributes of Listrule
		
		return job.build().toString();
	}
	
	@DELETE
	@Path("{code}")
	public String deleteListRule (int code) {
		// logging for Heroku application server
		System.out.println(".. executing ListRule Resource (DELETE) for " + code);
		
		BusinessRule rule = listRuleService.getListRuleByCode(code);
		try {
			listRuleService.deleteListRule(rule);
			return "Success";
		} catch (Exception e) {
			return "Failed DELETE";
		}
	}
}