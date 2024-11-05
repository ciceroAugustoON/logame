package logame.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logame.db.DB;
import logame.db.DbException;
import logame.entities.enumerations.LogState;
import logame.model.dao.Dao;
import logame.model.entities.PlayedTime;

public class PlayedTimeDaoJDBC implements Dao<PlayedTime> {
	private Connection conn;
	
	public PlayedTimeDaoJDBC(Connection conn) {
		this.conn = conn;
	}

    @Override
    public List<PlayedTime> findAll() {
        String sql = "SELECT * FROM played_time";
        PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<PlayedTime> playedTimes = new ArrayList<>();
			while (rs.next()) {
				PlayedTime playedTime = instantiatePlayedTime(rs);
				playedTimes.add(playedTime);
			}
			return playedTimes;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stmt);
		}
    }

	public List<PlayedTime> findByGameId(Integer id) {
        String sql = "SELECT * FROM played_time WHERE game_id = ?";
        PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			List<PlayedTime> playedTimes = new ArrayList<>();
			while (rs.next()) {
				PlayedTime playedTime = instantiatePlayedTime(rs);
				playedTimes.add(playedTime);
			}
			return playedTimes;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stmt);
		}
    }

    @Override
    public PlayedTime findById(Number id) {
        String sql = "SELECT * FROM played_time WHERE id = ?";
        PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, (Integer)id);
			rs = stmt.executeQuery();
			PlayedTime playedTime = null;
			if (rs.next()) {
				playedTime = instantiatePlayedTime(rs);
			}
			return playedTime;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stmt);
		}
    }

    @Override
    public void create(PlayedTime entity) {
        String sql = "INSERT INTO played_time(state, platform, finished_date, minutes_played, game_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, entity.getState().toString());
            stmt.setString(2, entity.getPlatform());
            stmt.setDate(3, new java.sql.Date(entity.getFinishedDate().getTime()));
            stmt.setInt(4, entity.getMinutesPlayed());
            stmt.setInt(5, entity.getGameId());
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
    public void update(PlayedTime entity) {
        String sql = "UPDATE played_time SET state = ?, platform = ?, finished_date = ?, minutes_played = ? WHERE id = ?";
        PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, entity.getState().toString());
            stmt.setString(2, entity.getPlatform());
            stmt.setDate(3, new java.sql.Date(entity.getFinishedDate().getTime()));
            stmt.setInt(4, entity.getMinutesPlayed());
            stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(stmt);
		}
    }
    
    private PlayedTime instantiatePlayedTime(ResultSet rs) throws SQLException{
    	PlayedTime playedTime = new PlayedTime(rs.getInt("id"), LogState.valueOf(rs.getString("state")));
        playedTime.setPlatform(rs.getString("platform"));
        playedTime.setFinishedDate(rs.getDate("finished_date"));
        playedTime.setMinutesPlayed(rs.getInt("minutes_played"));
        playedTime.setGameId(rs.getInt("game_id"));
		return playedTime;
	}
    
}
