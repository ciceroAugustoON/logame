package logame.entities;

import java.util.ArrayList;
import java.util.List;

import logame.db.DBTemplate.Cardinality;
import logame.db.DBTemplate.Column;
import logame.db.DBTemplate.ForeignKey;
import logame.db.DBTemplate.NotNull;
import logame.db.DBTemplate.PrimaryKey;
import logame.db.DBTemplate.Table;

@Table(tableName = "game")
public class Game {
    @NotNull
    private String name;
    @Column(columnName = "release_year")
    private Integer releaseYear;
    private String genre;
    private String scope;
    private String icon;
    private String cover;
    @PrimaryKey
    private Integer id;
    @ForeignKey(cardinality = Cardinality.OneToMany, tableName = "played_time")
    private List<PlayedTime> playedTimes;

    public Game(Integer id, String name) {
        this.id = id;
        this.name = name;
        playedTimes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public Integer getId() {
        return id;
    }

    public List<PlayedTime> getPlayedTimes() {
        return playedTimes;
    }
    public void addPlayedTime(PlayedTime playedTime) {
        playedTimes.add(playedTime);
    }
    public void removePlayedTime(PlayedTime playedTime) {
        playedTimes.remove(playedTime);
    }
}
