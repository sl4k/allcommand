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
        boolean statusOfOperation = dataBaseOperationBO.executeValuesUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @Test(dependsOnMethods = "addValues")
    public void updateValues() throws SQLException {
        String sqlQuery = "UPDATE test_table SET name=\"updated_name\" WHERE id=777";
        boolean statusOfOperation = dataBaseOperationBO.executeValuesUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @Test(dependsOnMethods = "updateValues")
    public void deleteValues() throws SQLException {
        String sqlQuery = "DELETE FROM test_table WHERE id=777";
        boolean statusOfOperation = dataBaseOperationBO.executeValuesUpdating(sqlQuery);
        Assert.assertTrue(statusOfOperation, "Operation wasn't successful");
    }

    @AfterClass
    public void closeConnection() throws SQLException {
        Database.closeConnection();
    }
}
