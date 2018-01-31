package nl.hu.tosad2017.webservices;

import java.sql.SQLException;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import nl.hu.tosad2017.model.services.GenerateService;

@Path("/generate")
public class GenerateResource {
	GenerateService service = new GenerateService();
	
	@POST
	@Produces("application/json")
	public String generateRule(@QueryParam("id") String id,
								@QueryParam("type") String type) throws SQLException {
				
		Integer idInt = Integer.parseInt(id);
		
		return service.generateRule(idInt, type);
	}
	
	@DELETE
	@Produces("application/json")
	public String deleteRule(@QueryParam("id") String id,
							@QueryParam("type") String type) throws SQLException {
		
		Integer idInt = Integer.parseInt(id);
		return service.deleteRule(idInt, type);
	
	}
}
