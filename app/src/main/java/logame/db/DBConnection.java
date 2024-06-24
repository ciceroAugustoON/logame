package logame.db;


import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String sqlUrl;
    
    public static java.sql.Connection connect() throws SQLException {
        if (sqlUrl != null) {
            return DriverManager.getConnection(sqlUrl); 
        } else {
            throw new SQLException("The SQL url is not defined!");
        }
    }

    public static void setSqlUrl(String sqlUrl) {
        DBConnection.sqlUrl = sqlUrl;
    }
}
