package nl.hu.tosad2017.persistence.tool;

import nl.hu.tosad2017.model.model.ModifyRule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolModifyRuleDAO extends ToolBaseDAO {

    public boolean createRule(ModifyRule rule) throws SQLException {

        String query = "INSERT INTO MODIFYRULE" +
                "(ID,CODE,NAME,RULETYPE,QUERY,STATUS,COLUMNNAME,COLUMNTYPE,TABLENAME,OPERATOR" +
                ",TRIGGEREVENTS,MESSAGETEXT)" +
                "VALUES(0,?,?,?,?,?,?,?,?,?,?,?)";

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
        ps.setString(9, rule.getOperator());
        ps.setString(10, rule.getTriggerEvents());
        ps.setString(11, rule.getMessageText());
        int i = ps.executeUpdate();

        if (i > 0) {
            return true;
        }
        return false;
    }

    public boolean updateRule(ModifyRule rule) throws SQLException {

        String query = "UPDATE MODIFYRULE SET" +
                " QUERY= ?," +
                " STATUS= ?," +
                " COLUMNNAME= ?," +
                " COLUMNTYPE= ?," +
                " TABLENAME= ?," +
                " OPERATOR= ?," +
                " TRIGGEREVENTS= ?," +
                " MESSAGETEXT= ?" +
                " WHERE ID= ?";

        Connection connection = super.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, rule.getQuery());
        ps.setString(2, rule.getStatus());
        ps.setString(3, rule.getColumnName());
        ps.setString(4, rule.getColumnType());
        ps.setString(5, rule.getTableName());
        ps.setString(6, rule.getOperator());
        ps.setString(7, rule.getTriggerEvents());
        ps.setString(8, rule.getMessageText());
        ps.setInt(9,rule.getId());
        int i = ps.executeUpdate();

        if (i > 0) {
            return true;
        }

        return false;
    }

    public boolean deleteRule(int modifyRuleId) throws SQLException {

        String query = "DELETE FROM MODIFYRULE WHERE ID= ?";

        Connection connection = super.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1,modifyRuleId);
        int i = ps.executeUpdate();

        if (i > 0){
            return true;
        }
        return false;
    }

    public List<ModifyRule> readAllRules() throws SQLException {

        List<ModifyRule> modifyRules = new ArrayList<ModifyRule>();

        Connection connection = super.getConnection();
        String query = "SELECT * FROM MODIFYRULE";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("ID");
            String code = rs.getString("CODE");
            String name = rs.getString("NAME");
            String ruletype = rs.getString("RULETYPE");
            String v_query = rs.getString("QUERY");
            String status = rs.getString("STATUS");
            String columnName = rs.getString("COLUMNNAME");
            String columnType = rs.getString("COLUMNTYPE");
            String tableName = rs.getString("TABLENAME");
            String operator = rs.getString("OPERATOR");
            String triggerEvents = rs.getString("TRIGGEREVENTS");
            String msgText = rs.getString("MESSAGETEXT");

            ModifyRule rule = new ModifyRule(id, code, name, msgText, ruletype, columnName, columnType, tableName, status, operator, triggerEvents, v_query);
            modifyRules.add(rule);
        }
        return modifyRules;
    }

    public ModifyRule readRule(int modifyRuleId) throws SQLException {

        Connection connection = super.getConnection();
        String query = "SELECT * FROM MODIFYRULE WHERE ID= ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, modifyRuleId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String code = rs.getString("CODE");
            String name = rs.getString("NAME");
            String ruletype = rs.getString("RULETYPE");
            String v_query = rs.getString("QUERY");
            String status = rs.getString("STATUS");
            String columnName = rs.getString("COLUMNNAME");
            String columnType = rs.getString("COLUMNTYPE");
            String tableName = rs.getString("TABLENAME");
            String operator = rs.getString("OPERATOR");
            String triggerEvents = rs.getString("TRIGGEREVENTS");
            String msgText = rs.getString("MESSAGETEXT");

            ModifyRule rule = new ModifyRule(id, code, name, msgText, ruletype, columnName, columnType, tableName, status, operator, triggerEvents, v_query);
            return rule;
        }
        return null;
    }
}
