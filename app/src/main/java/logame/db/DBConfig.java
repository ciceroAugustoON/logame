package logame.db;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import logame.db.util.JavaToSQLMapper;

public class DBConfig {

    public static Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + DBConfig.class.getClassLoader().getResource("").toURI().getPath() + "logame.db");
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void createTable(Class<?> entity) {
        String sql = "CREATE TABLE IF NOT EXISTS " + JavaToSQLMapper.entityToTable(entity);

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
