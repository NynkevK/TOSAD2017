package nl.hu.tosad2017.persistence.target;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
        }
    }
}
