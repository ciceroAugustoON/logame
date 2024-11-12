package logame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;

import org.sqlite.FileException;

import config.Config;
import db.DB;
import db.DBManagement;
import gui.GUIManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogameApplication extends Application {
    private final static String schema = LogameApplication.class.getClassLoader().getResource("db").getPath() + File.separator + "schema.sql";
    private final static Double dbVersion = 0.5;
    private final static String dbPath = "jdbc:sqlite:" + LogameApplication.class.getClassLoader().getResource("").getPath() + "logame.db";
    private final static String assetsPath = LogameApplication.class.getClassLoader().getResource("").getPath() + "assets/";

    public static void main(String[] args) {
        initLogame();
        launch(args);
    }

    private static void initLogame() {
        try {
            configuration(true);
            DBManagement.insertExamples();
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
            DBManagement.createTables();
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		GUIManager.loadPrimaryStage("Logame", primaryStage, "/gui/MainView.fxml");
	}
}
