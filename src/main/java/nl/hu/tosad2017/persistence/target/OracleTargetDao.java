package nl.hu.tosad2017.persistence.target;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleTargetDao extends TargetBaseDAO{

    public void insertTrigger(String code) throws SQLException {
        Connection con = TargetBaseDAO.getConnection();
        try {
            Statement stmt = con.createStatement();
            String triggerCode = code;

            if (stmt.execute(triggerCode)){
            	System.out.println("Adding/Altering trigger has succeeded");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Adding/Altering trigger has failed");
        }
    }
    
    public String getForeignKey(String cName) {
    	String fk = "";
        try {
        	Connection con = TargetBaseDAO.getConnection();
            Statement stmt = con.createStatement();
            String triggerCode = "SELECT a.column_name "+
            		  "FROM all_cons_columns a "+
            		  "where CONSTRAINT_NAME = '"+ cName +"'";
            System.out.println(triggerCode);
            ResultSet rs = stmt.executeQuery(triggerCode);

            if(rs.next()) {
            	  fk = rs.getString(1);
            	  System.out.println(fk);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    	return fk;

    }
    
    public void removeTrigger(String triggerName){
    	try {
        	Connection con = TargetBaseDAO.getConnection();
            Statement stmt = con.createStatement();
            String triggerCode = "drop trigger \""+triggerName+"\"";
            System.out.println(triggerCode);
            if (stmt.execute(triggerCode)){
            	System.out.println("Succesfully removed trigger");
            }

        } catch (SQLException ex) {
            System.out.println("Removing trigger failed");
        }
        
    }
}
