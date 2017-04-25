import org.testng.annotations.Test;

import java.sql.*;

public class CheckCommands {

    @Test
    public void checkExtractionFromDB() throws SQLException {

        String sqlQuery = "SELECT * FROM test_table";
        Connection conn = Database.getConnection();

        ResultSet rs = Database.executeStatement(sqlQuery);

            //Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String name = rs.getString("name");

                //Display values
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
            }
            //STEP 6: Clean-up environment
            rs.close();
//        stmt.close();
            conn.close();

    }


}
