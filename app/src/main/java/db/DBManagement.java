package db;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

	public static void createTables() {
		StringBuilder stringBuilder = getSchemaString();
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			stmt.execute(stringBuilder.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(stmt);
		}
	}

	private static StringBuilder getSchemaString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("CREATE TABLE IF NOT EXISTS game (\r\n" + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "    name VARCHAR(256) NOT NULL,\r\n" + "    release_year INT,\r\n" + "    genre VARCHAR(128),\r\n"
				+ "    scope VARCHAR(128),\r\n" + "    icon VARCHAR(64),\r\n" + "    cover VARCHAR(64)\r\n" + ");\r\n"
				+ "CREATE TABLE IF NOT EXISTS played_time (\r\n" + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "    state VARCHAR(16) NOT NULL,\r\n" + "    platform VARCHAR(128),\r\n"
				+ "    finished_date DATE,\r\n" + "    minutes_played INT,\r\n" + "    game_id INT,\r\n"
				+ "    FOREIGN KEY(game_id) REFERENCES game(id)\r\n" + ");");
		return stringBuilder;
	}
	
	public static void insertExamples() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("INSERT INTO game (name, release_year, genre, scope) VALUES \r\n"
				+ "('The Legend of Zelda: Breath of the Wild', 2017, 'Action-Adventure', 'Open World'),\r\n"
				+ "('Minecraft', 2011, 'Sandbox', 'Open World'),\r\n"
				+ "('The Witcher 3: Wild Hunt', 2015, 'RPG', 'Open World'),\r\n"
				+ "('Among Us', 2018, 'Party', 'Multiplayer'),\r\n"
				+ "('Celeste', 2018, 'Platformer', 'Single Player');");
		stringBuilder.append("INSERT INTO played_time (state, platform, finished_date, minutes_played, game_id) VALUES\r\n"
				+ "('Completed', 'Nintendo Switch', '2020-10-15', 3000, 1),\r\n"
				+ "('In Progress', 'PC', NULL, 4500, 2),\r\n"
				+ "('Completed', 'PlayStation 4', '2017-07-10', 5200, 3),\r\n"
				+ "('Not Started', 'Mobile', NULL, 0, 4),\r\n"
				+ "('Completed', 'PC', '2019-02-20', 800, 5);");
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			stmt.execute(stringBuilder.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(stmt);
		}
	}
}
