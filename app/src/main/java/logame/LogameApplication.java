package logame;

import org.sqlite.FileException;

import java.io.File;

import logame.config.Config;

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

            logameConfigurations.create("AssetsPath", assetsPath);
            if (new File(assetsPath).mkdir()) {
                System.out.println("Created!");
            } else {
                throw new FileException("Could not create the AssetsPath");
            }
        }
    }
}
