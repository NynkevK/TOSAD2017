package nl.hu.tosad2017.persistence.target;

import java.sql.*;

public class TargetRangeRuleDAO {
    TargetBaseDAO BaseDAO = new TargetBaseDAO();

    public boolean insertRangeRule(String operator, String code, String kolomNaam,  int minvalue, int maxvalue) throws SQLException {
        Connection con = BaseDAO.getConnection();
        try {
            Statement stmt = con.createStatement();
            String insert = "INSERT INTO RANGERULES VALUES(0,'" + operator + "','" + code + "','" + kolomNaam + "'," + minvalue + "," + maxvalue + ")";


            ResultSet rs = stmt.executeQuery(insert);

            if (stmt.execute(insert)){
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

