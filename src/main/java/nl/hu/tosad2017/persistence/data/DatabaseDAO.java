package nl.hu.tosad2017.persistence.data;

import nl.hu.tosad2017.model.model.DataInfo;
import nl.hu.tosad2017.persistence.tool.ToolBaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO extends ToolBaseDAO {

    public List<DataInfo> readAllData() throws SQLException {
        List<DataInfo> data = new ArrayList<DataInfo>();

        Connection connection = super.getConnection();
        String query = "SELECT table_name, column_name, data_type\n" +
                "FROM user_tab_columns\n" +
                "where   table_name= UPPER('KLANTEN')    and not data_type = UPPER('DATE')\n" +
                "    or  table_name= UPPER('LEVERINGEN') and not data_type = UPPER('DATE') \n" +
                "    or  table_name= UPPER('ORDERS')     and not data_type = UPPER('DATE')\n" +
                "    or  table_name= UPPER('PRODUCTEN')  and not data_type = UPPER('DATE')\n" +
                "    or  table_name= UPPER('LOCATIES')   and not data_type = UPPER('DATE')";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);

        while (rs.next()) {
            String table = rs.getString("table_name");
            String column = rs.getString("column_name");
            String datatype = rs.getString("data_type");

                DataInfo info = new DataInfo(table,column,datatype);
                System.out.println(info.toString());
                data.add(info);
        }
        return data;
    }
}
