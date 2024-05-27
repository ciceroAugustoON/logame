package logame;

import java.util.List;

import logame.config.Config;
import logame.db.DBConfig;
import logame.entities.Game;
import logame.entities.PlayedTime;

public class LogameApplication {
    private static String dbVersion = "0.5.0";
    public static void main(String[] args) {
        init();
    }

    public static void init() {
        configuration();


    }

    public static void configuration() {
        Config logameConfigurations = Config.load();

        if (logameConfigurations.isEmpty()) {
            logameConfigurations.create("DBVersion", dbVersion);
            for (Class<?> entity : entities()) {
                DBConfig.createTable(entity);
            }
        }
    }

    public static List<Class<?>> entities() {
        return List.of(Game.class, PlayedTime.class);
    }
}
