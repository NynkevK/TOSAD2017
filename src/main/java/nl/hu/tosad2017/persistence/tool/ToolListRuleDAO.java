package nl.hu.tosad2017.persistence.tool;

import nl.hu.tosad2017.model.model.ListRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolListRuleDAO extends ToolBaseDAO {

    public boolean createRule(ListRule rule) throws SQLException {

        String query = "INSERT INTO LISTRULE" +
                "(ID,CODE,NAME,RULETYPE,LIST,STATUS,COLUMNNAME,COLUMNTYPE,TABLENAME,OPERATOR" +
                ",TRIGGEREVENTS,MESSAGETEXT)" +
                "VALUES(0,?,?,?,?,?,?,?,?,?,?,?)";

        Connection connection = super.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, rule.getCode());
        ps.setString(2, rule.getName());
        ps.setString(3, rule.getRuleType());
        ps.setString(4, rule.getList().toString());
        ps.setString(5, rule.getStatus());
        ps.setString(6, rule.getColumnName());
        ps.setString(7, rule.getColumnType());
        ps.setString(8, rule.getTableName());
        ps.setString(9, rule.getOperator());
        ps.setString(10, rule.getTriggerEvents());
        ps.setString(11, rule.getMessageText());
        int i = ps.executeUpdate();

        if (i > 0 ){
            return true;
        }

        return false;
    }

    public boolean updateRule(ListRule rule) throws SQLException {

        String query = "UPDATE LISTRULE SET" +
                " LIST= ?," +
                " STATUS= ?," +
                " OPERATOR= ?," +
                " MESSAGETEXT= ?" +
                " WHERE ID= ? ";

        Connection connection = super.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, rule.getList().toString());
        ps.setString(2, rule.getStatus());
        ps.setString(3, rule.getOperator());
        ps.setString(4, rule.getMessageText());
        ps.setInt(5,rule.getId());
        int i = ps.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;}

    public ListRule readRule(int listRuleId) throws SQLException {

        Connection connection = super.getConnection();
        String query = "SELECT * FROM LISTRULE WHERE ID= ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, listRuleId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String code = rs.getString("CODE");
            String name = rs.getString("NAME");
            String ruletype = rs.getString("RULETYPE");
            String list = rs.getString("LIST");
            String status = rs.getString("STATUS");
            String columnName = rs.getString("COLUMNNAME");
            String columnType = rs.getString("COLUMNTYPE");
            String tableName = rs.getString("TABLENAME");
            String operator = rs.getString("OPERATOR");
            String triggerEvents = rs.getString("TRIGGEREVENTS");
            String msgText = rs.getString("MESSAGETEXT");

            ListRule rule = new ListRule(id, code, name, msgText, ruletype, columnName, columnType, tableName, status, operator, triggerEvents, list);
            return rule;
        }
        return null;
    }

    public List<ListRule> readAllRules() throws SQLException {

        List<ListRule> listRules = new ArrayList<ListRule>();

        Connection connection = super.getConnection();
        String query = "SELECT * FROM LISTRULE";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String code = rs.getString("CODE");
            String name = rs.getString("NAME");
            String ruletype = rs.getString("RULETYPE");
            String list = rs.getString("LIST");
            String status = rs.getString("STATUS");
            String columnName = rs.getString("COLUMNNAME");
            String columnType = rs.getString("COLUMNTYPE");
            String tableName = rs.getString("TABLENAME");
            String operator = rs.getString("OPERATOR");
            String triggerEvents = rs.getString("TRIGGEREVENTS");
            String msgText = rs.getString("MESSAGETEXT");

            ListRule rule = new ListRule(id, code, name, msgText, ruletype, columnName, columnType, tableName, status, operator, triggerEvents, list);
            listRules.add(rule);
            System.out.println(rule.toString());
        }
        return listRules;
    }

    public boolean deleteRule(int listRuleId) throws SQLException {

        String query = "DELETE FROM LISTRULE WHERE ID= ?";

        Connection connection = super.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1,listRuleId);
        int i = ps.executeUpdate();

        if (i > 0){
            System.out.println("true");
            return true;
        }
        System.out.println("false");
        return false;}

}
