package nl.hu.tosad2017.model.model;

public class DataInfo {
    protected String tableName;
    protected String columnName;

    public DataInfo(String tableName, String columnName, String dataType){
        this.tableName = tableName;
        this.columnName = columnName;
        this.dataType = dataType;

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    protected String dataType;


}
