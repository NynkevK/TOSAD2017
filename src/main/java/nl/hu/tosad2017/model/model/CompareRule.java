package nl.hu.tosad2017.model.model;

public class CompareRule extends BusinessRule {
    private String comparedColumn;
    private String comparedTable;
    private int compareValue;
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComparedColumn() {
        return comparedColumn;
    }

    public void setComparedColumn(String comparedColumn) {
        this.comparedColumn = comparedColumn;
    }

    public String getComparedTable() {
        return comparedTable;
    }

    public void setComparedTable(String comparedTable) {
        this.comparedTable = comparedTable;
    }

    public int getCompareValue() {
        return compareValue;
    }

    public void setCompareValue(int compareValue) {
        this.compareValue = compareValue;
    }
}
