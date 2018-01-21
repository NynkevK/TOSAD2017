package nl.hu.tosad2017.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.tosad2017.model.model.BusinessRule;
import nl.hu.tosad2017.model.services.RuleService;
import nl.hu.tosad2017.model.services.ServiceProvider;

@Path("/rule")
public class RuleResource {
	/*RuleService ruleservice = ServiceProvider.getRuleService();
	
	private JsonObjectBuilder ruleToJson(BusinessRule rule) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		return job.add("value", rule.getCode());
	}
	
	@GET
	@Produces("application/json")
	public String getAllRules() {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (GET)");
		
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (BusinessRule br : ruleservice.getAllRules()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			//Add all rule attributes to a json object
			job.add("code", br.getCode());
			//TODO Add all business rule attributes to the json object
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getRuleByCode(@PathParam("code") String code) {
		// logging for Heroku application server
		System.out.println(".. executing RangeRule Resource (GET)");
		
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (BusinessRule br : ruleservice.getAllRules()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", br.getCode());
			//TODO Add all business rule attributes to the json object
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}*/
}
