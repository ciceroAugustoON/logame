package logame.model.entities;

import java.io.Serializable;
import java.util.Date;

import logame.entities.enumerations.LogState;

public class PlayedTime implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String platform;
    private Date finishedDate;
    private Integer minutesPlayed;
    private LogState state;
    private Integer gameId;

    public PlayedTime(Integer id, LogState state) {
        this.id = id;
        this.state = state;
    }
    
    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public Date getFinishedDate() {
        return finishedDate;
    }
    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }
    public Integer getMinutesPlayed() {
        return minutesPlayed;
    }
    public void setMinutesPlayed(Integer minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LogState getState() {
        return state;
    }
    public void setState(LogState state) {
        this.state = state;
    }
    public Integer getGameId() {
        return gameId;
    }
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}
