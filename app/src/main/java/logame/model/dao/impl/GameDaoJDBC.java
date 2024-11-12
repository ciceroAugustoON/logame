package logame.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import logame.model.dao.Dao;
import logame.model.entities.Game;

public class GameDaoJDBC implements Dao<Game>{
	private Connection conn;
	
	public GameDaoJDBC(Connection conn) {
		this.conn = conn;
	}

    @Override
    public List<Game> findAll() {
        PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM game");
			rs = stmt.executeQuery();
			List<Game> games = new ArrayList<>();
			while (rs.next()) {
				Game game = instantiateGame(rs);
                games.add(game);
			}
			return games;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stmt);
		}
    }

    @Override
    public Game findById(Number id) {
        PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT * FROM game WHERE id = ?");
			stmt.setInt(1, (Integer)id);
			rs = stmt.executeQuery();
			Game game = null;
			if (rs.next()) {
				game = instantiateGame(rs);
			}
			return game;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stmt);
		}
    }

    @Override
    public void create(Game entity) {
        PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("INSERT INTO game(name, release_year, genre, scope, icon, cover) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, entity.getName());
			stmt.setInt(2, entity.getReleaseYear());
            stmt.setString(3, entity.getGenre());
            stmt.setString(4, entity.getScope());
            stmt.setString(5, entity.getIcon());
            stmt.setString(6, entity.getCover());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				entity.setId(rs.getInt(1));
			} else {
				throw new DbException("Unexpected Error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(stmt);
		}
    }

    @Override
    public void update(Game entity) {
        String sql = "UPDATE game SET name = ?, release_year = ?, genre = ?, scope = ?, icon = ?, cover = ? WHERE id = ?";
        PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getReleaseYear());
            stmt.setString(3, entity.getGenre());
            stmt.setString(4, entity.getScope());
            stmt.setString(5, entity.getIcon());
            stmt.setString(6, entity.getCover());
            stmt.setInt(7, entity.getId());
            stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(stmt);
		}
    }
    
    private Game instantiateGame(ResultSet rs) throws SQLException {
    	Game game = new Game(rs.getInt("id"), rs.getString("name"));
        game.setReleaseYear(rs.getInt("release_year"));
        game.setGenre(rs.getString("genre"));
        game.setScope(rs.getString("scope"));
        game.setIcon(rs.getString("icon"));
        game.setCover(rs.getString("cover"));
        return game;
    }

}
