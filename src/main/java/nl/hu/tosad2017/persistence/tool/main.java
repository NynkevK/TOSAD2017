package nl.hu.tosad2017.persistence.tool;

import nl.hu.tosad2017.model.model.RangeRule;
import nl.hu.tosad2017.persistence.tool.ToolRangeRuleDAO;

import java.sql.SQLException;

public class main {
    public static void main(String [ ] args)
    {
        ToolRangeRuleDAO dao = new ToolRangeRuleDAO();
        RangeRule rure = new RangeRule("arn","asdf","sadfsadf","sadfasadf","asdfsf","sdf","sdf","sdf","sd","asd",5,4);
        try {
            System.out.println(dao.readAllRules().toString());
            dao.createRule(rure);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
