package nl.hu.tosad2017.webservices;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import nl.hu.tosad2017.model.services.GenerateService;

@Path("/generate")
public class GenerateResource {
	GenerateService service = new GenerateService();
	
	@POST
	@Produces("application/json")
<<<<<<< HEAD
	public String generateRule(@QueryParam("id") String id,
							@QueryParam("ruletype") String ruletype) 
							//@QueryParam("otherTable") String otherTable,
							//@QueryParam("otherColumn") String otherColumn; 
							throws SQLException {
=======
	public String generateRule(@PathParam("id") String id,
								@PathParam("type") String type) throws SQLException {
				
>>>>>>> ae9b95ca3cb9b93a4a9a25c829f1ef9e01c86c72
		Integer idInt = Integer.parseInt(id);
		
		service.generateRule(idInt, ruletype);
		
		return "success";
	}
}
