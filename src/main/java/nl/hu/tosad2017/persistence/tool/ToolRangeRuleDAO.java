package nl.hu.tosad2017.persistence.tool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import nl.hu.tosad2017.model.model.RangeRule;
import nl.hu.tosad2017.persistence.target.TargetBaseDAO;

public class ToolRangeRuleDAO extends ToolBaseDAO {

    public boolean createRule(RangeRule rule) throws SQLException {

        String query = "INSERT INTO RANGERULE" +
                "(ID,CODE,NAME,RULETYPE,MINIMUMVALUE,MAXIMUMVALUE,STATUS,COLUMNNAME,COLUMNTYPE,TABLENAME,OPERATOR" +
                ",TRIGGEREVENTS,MESSAGETEXT)" +
                "VALUES(0,?,?,?,?,?,?,?,?,?,?,?,?)";

            Connection connection = super.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, rule.getCode());
            ps.setString(2, rule.getName());
            ps.setString(3, rule.getRuleType());
            ps.setInt(4, rule.getMinValue());
            ps.setInt(5, rule.getMaxValue());
            ps.setString(6, rule.getStatus());
            ps.setString(7, rule.getColumnName());
            ps.setString(8, rule.getColumnType());
            ps.setString(9, rule.getTableName());
            ps.setString(10, rule.getOperator());
            ps.setString(11, rule.getTriggerEvents());
            ps.setString(12, rule.getMessageText());

            int i = ps.executeUpdate();

            if (i > 0 ){
                return true;
            }

            return false;

    }

    public List<RangeRule> readAllRules() throws SQLException {
        List<RangeRule> rangeRules = new ArrayList<RangeRule>();

        Connection connection = super.getConnection();
        String query = "SELECT * FROM RANGERULE";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("ID");
            String code = rs.getString("CODE");
            String name = rs.getString("NAME");
            String ruletype = rs.getString("RULETYPE");
            int minvalue = rs.getInt("MINIMUMVALUE");
            int maxvalue = rs.getInt("MAXIMUMVALUE");
            String status = rs.getString("STATUS");
            String columnName = rs.getString("COLUMNNAME");
            String columnType = rs.getString("COLUMNTYPE");
            String tableName = rs.getString("TABLENAME");
            String operator = rs.getString("OPERATOR");
            String triggerEvents = rs.getString("TRIGGEREVENTS");
            String msgText = rs.getString("MESSAGETEXT");

            RangeRule rule = new RangeRule(id, code, name, msgText, ruletype, columnName, columnType, tableName, status, operator, triggerEvents, minvalue, maxvalue);
            System.out.println(rule.toString());
            rangeRules.add(rule);
        }
        return rangeRules;

    }

    public RangeRule readRule(int rangeRuleId) throws SQLException {

        Connection connection = super.getConnection();
        String query = "SELECT * FROM RANGERULE WHERE ID= ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, rangeRuleId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String code = rs.getString("CODE");
            String name = rs.getString("NAME");
            String ruletype = rs.getString("RULETYPE");
            int minvalue = rs.getInt("MINIMUMVALUE");
            int maxvalue = rs.getInt("MAXIMUMVALUE");
            String status = rs.getString("STATUS");
            String columnName = rs.getString("COLUMNNAME");
            String columnType = rs.getString("COLUMNTYPE");
            String tableName = rs.getString("TABLENAME");
            String operator = rs.getString("OPERATOR");
            String triggerEvents = rs.getString("TRIGGEREVENTS");
            String msgText = rs.getString("MESSAGETEXT");

            RangeRule rule = new RangeRule(id, code, name, msgText, ruletype, columnName, columnType, tableName, status, operator, triggerEvents, minvalue, maxvalue);
            System.out.println(rule.toString());
            return rule;
        }
        return null;
    }

    public boolean deleteRule(int rangeRuleId) throws SQLException {

        String query = "DELETE FROM RANGERULE WHERE ID= ?";

            Connection connection = super.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1,rangeRuleId);
            int i = ps.executeUpdate();

            if (i > 0){
                System.out.println("true");
                return true;
            }
        System.out.println("false");
        return false;

    }
}

