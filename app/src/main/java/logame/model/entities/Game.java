package logame.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private Integer releaseYear;
    private String genre;
    private String scope;
    private String icon;
    private String cover;
    private List<PlayedTime> playedTimes = new ArrayList<>();

    public Game() {}

    public Game(Integer id, String name) {
        this.id = id;
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
    public void setId(Integer id) {
        this.id = id;
    }
    public void addAllPlayedTimes(List<PlayedTime> playedTimes) {
    	this.playedTimes.addAll(playedTimes);
    }
    public void addPlayedTime(PlayedTime playedTime) {
    	playedTimes.add(playedTime);
    }
    public void removePlayedTime(PlayedTime playedTime) {
    	playedTimes.remove(playedTime);
    }
    public List<PlayedTime> getPlayedTimes() {
        return playedTimes;
    }
    @Override
    public String toString() {
        return getName();
    }
}
