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
	// initialise service
	CompareRuleService compareRuleservice = ServiceProvider.getCompareRuleService();

	@GET
	@Produces("application/json")
	public String getAllCompareRules() throws SQLException {
		// logging for Heroku application server 	
		System.out.println(".. executing CompareRule Resource (GET) for all");
				
		JsonArrayBuilder jab = Json.createArrayBuilder();
			
		//Add each compareRule to a json object
		for (CompareRule r : compareRuleservice.getAllCompareRules()) {
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
			job.add("comparedTable", r.getComparedTable());
			job.add("comparedColumn", r.getComparedColumn());
			job.add("comparedValue", r.getCompareValue());
			job.add("value", r.getValue());
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
	public String getCompareRuleById(@PathParam("id") String id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing CompareRule Resource (GET) for " + id);
		
		Integer idInt = Integer.parseInt(id);
		CompareRule r = compareRuleservice.getCompareRuleById(idInt);
		
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
		job.add("comparedTable", r.getComparedTable());
		job.add("comparedColumn", r.getComparedColumn());
		job.add("comparedValue", r.getCompareValue());
		job.add("value", r.getValue());
		return job.build().toString();
	}

	@POST
	@Produces("application/json")
	public Response defineCompareRule(@FormParam("id") String id,
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
								@FormParam("comparedTable") String comparedTable,
								@FormParam("comparedColumn") String comparedColumn,
								@FormParam("comparedValue") String comparedValue,
								@FormParam("value") String value) throws SQLException {

		// logging for Heroku application server
		System.out.println(".. executing CompareRule Resource (POST)");

		Integer idInt = Integer.parseInt(id);
		String comparedTableString = comparedTable;
		String comparedColumnString = comparedColumn;
		Integer comparedValueInt = Integer.parseInt(comparedValue);
		Integer valueInt = Integer.parseInt(value);

		CompareRule newRule = new CompareRule(idInt, code, name, type, status,
											columnName, columnType, table, valueInt,
											comparedColumnString, comparedTableString,
											comparedValueInt, operator, triggerEvents,
											message);

		if(compareRuleservice.getCompareRuleById(idInt) == null){
			boolean returnedRule = compareRuleservice.defineCompareRule(newRule);
			return Response.ok(returnedRule).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}

	@PUT
	@Path("{id}")
	@Produces("application/json")
	public String updateCompareRule(@PathParam("id") String id,
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
									@FormParam("comparedTable") String comparedTable,
									@FormParam("comparedColumn") String comparedColumn,
									@FormParam("comparedValue") String comparedValue,
									@FormParam("value") String value) throws SQLException {

		// logging for Heroku application server
		System.out.println(".. executing CompareRule Resource (PUT) for " + id);

		Integer idInt = Integer.parseInt(id);
		String comparedTableString = comparedTable;
		String comparedColumnString = comparedColumn;
		Integer comparedValueInt = Integer.parseInt(comparedValue);
		Integer valueInt = Integer.parseInt(value);
		
		CompareRule oldRule = compareRuleservice.getCompareRuleById(idInt);

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
		oldRule.setComparedColumn(comparedColumnString);
		oldRule.setComparedTable(comparedTableString);
		oldRule.setCompareValue(comparedValueInt);
		oldRule.setValue(valueInt);

		String string = "failed";
		
		if (compareRuleservice.updateCompareRule(oldRule) == true) {
			string = "success";
		}
		/*JsonObjectBuilder job = Json.createObjectBuilder();
		
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
		job.add("comparedTable", newRule.getComparedTable());
		job.add("comparedColumn", newRule.getComparedColumn());
		job.add("comparedValue", newRule.getCompareValue());
		job.add("value", newRule.getValue());*/

		return string;

	}

	@DELETE
	@Path("{id}")
	public boolean deleteCompareRule(@PathParam("id") String id) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing CompareRule Resource (DELETE) for " + id);
		Integer idInt = Integer.parseInt(id);
		return compareRuleservice.deleteCompareRule(idInt);

	}
}
