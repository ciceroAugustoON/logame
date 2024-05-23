package logame;

import logame.db.DBTemplate.Table;
import logame.entities.Game;
import logame.entities.PlayedTime;
import logame.entities.enumerations.LogState;

public class App {
    public static void main(String[] args) {
        Game g = new Game(1, "Hollow Knight");
        Table tb = g.getClass().getAnnotation(Table.class);

        System.out.println("Entity: " + Game.class.getName());
        System.out.println("Table: " + tb.tableName());

        PlayedTime playedTime = new PlayedTime(1, LogState.PLAYING);
        tb = playedTime.getClass().getAnnotation(Table.class);
        
        System.out.println("Entity: " + playedTime.getClass().getName());
        System.out.println("Table: " + tb.tableName());
    }
}
