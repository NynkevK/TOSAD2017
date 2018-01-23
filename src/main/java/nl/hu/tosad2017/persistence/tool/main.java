package nl.hu.tosad2017.persistence.tool;

import nl.hu.tosad2017.model.model.ListRule;
import nl.hu.tosad2017.model.model.ModifyRule;
import nl.hu.tosad2017.model.model.RangeRule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String [ ] args)
    {
        //ToolRangeRuleDAO dao = new ToolRangeRuleDAO();
        //ToolModifyRuleDAO dao1 = new ToolModifyRuleDAO();
        ToolListRuleDAO dao2 = new ToolListRuleDAO();
        //RangeRule rure = new RangeRule("foodpls","asdf","sadfsadf","sadfasadf","asdfsf","sdf","sdf","sdf","sd","asd",5,4);
        //ModifyRule rure1 = new ModifyRule("MRN","ModifyRule","iHateYou","Modify","mod","rure","idkfr","defined","yeswellsaid","insup","idk","poo","ok");
        ListRule rure2 = new ListRule(1,"LR","ListRule","JeRuleKloptNiet","ListRule","hoi","superKolom","superTabel","defined","MustBeInOfzo","insup","'a','b','c'");
        try {
            // RangeRuleTesters
            //dao.readAllRules();
            //dao.createRule(rure);
            //dao.readRule(5);
            //dao.deleteRule(7);

            // ModifyRuleTesters
            //dao1.createRule(rure1);
            //dao1.readAllRules();
            //dao1.readRule(2);
            //dao1.updateRule(rure1);
            //dao1.deleteRule(2);

            //ListRuleTesters
            //dao2.createRule(rure2);
            dao2.readAllRules();
            //dao2.readRule(1);
            //dao2.updateRule(rure2);
            //dao2.deleteRule(1);
        } catch (SQLException e) {
           e.printStackTrace();
        }

    }
}
