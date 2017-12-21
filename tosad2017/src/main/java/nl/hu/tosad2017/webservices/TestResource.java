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


@Path("/test")
public class TestResource {
		
		@GET
		@Produces("application/json")
		public String getValues() {
			Service service = ServiceProvider.getService();
			JsonArrayBuilder jab = Json.createArrayBuilder();
			
			JsonArray array = jab.build();
			return array.toString();
		}
	}
}
