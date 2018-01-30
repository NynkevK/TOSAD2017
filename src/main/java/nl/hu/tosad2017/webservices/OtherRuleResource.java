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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import nl.hu.tosad2017.model.model.OtherRule;
import nl.hu.tosad2017.model.services.OtherRuleService;
import nl.hu.tosad2017.model.services.ServiceProvider;

@Path("/otherrule")
public class OtherRuleResource {
	// initialise service
	OtherRuleService otherruleservice = ServiceProvider.getOtherRuleService();

		@GET
		@Produces("application/json")
		public String getAllOtherRules() throws SQLException {
					
			JsonArrayBuilder jab = Json.createArrayBuilder();
				
			//Add each rangerule to a json object
			for (OtherRule r : otherruleservice.getAllOtherRules()) {
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
				job.add("query", r.getQuery());
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
		public String getOtherRuleById(@PathParam("id") String id) throws SQLException {
			
			Integer idInt = Integer.parseInt(id);
			OtherRule r = otherruleservice.getOtherRuleById(idInt);
			
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
			job.add("query", r.getQuery());
			return job.build().toString();
		}

		@POST
		@Produces("application/json")
		public Response defineRangeRule(@QueryParam("id") String id,
									@QueryParam("code") String code,
									@QueryParam("name") String name,
									@QueryParam("message") String message,
									@QueryParam("type") String type,
									@QueryParam("columnName") String columnName,
									@QueryParam("columnType") String columnType,
									@QueryParam("otherColumn") String otherColumn,
									@QueryParam("table") String table,
									@QueryParam("status") String status,
									@QueryParam("operator") String operator,
									@QueryParam("triggerEvents") String triggerEvents,
									@QueryParam("query") String query) throws SQLException {


			Integer idInt = Integer.parseInt(id);

			OtherRule newRule = new OtherRule(idInt, code, name, message, type, 
												columnName, columnType, otherColumn,
												table, status, operator, triggerEvents, query);

			if(otherruleservice.getOtherRuleById(idInt) == null){
				boolean returnedRule = otherruleservice.defineOtherRule(newRule);
				return Response.ok(returnedRule).build();
			} else {
				return Response.status(Response.Status.FOUND).build();
			}
		}

		@PUT
		@Path("{id}")
		@Produces("application/json")
		public String updateOtherRule(@PathParam("id") String id,
									@QueryParam("code") String code,
									@QueryParam("name") String name,
									@QueryParam("message") String message,
									@QueryParam("type") String type,
									@QueryParam("columnName") String columnName,
									@QueryParam("columnType") String columnType,
									@QueryParam("table") String table,
									@QueryParam("status") String status,
									@QueryParam("operator") String operator,
									@QueryParam("triggerEvents") String triggerEvents,
									@QueryParam("query") String query) throws SQLException {


			Integer idInt = Integer.parseInt(id);
			
			OtherRule oldRule = otherruleservice.getOtherRuleById(idInt);

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
			oldRule.setQuery(query);

			OtherRule newRule = otherruleservice.updateOtherRule(idInt);

			JsonObjectBuilder job = Json.createObjectBuilder();
			
			//Add all rule attributes to a json object
			job.add("id", newRule.getId());
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
			job.add("query", newRule.getQuery());

			return job.build().toString();
		}

		@DELETE
		@Path("{id}")
		public boolean deleteOtherRule(@PathParam("id") String id) throws SQLException {
			Integer idInt = Integer.parseInt(id);
			return otherruleservice.deleteOtherRule(idInt);

		}
	}

