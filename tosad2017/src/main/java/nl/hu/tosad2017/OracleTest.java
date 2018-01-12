package nl.hu.tosad2017;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class OracleTest {
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL  = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String DB_USER = "TEST";
    private static final String DB_PASS = "test";

    public static void main(String[] args){
        try{
            // Load database driver
            Class.forName(DB_DRIV).newInstance();

            // Create connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);


            Statement statement = connection.createStatement();
            String constraint = "ALTER TABLE medewerkers " +
                            "ADD CONSTRAINT CHK_Functie CHECK (functie in ('TRAINER', 'DIRECTEUR', 'VERKOPER', 'BOEKHOUDER', 'MANAGER'))";

            // Execute query
            System.out.println(statement.executeUpdate(constraint));

            String plsql = "create or replace trigger aur_med\n" +
                            "    after update of maandsal\n" +
                            "    on medewerkers\n" +
                            "    for each row\n" +
                            "begin\n" +
                            "    if(( :new.maandsal - :old.maandsal) > 0.1 * :old.maandsal)\n" +
                            "    then\n" +
                            "        raise_application_error(-20000, 'Maandsalaris te veel verhoogd: meer dan 10 procent');\n" +
                            "    end if;\n" +
                            "end;";

            System.out.println(statement.execute(plsql));

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
