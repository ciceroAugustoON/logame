package logame.db;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    
    public static Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + DBConfig.class.getClassLoader().getResource("").toURI().getPath() + "logame.db");
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
