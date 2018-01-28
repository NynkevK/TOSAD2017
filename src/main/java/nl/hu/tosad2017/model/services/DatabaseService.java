package nl.hu.tosad2017.model.services;

import nl.hu.tosad2017.model.model.DataInfo;
import nl.hu.tosad2017.persistence.data.DatabaseDAO;

import java.sql.SQLException;
import java.util.List;

public class DatabaseService {
    DatabaseDAO dataDAO = new  DatabaseDAO();

    public List<DataInfo> getAllDataInfo() throws SQLException {
        return dataDAO.readAllData();
    }

    public List<DataInfo> getDataByTableName(String tableName) throws SQLException {
        return dataDAO.readDataByTableName(tableName);
    }

}
