package logame.entities;

import java.util.Date;

import logame.db.DBTemplate.Cardinality;
import logame.db.DBTemplate.Column;
import logame.db.DBTemplate.ForeignKey;
import logame.db.DBTemplate.NotNull;
import logame.db.DBTemplate.PrimaryKey;
import logame.db.DBTemplate.Table;
import logame.entities.enumerations.LogState;

@Table(tableName = "played_time")
public class PlayedTime {
    @NotNull
    private String platform;
    @Column(columnName = "finished_date")
    private Date finishedDate;
    @Column(columnName = "minutes_played")
    private Integer minutesPlayed;
    @NotNull
    private LogState state;
    @PrimaryKey
    private Integer id;
    @ForeignKey(cardinality = Cardinality.OneToMany, tableName = "game", foreignFieldName = "id")
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
    public LogState getState() {
        return state;
    }
    public void setState(LogState state) {
        this.state = state;
    }
}
