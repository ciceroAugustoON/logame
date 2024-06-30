package logame.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logame.db.DBConnection;
import logame.entities.Game;

public class GameRepository implements Repository<Game>{

    @Override
    public List<Game> findAll() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM game";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            do {
                Game game = new Game(rs.getInt("id"), rs.getString("name"));
                game.setReleaseYear(rs.getInt("release_year"));
                game.setGenre(rs.getString("genre"));
                game.setScope(rs.getString("scope"));
                game.setIcon(rs.getString("icon"));
                game.setCover(rs.getString("cover"));
                games.add(game);
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return games;
    }

    @Override
    public Game findById(Number id) {
        String sql = "SELECT * FROM game WHERE id = ?";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (Integer)id);
            ResultSet rs = stmt.executeQuery();
            Game game = new Game(rs.getInt("id"), rs.getString("name"));
            game.setReleaseYear(rs.getInt("release_year"));
            game.setGenre(rs.getString("genre"));
            game.setScope(rs.getString("scope"));
            game.setIcon(rs.getString("icon"));
            game.setCover(rs.getString("cover"));
            return game;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Game entity) {
        String sql = "INSERT INTO game(name, release_year, genre, scope, icon, cover) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getReleaseYear());
            stmt.setString(3, entity.getGenre());
            stmt.setString(4, entity.getScope());
            stmt.setString(5, entity.getIcon());
            stmt.setString(6, entity.getCover());
            stmt.executeUpdate();
            entity.setId(returnLastId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Game entity) {
        String sql = "UPDATE game SET name = ?, release_year = ?, genre = ?, scope = ?, icon = ?, cover = ? WHERE id = ?";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getReleaseYear());
            stmt.setString(3, entity.getGenre());
            stmt.setString(4, entity.getScope());
            stmt.setString(5, entity.getIcon());
            stmt.setString(6, entity.getCover());
            stmt.setInt(7, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int returnLastId() {
        String sql = "SELECT last_insert_rowid() FROM game";
        try (Connection conn = DBConnection.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
