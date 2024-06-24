package logame;

import org.sqlite.FileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

import logame.config.Config;
import logame.db.DBConnection;
import logame.db.DBManagement;

public class LogameApplication {
    private final static String schema = LogameApplication.class.getClassLoader().getResource("db").getPath() + "/schema.sql";
    private final static String dbPath = "jdbc:sqlite:" + LogameApplication.class.getClassLoader().getResource("").getPath() + "logame.db";
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

    public static void configuration() throws FileException, FileNotFoundException{
        Config logameConfigurations = Config.load();

        if (logameConfigurations.isEmpty()) {
            logameConfigurations.create("DB", dbPath);
            DBConnection.setSqlUrl(dbPath);
            DBManagement.createTables(new BufferedReader(new FileReader(new File(schema))));
            logameConfigurations.create("AssetsPath", assetsPath);
            if (new File(assetsPath).mkdir()) {
                System.out.println("Created!");
            } else {
                throw new FileException("Could not create the AssetsPath");
            }
        }
    }
}
