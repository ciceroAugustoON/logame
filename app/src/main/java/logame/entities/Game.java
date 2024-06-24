package logame.entities;

import java.util.ArrayList;
import java.util.List;

public class Game{
    private Integer id;
    private String name;
    private Integer releaseYear;
    private String genre;
    private String scope;
    private String icon;
    private String cover;
    private List<PlayedTime> playedTimes = new ArrayList<>();

    public Game() {}

    public Game(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return getName();
    }
}
