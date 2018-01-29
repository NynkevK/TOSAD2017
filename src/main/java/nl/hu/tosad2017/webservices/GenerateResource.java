package nl.hu.tosad2017.webservices;

import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.tosad2017.model.services.GenerateService;

@Path("/generate")
public class GenerateResource {
	GenerateService service = new GenerateService();
	
	@POST
	@Path("{id},{type}")
	@Produces("application/json")
	public String generateRule(@PathParam("id") String id,
								@PathParam("type") String type) throws SQLException {
		// logging for Heroku application server
		System.out.println(".. executing generate Resource for " + id);
				
		Integer idInt = Integer.parseInt(id);
		String typeString = type;
		
		service.generateRule(idInt, typeString);
		
		return "success";
	}
}
