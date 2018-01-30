package nl.hu.tosad2017.persistence.data;

import nl.hu.tosad2017.model.model.DataInfo;
import nl.hu.tosad2017.persistence.target.TargetBaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO extends TargetBaseDAO {

    public List<DataInfo> readAllData() throws SQLException {
        List<DataInfo> data = new ArrayList<DataInfo>();

        Connection connection = super.getConnection();
        String query = "SELECT table_name, column_name, data_type\n" +
                "FROM user_tab_columns\n" +
                "where   table_name= UPPER('VBMG_KLANTEN')    and not data_type = UPPER('DATE')\n" +
                "    or  table_name= UPPER('VBMG_LEVERINGEN') and not data_type = UPPER('DATE') \n" +
                "    or  table_name= UPPER('VBMG_ORDERS')     and not data_type = UPPER('DATE')\n" +
                "    or  table_name= UPPER('VBMG_PRODUCTEN')  and not data_type = UPPER('DATE')\n" +
                "    or  table_name= UPPER('VBMG_LOCATIES')   and not data_type = UPPER('DATE')";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);

        while (rs.next()) {
            String table = rs.getString("table_name");
            String column = rs.getString("column_name");
            String datatype = rs.getString("data_type");

                DataInfo info = new DataInfo(table,column,datatype);
                data.add(info);
        }
        return data;
    }

    public List<DataInfo> readDataByTableName(String tableName) throws SQLException {
        List<DataInfo> data = new ArrayList<DataInfo>();

        Connection connection = super.getConnection();
        String query =  "SELECT table_name, column_name, data_type FROM user_tab_columns " +
                        "where table_name= UPPER(?) and not data_type = UPPER('DATE')";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,tableName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String table = rs.getString("table_name");
            String column = rs.getString("column_name");
            String datatype = rs.getString("data_type");

            DataInfo info = new DataInfo(table,column,datatype);
            data.add(info);
        }
        return data;
    }

}
