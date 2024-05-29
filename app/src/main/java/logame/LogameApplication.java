package logame;

import java.util.List;

import org.sqlite.FileException;

import java.io.File;

import logame.config.Config;
import logame.db.DBConfig;
import logame.entities.Game;
import logame.entities.PlayedTime;

public class LogameApplication {
    private final static String dbVersion = "0.5.0";
    private final static String assetsPath = LogameApplication.class.getClassLoader().getResource("").getPath() + "assets/";

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        try {
            configuration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void configuration() throws FileException{
        Config logameConfigurations = Config.load();

        if (logameConfigurations.isEmpty()) {
            logameConfigurations.create("DBVersion", dbVersion);
            for (Class<?> entity : entities()) {
                DBConfig.createTable(entity);
            }

            logameConfigurations.create("AssetsPath", assetsPath);
            if (new File(assetsPath).mkdir()) {
                System.out.println("Created!");
            } else {
                throw new FileException("Could not create the AssetsPath");
            }
        }
    }

    public static List<Class<?>> entities() {
        return List.of(Game.class, PlayedTime.class);
    }
}
