package nl.hu.tosad2017.persistence.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nl.hu.tosad2017.model.model.CompareRule;

public class ToolCompareRuleDAO extends ToolBaseDAO {

    public boolean createRule(CompareRule rule) throws SQLException {

        String query = "INSERT INTO COMPARERULE" +
                "(ID,CODE,NAME,RULETYPE,STATUS,COLUMNNAME,COLUMNTYPE,TABLENAME,VALUE,COMPAREDCOLUMN" +
                ",COMPAREDTABLE,COMPAREDVALUE,OPERATOR,TRIGGEREVENTS,MESSAGETEXT)" +
                "VALUES(0,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection connection = super.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, rule.getCode());
        ps.setString(2, rule.getName());
        ps.setString(3, rule.getRuleType());
        ps.setString(4, rule.getStatus());
        ps.setString(5, rule.getColumnName());
        ps.setString(6, rule.getColumnType());
        ps.setString(7, rule.getTableName());
        ps.setString(8, rule.getComparedColumn());
        ps.setString(9, rule.getComparedTable());
        ps.setInt(   10, rule.getCompareValue());
        ps.setString(11, rule.getOperator());
        ps.setString(12, rule.getTriggerEvents());
        ps.setString(13, rule.getMessageText());

        int i = ps.executeUpdate();

        if (i > 0 ){
            return true;
        }

        return false;

    }

    public List<CompareRule> readAllRules() throws SQLException {
        List<CompareRule> CompareRules = new ArrayList<CompareRule>();

        Connection connection = super.getConnection();
        String query = "SELECT * FROM COMPARERULE";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("ID");
            String code = rs.getString("CODE");
            String name = rs.getString("NAME");
            String ruletype = rs.getString("RULETYPE");
            int value = rs.getInt("VALUE");
            int comparedvalue = rs.getInt("COMPAREDVALUE");
            String status = rs.getString("STATUS");
            String columnName = rs.getString("COLUMNNAME");
            String columnType = rs.getString("COLUMNTYPE");
            String tableName = rs.getString("TABLENAME");
            String comparedColumn = rs.getString("COMPAREDCOLUMN");
            String comparedTable = rs.getString("COMPAREDTABLE");
            String operator = rs.getString("OPERATOR");
            String triggerEvents = rs.getString("TRIGGEREVENTS");
            String msgText = rs.getString("MESSAGETEXT");

            CompareRule rule = new CompareRule(id, code, name, ruletype, status, columnName, columnType, tableName, comparedColumn, comparedTable, comparedvalue, operator, triggerEvents, msgText);

            System.out.println(rule.toString());
            CompareRules.add(rule);
        }
        return CompareRules;

    }

    public CompareRule readRule(int CompareRuleId) throws SQLException {

        Connection connection = super.getConnection();
        String query = "SELECT * FROM COMPARERULE WHERE ID= ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, CompareRuleId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String code = rs.getString("CODE");
            String name = rs.getString("NAME");
            String ruletype = rs.getString("RULETYPE");
            int comparedvalue = rs.getInt("COMPAREDVALUE");
            String status = rs.getString("STATUS");
            String columnName = rs.getString("COLUMNNAME");
            String columnType = rs.getString("COLUMNTYPE");
            String tableName = rs.getString("TABLENAME");
            String comparedColumn = rs.getString("COMPAREDCOLUMN");
            String comparedTable = rs.getString("COMPAREDTABLE");
            String operator = rs.getString("OPERATOR");
            String triggerEvents = rs.getString("TRIGGEREVENTS");
            String msgText = rs.getString("MESSAGETEXT");

            CompareRule rule = new CompareRule(id, code, name, ruletype, status, columnName, columnType, tableName, comparedColumn, comparedTable, comparedvalue, operator, triggerEvents, msgText);
            System.out.println(rule.toString());
            return rule;
        }
        return null;
    }

    public boolean deleteRule(int CompareRuleId) throws SQLException {

        String query = "DELETE FROM COMPARERULE WHERE ID= ?";

        Connection connection = super.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1,CompareRuleId);
        int i = ps.executeUpdate();

        if (i > 0){
            System.out.println("true");
            return true;
        }
        System.out.println("false");
        return false;

    }

    public boolean updateRule(CompareRule rule) throws SQLException {

        String query = "UPDATE COMPARERULE SET" +
                " COMPAREDVALUE= ?, " +
                " COLUMNNAME= ?, " +
                " COMPAREDCOLUMN= ?, " +
                " COMPAREDTABLE= ?, " +
                " STATUS= ?," +
                " OPERATOR= ?," +
                " MESSAGETEXT= ?" +
                " WHERE ID= ? ";

        Connection connection = super.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, rule.getCompareValue());
        ps.setString(2, rule.getColumnName());
        ps.setString(3, rule.getComparedColumn());
        ps.setString(4, rule.getComparedTable());
        ps.setString(5, rule.getStatus());
        ps.setString(6, rule.getOperator());
        ps.setString(7, rule.getMessageText());
        ps.setInt(8,rule.getId());
        int i = ps.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }

}
