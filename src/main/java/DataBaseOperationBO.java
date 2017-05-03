import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseOperationBO {

    public boolean executeSelection(String sqlQuery) throws SQLException {
        ResultSet rs = Database.executeStatement(sqlQuery);
        boolean isResultPrsent = false;
        //Extract data from result set
        while (rs.next()) {
            isResultPrsent = true;
            //Retrieve by column name
            int id = rs.getInt("id");
            String name = rs.getString("name");

            //Display values
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
        }

        rs.close();
//        stmt.close();

        return isResultPrsent;
    }

    public boolean executeUpdating(String sqlQuery) throws SQLException {
        int status = Database.executeUpdate(sqlQuery);
        if(sqlQuery.toLowerCase().contains("create table") || sqlQuery.toLowerCase().contains("drop table") || sqlQuery.toLowerCase().contains("alter table")){
            return status==0;
        }else{
            return status>0;
        }
    }
}
