package nl.hu.tosad2017.persistence.tool;

import nl.hu.tosad2017.model.model.RangeRule;

import java.sql.SQLException;

public class main {
    public static void main(String [ ] args)
    {
        ToolRangeRuleDAO dao = new ToolRangeRuleDAO();
        RangeRule rure = new RangeRule("foodpls","asdf","sadfsadf","sadfasadf","asdfsf","sdf","sdf","sdf","sd","asd",5,4);
        try {
            dao.readAllRules();
            //dao.createRule(rure);
            //dao.readRule(5);
            //dao.deleteRule(7);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
