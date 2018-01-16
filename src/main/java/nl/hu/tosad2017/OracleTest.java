package nl.hu.tosad2017;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class OracleTest {
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL  = "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl";
    private static final String DB_USER = "tosad_2017_2b_team1";
    private static final String DB_PASS = "tosad_2017_2b_team1";

    public static void main(String[] args){
        try{
            // Load database driver
            Class.forName(DB_DRIV).newInstance();

            // Create connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);


            Statement statement = connection.createStatement();
            String constraint = "ALTER TABLE connection_test " +
                            "ADD CONSTRAINT CHK_Value CHECK (value IS NOT NULL)";

            // Execute query
            System.out.println(statement.executeUpdate(constraint));

            String plsql = "create or replace trigger aur_med \n" +
                            "    after update of value \n" +
                            "    on connection_test \n" +
                            "    for each row \n" +
                            "begin \n" +
                            "    if(( :new.value - :old.value) > 0.1 * :old.value) \n" +
                            "    then \n" +
                            "        raise_application_error(-20000, 'test gedoe'); \n" +
                            "    end if; \n" +
                            "end;";

            System.out.println(statement.execute(plsql));

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
