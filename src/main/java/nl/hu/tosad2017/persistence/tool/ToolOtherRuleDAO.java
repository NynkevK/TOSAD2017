package nl.hu.tosad2017.persistence.tool;

import nl.hu.tosad2017.model.model.OtherRule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolOtherRuleDAO extends ToolBaseDAO{

    public boolean createRule(OtherRule rule) throws SQLException {

            String query = "INSERT INTO OTHERRULE" +
                    "(ID,CODE,NAME,RULETYPE,QUERY,STATUS,COLUMNNAME,COLUMNTYPE,TABLENAME,OTHERCOLUMN,OPERATOR" +
                    ",TRIGGEREVENTS,MESSAGETEXT)" +
                    "VALUES(0,?,?,?,?,?,?,?,?,?,?,?,?)";

            Connection connection = super.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, rule.getCode());
            ps.setString(2, rule.getName());
            ps.setString(3, rule.getRuleType());
            ps.setString(4, rule.getQuery());
            ps.setString(5, rule.getStatus());
            ps.setString(6, rule.getColumnName());
            ps.setString(7, rule.getColumnType());
            ps.setString(8, rule.getTableName());
            ps.setString(9, rule.getOtherColumn());
            ps.setString(10, rule.getOperator());
            ps.setString(11, rule.getTriggerEvents());
            ps.setString(12, rule.getMessageText());
            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }
            return false;
        }

        public List<OtherRule> readAllRules() throws SQLException {

            List<OtherRule> otherRules = new ArrayList<OtherRule>();

            Connection connection = super.getConnection();
            String query = "SELECT * FROM OTHERRULE";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String code = rs.getString("CODE");
                String name = rs.getString("NAME");
                String ruletype = rs.getString("RULETYPE");
                String o_query = rs.getString("QUERY");
                String status = rs.getString("STATUS");
                String columnName = rs.getString("COLUMNNAME");
                String columnType = rs.getString("COLUMNTYPE");
                String tableName = rs.getString("TABLENAME");
                String otherColumn = rs.getString("OTHERCOLUMN");
                String operator = rs.getString("OPERATOR");
                String triggerEvents = rs.getString("TRIGGEREVENTS");
                String msgText = rs.getString("MESSAGETEXT");

               OtherRule rule = new OtherRule(id, code, name, ruletype, o_query, status, columnName, columnType, tableName, otherColumn, operator, triggerEvents, msgText);
               System.out.println(rule.toString());
               otherRules.add(rule);
            }
            return otherRules;
        }

        public OtherRule readRule(int otherRuleId) throws SQLException {

            Connection connection = super.getConnection();
            String query = "SELECT * FROM OTHERRULE WHERE ID= ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, otherRuleId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String code = rs.getString("CODE");
                String name = rs.getString("NAME");
                String ruletype = rs.getString("RULETYPE");
                String o_query = rs.getString("QUERY");
                String status = rs.getString("STATUS");
                String columnName = rs.getString("COLUMNNAME");
                String columnType = rs.getString("COLUMNTYPE");
                String tableName = rs.getString("TABLENAME");
                String otherColumn = rs.getString("OTHERCOLUMN");
                String operator = rs.getString("OPERATOR");
                String triggerEvents = rs.getString("TRIGGEREVENTS");
                String msgText = rs.getString("MESSAGETEXT");

                OtherRule rule = new OtherRule(id, code, name, ruletype, o_query, status, columnName, columnType, tableName, otherColumn, operator, triggerEvents, msgText);
                System.out.println(rule.toString());
                return rule;
            }
            return null;
        }

    public boolean deleteRule(int otherRuleId) throws SQLException {

        String query = "DELETE FROM OTHERRULE WHERE ID= ?";

        Connection connection = super.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1,otherRuleId);
        int i = ps.executeUpdate();

        if (i > 0){
            System.out.println("true");
            return true;
        }
        return false;
    }

    public boolean updateRule(OtherRule rule) throws SQLException {

        String query = "UPDATE OTHERRULE SET" +
                " QUERY= ?," +
                " STATUS= ?," +
                " COLUMNNAME= ?," +
                " OTHERCOLUMN= ?," +
                " OPERATOR= ?," +
                " TRIGGEREVENTS= ?," +
                " MESSAGETEXT= ?" +
                " WHERE ID= ?";

        Connection connection = super.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, rule.getQuery());
        ps.setString(2, rule.getStatus());
        ps.setString(3, rule.getColumnName());
        ps.setString(4,rule.getOtherColumn());
        ps.setString(5, rule.getOperator());
        ps.setString(6, rule.getTriggerEvents());
        ps.setString(7, rule.getMessageText());
        ps.setInt(8,rule.getId());
        int i = ps.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }
    }
