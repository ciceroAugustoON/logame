package logame;

import org.sqlite.FileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

import logame.config.Config;
import logame.db.DB;
import logame.db.DBManagement;

public class LogameApplication {
    private final static String schema = LogameApplication.class.getClassLoader().getResource("db").getPath() + "/schema.sql";
    private final static Double dbVersion = 0.5;
    private final static String dbPath = "jdbc:sqlite:" + LogameApplication.class.getClassLoader().getResource("").getPath() + "logame.db";
    private final static String assetsPath = LogameApplication.class.getClassLoader().getResource("").getPath() + "assets/";

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        try {
            configuration(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void configuration(boolean verbose) throws FileException, FileNotFoundException{
        Config logameConfigurations = Config.load();
        DB.setUrl(dbPath);
        if (logameConfigurations.isEmpty()) {
            if (verbose) System.out.println("Creating a new DB...");
            logameConfigurations.create("DBVersion", dbVersion);
            DBManagement.createTables(new BufferedReader(new FileReader(new File(schema))));
            logameConfigurations.create("AssetsPath", assetsPath);
            if (new File(assetsPath).mkdir()) {
                System.out.println("Created!");
            } else {
                throw new FileException("Could not create the AssetsPath");
            }
        } else {
            /*if (logameConfigurations.get("DBVersion") != dbVersion) {
                DBManagement.updateTables(new BufferedReader(new FileReader(new File(schema))));
            }*/
        }
    }
}
