package logame.entities;

import logame.db.DBTemplate.Column;
import logame.db.DBTemplate.NotNull;
import logame.db.DBTemplate.PrimaryKey;
import logame.db.DBTemplate.Table;
import logame.db.DBTemplate.Varchar;

@Table(tableName = "game")
public class Game {
    @NotNull
    @Varchar(limit = 255)
    private String name;

    @Column(columnName = "release_year")
    private Integer releaseYear;

    @Varchar(limit = 64)
    private String genre;

    @Varchar(limit = 64)
    private String scope;

    @Varchar(limit = 64)
    private String icon;

    @Varchar(limit = 64)
    private String cover;
    
    @PrimaryKey
    private Integer id;

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
}
