package logame.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import logame.db.util.JavaToSQLMapper;

public class DBConfig {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + DBConfig.class.getClassLoader().getResource("") + "logame.db");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void createTable(Class<?> entity) {
        String sql = "CREATE TABLE IF NOT EXISTS " + JavaToSQLMapper.entityToTable(entity);

        System.out.println(sql);
    }
    
}
