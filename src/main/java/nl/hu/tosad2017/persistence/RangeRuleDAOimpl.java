package nl.hu.tosad2017.persistence;

import nl.hu.tosad2017.persistence.BaseDAO;

import java.sql.*;

public class RangeRuleDAOimpl implements RangeRuleDAO{
    nl.hu.tosad2017.persistence.BaseDAO BaseDAO = new BaseDAO();

    public boolean insertRangeRule(String operator, String code, String kolomNaam,  int minvalue, int maxvalue) throws SQLException {
        Connection con = BaseDAO.getConn();
        try {
            Statement stmt = con.createStatement();
            String insert = "INSERT INTO RANGERULES VALUES(0,'" + operator + "','" + code + "','" + kolomNaam + "'," + minvalue + "," + maxvalue + ")";

            System.out.println(insert);

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

