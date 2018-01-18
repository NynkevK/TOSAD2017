package nl.hu.tosad2017.persistence;

import java.sql.SQLException;

public interface RangeRuleDAO {
    public boolean insertRangeRule(String operator, String code, String kolomNaam,  int minvalue, int maxvalue) throws SQLException;
}
