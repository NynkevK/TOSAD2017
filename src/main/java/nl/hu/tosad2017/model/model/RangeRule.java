package nl.hu.tosad2017.model.model;

public class RangeRule extends BusinessRule {
    private int minValue;
    private int maxValue;

    public int getMinValue() { return minValue; }

    public void setMinValue(int minValue) { this.minValue = minValue; }

    public int getMaxValue() { return maxValue; }

    public void setMaxValue(int maxValue) { this.maxValue = maxValue; }
}
