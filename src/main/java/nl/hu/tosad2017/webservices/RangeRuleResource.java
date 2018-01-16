package nl.hu.tosad2017.webservices;

import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
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

import nl.hu.tosad2017.model.*;


@Path("/rangerule")
public class RangeRuleResource {
		
	private JsonObjectBuilder rowToJson(Row row) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("value", row.getValue())
			.add("id", row.getId());
		return job;
	}
	
	@GET
	@Produces("application/json")
	public String getValues() {
		Service service = ServiceProvider.getService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for (String s : service.getRangeRules()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("test", s);
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@Path ("/test")
	public Response addValues(@FormParam("value") String value,
								@FormParam("id") String id) {
		Service service = ServiceProvider.getService();
		
		Integer valueInt = Integer.parseInt(value);
		Integer idInt = Integer.parseInt(id);
		
		Row newRow = new Row(value, id);
		if(service.getRowById(id) == null){
			Row returnRow = service.addRow(newRow);
			String a = rowToJson(returnRow).build().toString();
			return Response.ok(a).build();
		} else {
			return Response.status(Response.Status.FOUND).build();
		}
	}
}