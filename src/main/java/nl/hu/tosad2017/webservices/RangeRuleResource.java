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
import nl.hu.tosad2017.model.services.RuleService;
import nl.hu.tosad2017.model.services.ServiceProvider;;


@Path("/rangerule")
public class RangeRuleResource {
	//TODO Implement method in serviceprovider, see row below
	RuleService ruleservice = ServiceProvider.getRuleService();
	RangeRuleService rangeruleservice = ServiceProvider.getRangeRuleService();
	
	private JsonObjectBuilder ruleToJson(RangeRule rule) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		return job.add("value", rule.getCode());
	}

	@POST
	@Produces("application/json")
	public Response defineRangeRule(@FormParam("code") String code,
								@FormParam("add") String two) {
		//TODO Add all params for rangerule in methods
		
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (POST)");
				
		Integer idInt = Integer.parseInt(code);
		//TODO Add above for each of the attributes of rangerule
		
		//TODO Add all paramters for rangerule in constructor below
		
		RangeRule newRule = new RangeRule();
		
		//TODO Implement method from ruleservice below
		if(ruleservice.getRuleByCode(code) == null){
			RangeRule returnedRule = rangeruleservice.defineRangeRule(newRule);
			String a =  returnedRule.getCode();
			return Response.ok(a).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}
	
	@PUT
	@Path("{code}")
	@Produces("application/json")
	public String updateRangeRule(String code) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (PUT) for " + code);
		
		//TODO Add params to constructor and method 
		RangeRule oldRule = (RangeRule) ruleservice.getRuleByCode(code);
		
		oldRule.setCode(code);
		// TODO Add above for each of the attributes of rangerule
		
		RangeRule newRule = rangeruleservice.updateRangeRule(oldRule);
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		//Add all rule attributes to a json object
		job.add("code", newRule.getCode());
		//TODO Add above for each of the attributes of rangerule
		
		return job.build().toString();
		
	}
	
	@DELETE
	@Path("{code}")
	public String deleteRangeRule(String code) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (DELETE) for " + code);
		
		RangeRule rule = (RangeRule) ruleservice.getRuleByCode(code);
		try {
			rangeruleservice.deleteRangeRule(rule);
			return "Success";
		} catch (Exception e) {
			return "Failed DELETE";
		}
	}
}