package nl.hu.tosad2017.persistence.tool;

import nl.hu.tosad2017.model.model.ModifyRule;
import nl.hu.tosad2017.model.model.RangeRule;

import java.sql.SQLException;

public class main {
    public static void main(String [ ] args)
    {
        ToolRangeRuleDAO dao = new ToolRangeRuleDAO();
        ToolModifyRuleDAO dao1 = new ToolModifyRuleDAO();
        //RangeRule rure = new RangeRule("foodpls","asdf","sadfsadf","sadfasadf","asdfsf","sdf","sdf","sdf","sd","asd",5,4);
        ModifyRule rure1 = new ModifyRule("MRN","ModifyRule","iHateYou","Modify","mod","rure","idkfr","defined","yeswellsaid","insup","idk","poo","ok");
        try {
            // RangeRuleTesters
            dao.readAllRules();
            //dao.createRule(rure);
            //dao.readRule(5);
            //dao.deleteRule(7);

            // ModifyRuleTesters
            //dao1.createRule(rure1);
            //dao1.readAllRules();
            //dao1.readRule(2);
            //dao1.updateRule(rure1);
            //dao1.deleteRule(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
