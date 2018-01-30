package nl.hu.tosad2017.webservices;

import nl.hu.tosad2017.model.services.DatabaseService;
import nl.hu.tosad2017.model.services.ServiceProvider;
import nl.hu.tosad2017.model.model.DataInfo;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import java.sql.SQLException;

@Path( "/datainfo")
public class DatabaseResource {
        // initialise service
        DatabaseService databaseService = ServiceProvider.getDatabaseService();

    @GET
    @Produces("application/json")
    public String getAllDataInfo() throws SQLException {

        JsonArrayBuilder jab = Json.createArrayBuilder();

        //Add each dataInfo to a json object
        for (DataInfo d : databaseService.getAllDataInfo()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("tablename", d.getTableName());
            job.add("columnname", d.getColumnName());
            job.add("datatype", d.getDataType());
            jab.add(job);
        }

        if (jab == null ) {
            throw new WebApplicationException("No rues found!");
        }

        JsonArray array = jab.build();
        return array.toString();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getDataByTableName(@PathParam("id") String table) throws SQLException {

        JsonArrayBuilder jab = Json.createArrayBuilder();

        //Add each dataInfo to a json object

        for (DataInfo d : databaseService.getDataByTableName(table)) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("tablename", d.getTableName());
            job.add("columnname", d.getColumnName());
            job.add("datatype", d.getDataType());
            jab.add(job);
        }

        if (jab == null ) {
            throw new WebApplicationException("No rues found!");
        }

        JsonArray array = jab.build();
        return array.toString();
    }

}
