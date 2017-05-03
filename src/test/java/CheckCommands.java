import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class CheckCommands {
    private DataBaseOperationBO dataBaseOperationBO = new DataBaseOperationBO();

    @Test
    public void checkExtractionFromDB() throws SQLException {
        String sqlQuery = "SELECT * FROM test_table";
        boolean statusOfOperation = dataBaseOperationBO.executeSelection(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @Test
    public void addValues() throws SQLException {
        String sqlQuery = "INSERT INTO test_table VALUES(777, \"test_name\");";
        boolean statusOfOperation = dataBaseOperationBO.executeUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @Test(dependsOnMethods = "addValues")
    public void updateValues() throws SQLException {
        String sqlQuery = "UPDATE test_table SET name=\"updated_name\" WHERE id=777";
        boolean statusOfOperation = dataBaseOperationBO.executeUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @Test(dependsOnMethods = "updateValues")
    public void deleteValues() throws SQLException {
        String sqlQuery = "DELETE FROM test_table WHERE id=777";
        boolean statusOfOperation = dataBaseOperationBO.executeUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @Test(priority = 1)
    public void createTable() throws SQLException {
        String sqlQuery = "CREATE TABLE table_from_test (column1 INT, column2 VARCHAR(25), column3 VARCHAR(50));";
        boolean statusOfOperation = dataBaseOperationBO.executeUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @Test(priority = 2)
    public void deleteTable() throws SQLException {
        String sqlQuery = "DROP TABLE table_from_test;";
        boolean statusOfOperation = dataBaseOperationBO.executeUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @Test(priority = 1)
    public void addColumn() throws SQLException {
        String sqlQuery = "alter table test_table add column1 varchar (25);";
        boolean statusOfOperation = dataBaseOperationBO.executeUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @Test(priority = 2)
    public void updateColumnName() throws SQLException {
        String sqlQuery = "alter table test_table change column1 columnID varchar (50);";
        boolean statusOfOperation = dataBaseOperationBO.executeUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @Test(priority = 3)
    public void deleteColumn() throws SQLException {
        String sqlQuery = "alter table test_table drop columnID;";
        boolean statusOfOperation = dataBaseOperationBO.executeUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @AfterClass
    public void closeConnection() throws SQLException {
        Database.closeConnection();
    }
}
