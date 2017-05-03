import java.sql.*;


public class Database {

    static final String JDBC_DRIVER = PropertyReader.getValue("jdbs.driver");
    static final String DB_URL = PropertyReader.getValue("db.url");

    static final String USER = PropertyReader.getValue("user.name");
    static final String PASS = PropertyReader.getValue("user.pass");

    private static Connection conn = null;

    private static Connection createConnection() {

        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            return createConnection();
        } else {
            return conn;
        }
    }

    public static ResultSet executeStatement(String sqlQuery) throws SQLException {
        getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        System.out.println("Creating statement...");
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int executeUpdate(String sqlQuery) throws SQLException {
        getConnection();
        int status = -1;
        Statement stmt = null;
        System.out.println("Creating statement...");
        try {
            stmt = conn.createStatement();
            status = stmt.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void closeConnection() throws SQLException {
        conn.close();
    }

}
