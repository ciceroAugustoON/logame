package logame.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import logame.entities.enumerations.LogState;

@Entity
public class PlayedTime {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String platform;
    @Column(name = "finished_date")
    private Date finishedDate;
    @Column(name = "minutes_played")
    private Integer minutesPlayed;
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private LogState state;

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
    public LogState getState() {
        return state;
    }
    public void setState(LogState state) {
        this.state = state;
    }
}
