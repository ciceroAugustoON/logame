package logame.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManagement {
    
    public static void createTables(BufferedReader schema) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String str = schema.readLine();
            while (str != null) {
                stringBuilder.append(str);
                str = schema.readLine();
            }
            try (Connection conn = DB.getConnection(); var stmt = conn.createStatement()) {
                stmt.execute(stringBuilder.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
