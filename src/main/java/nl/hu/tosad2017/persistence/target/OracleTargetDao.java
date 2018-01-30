package nl.hu.tosad2017.persistence.target;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nl.hu.tosad2017.model.model.CompareRule;

public class OracleTargetDao extends TargetBaseDAO{

    public void insertTrigger(String code) throws SQLException {
        Connection con = TargetBaseDAO.getConnection();
        try {
            Statement stmt = con.createStatement();
            String triggerCode = code;

            if (stmt.execute(triggerCode)){
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
    
//    public void removeTrigger(String triggerName){
//    	try {
//        	Connection con = TargetBaseDAO.getConnection();
//            Statement stmt = con.createStatement();
//            String triggerCode = "DROP TRIGGER "+rule.get  
//            System.out.println(triggerCode);
//            ResultSet rs = stmt.executeQuery(triggerCode);
//
//            if(rs.next()) {
//            	  fk = rs.getString(1);
//            	  System.out.println(fk);
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        System.out.println("Succesfully added trigger");
//    }
}
