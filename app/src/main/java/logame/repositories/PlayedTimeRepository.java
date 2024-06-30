package logame.repositories;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logame.db.DBConnection;
import logame.entities.PlayedTime;
import logame.entities.enumerations.LogState;

public class PlayedTimeRepository implements Repository<PlayedTime> {

    @Override
    public List<PlayedTime> findAll() {
        List<PlayedTime> playedTimes = new ArrayList<>();
        String sql = "SELECT * FROM played_time";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            do {
                PlayedTime playedTime = new PlayedTime(rs.getInt("id"), LogState.valueOf(rs.getString("state")));
                playedTime.setPlatform(rs.getString("platform"));
                playedTime.setFinishedDate(rs.getDate("finished_date"));
                playedTime.setMinutesPlayed(rs.getInt("minutes_played"));
                playedTime.setGameId(rs.getInt("game_id"));
                playedTimes.add(playedTime);
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return playedTimes;
    }

    public List<PlayedTime> findByGameId(Integer id) {
        List<PlayedTime> playedTimes = new ArrayList<>();
        String sql = "SELECT * FROM played_time WHERE game_id = ?";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            do {
                PlayedTime playedTime = new PlayedTime(rs.getInt("id"), LogState.valueOf(rs.getString("state")));
                playedTime.setPlatform(rs.getString("platform"));
                playedTime.setFinishedDate(rs.getDate("finished_date"));
                playedTime.setMinutesPlayed(rs.getInt("minutes_played"));
                playedTime.setGameId(rs.getInt("game_id"));
                playedTimes.add(playedTime);
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return playedTimes;
    }

    @Override
    public PlayedTime findById(Number id) {
        String sql = "SELECT * FROM played_time WHERE id = ?";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (Integer)id);
            ResultSet rs = stmt.executeQuery();
            PlayedTime playedTime = new PlayedTime(rs.getInt("id"), LogState.valueOf(rs.getString("state")));
            playedTime.setPlatform(rs.getString("platform"));
            playedTime.setFinishedDate(rs.getDate("finished_date"));
            playedTime.setMinutesPlayed(rs.getInt("minutes_played"));
            playedTime.setGameId(rs.getInt("game_id"));
            return playedTime;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(PlayedTime entity) {
        String sql = "INSERT INTO played_time(state, platform, finished_date, minutes_played, game_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getState().toString());
            stmt.setString(2, entity.getPlatform());
            stmt.setDate(3, new java.sql.Date(entity.getFinishedDate().getTime()));
            stmt.setInt(4, entity.getMinutesPlayed());
            stmt.setInt(5, entity.getGameId());
            stmt.executeUpdate();
            entity.setId(returnLastId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PlayedTime entity) {
        String sql = "UPDATE played_time SET state = ?, platform = ?, finished_date = ?, minutes_played = ? WHERE id = ?";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getState().toString());
            stmt.setString(2, entity.getPlatform());
            stmt.setDate(3, new java.sql.Date(entity.getFinishedDate().getTime()));
            stmt.setInt(4, entity.getMinutesPlayed());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int returnLastId() {
        String sql = "SELECT last_insert_rowid() FROM played_time";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
