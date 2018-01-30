package nl.hu.tosad2017;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import nl.hu.tosad2017.model.model.BusinessRule;
import nl.hu.tosad2017.model.model.CompareRule;
import nl.hu.tosad2017.model.model.ListRule;
import nl.hu.tosad2017.model.model.OracleRuleGenerator;
import nl.hu.tosad2017.model.model.RangeRule;
import nl.hu.tosad2017.persistence.target.OracleTargetDao;

public class OracleTest {
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL  = "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl";
    private static final String DB_USER = "tosad_2017_2b_team1_target";
    private static final String DB_PASS = "tosad_2017_2b_team1_target";

    public static void main(String[] args){
        try{
            // Load database driver
            Class.forName(DB_DRIV).newInstance();

            // Create connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);


            Statement statement = connection.createStatement();
//            String constraint = "ALTER TABLE connection_test " +
//                            "ADD CONSTRAINT CHK_Value CHECK (value IS NOT NULL)";
//
//            // Execute query
//            System.out.println(statement.executeUpdate(constraint));
//
//            String plsql = "create or replace trigger aur_med \n" +
//                            "    after update of value \n" +
//                            "    on connection_test \n" +
//                            "    for each row \n" +
//                            "begin \n" +
//                            "    if(( :new.value - :old.value) > 0.1 * :old.value) \n" +
//                            "    then \n" +
//                            "        raise_application_error(-20000, 'test gedoe'); \n" +
//                            "    end if; \n" +
//                            "end;";
//
//            System.out.println(statement.execute(plsql));
            OracleRuleGenerator gen = new OracleRuleGenerator();
            OracleTargetDao dao = new OracleTargetDao();
            RangeRule rule = new RangeRule(1, "RANG", "VBMG_LEVER_RANG2", "Het aantal moet tussen 2 en 4", "Range", "aantal", "varchar", "VBMG_LEVERINGEN"
                    , "defined", "between", "INS UPD", 2, 40);
            CompareRule rule2 = new CompareRule(1, "COMP", "VBMG_LEVER_COMP1", "Het aantal mag niet kleiner zijn dan 1", "Attribute", "aantal", "varchar", "VBMG_LEVERINGEN"
                    , "defined", ">=", "INS UPD", "Prijs", "", 1);
            ListRule rule3 = new ListRule(1, "LIST", "VBMG_LEVER_LIST1", "Het type moet BOE of KLA zijn", "List", "Product_type", "varchar", "VBMG_PRODUCTEN"
                    , "defined", "in", "UPD", "'BOE', 'KLA'");
            CompareRule rule4 = new CompareRule(1, "COMP", "VBMG_COMP2", "Levering datum kleiner dan orders datum", "Inter-Entity", "datum", "varchar", "VBMG_LEVERINGEN"
                    , "defined", ">", "INS UPD", "aanvraag_datum", "VBMG_ORDERS", 0);
//            statement.execute(rule.accept(gen));
//            statement.execute(rule2.accept(gen));
//            statement.execute(rule3.accept(gen));
//            dao.insertTrigger(rule.accept(gen));
            dao.insertTrigger(rule3.accept(gen));

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
