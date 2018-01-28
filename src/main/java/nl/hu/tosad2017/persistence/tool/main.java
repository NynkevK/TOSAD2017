package nl.hu.tosad2017.persistence.tool;

import nl.hu.tosad2017.model.model.*;
import nl.hu.tosad2017.persistence.data.DatabaseDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String [ ] args)
    {
        //ToolRangeRuleDAO dao = new ToolRangeRuleDAO();
        //ToolModifyRuleDAO dao1 = new ToolModifyRuleDAO();
        //ToolListRuleDAO dao2 = new ToolListRuleDAO();
        //ToolCompareRuleDAO dao3 = new ToolCompareRuleDAO();
        //ToolOtherRuleDAO dao4 = new ToolOtherRuleDAO();
        DatabaseDAO dao5 = new DatabaseDAO();

        //RangeRule rure = new RangeRule("foodpls","asdf","sadfsadf","sadfasadf","asdfsf","sdf","sdf","sdf","sd","asd",5,4);
        //ModifyRule rure1 = new ModifyRule("MRN","ModifyRule","iHateYou","Modify","mod","rure","idkfr","defined","yeswellsaid","insup","idk","poo","ok");
        //ListRule rure2 = new ListRule(1,"LR","ListRule","JeRuleKloptNiet","ListRule","hoi","superKolom","superTabel","defined","MustBeInOfzo","insup","'a','b','c'");
        //CompareRule rure3 = new CompareRule(2,"boop","asggfffdf","tuple","sadfasadf","wBOOOOOPPP","sdf","sdf", 20, "woop2", "woooop", 9, "asd", "hhhhh", "puhhh");
        //OtherRule rure4 = new OtherRule(2,"boop","asggfffdf","tuple","sadfasadf","wBOOOOOPPP","booooooop","sdf","qawsedgrfh","qwteg","tyyy","wtho","thye");


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
            //dao2.readAllRules();
            //dao2.readRule(1);
            //dao2.updateRule(rure2);
            //dao2.deleteRule(1);

            //CompareRuleTesters
            //dao3.createRule(rure3);
            //dao3.readAllRules();
            //dao3.readRule(3);
            //dao3.updateRule(rure3);
            //dao3.deleteRule(3);

            //OtherRuleTesters
            //dao4.createRule(rure4);
            //dao4.readAllRules();
            //dao4.readRule(1);
            //dao4.updateRule(rure4);
            //dao4.deleteRule(1);

            //DatabaseTesters
            //dao5.readAllData();
            dao5.readDataByTableName("ORDERS");
        } catch (SQLException e) {
           e.printStackTrace();
        }

    }
}
