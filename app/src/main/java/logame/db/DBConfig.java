package logame.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import logame.db.util.JavaToSQLMapper;

public class DBConfig {

    public static void createTable(Class<?> entity) {
        String sql = "CREATE TABLE IF NOT EXISTS " + JavaToSQLMapper.entityToTable(entity);

        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
