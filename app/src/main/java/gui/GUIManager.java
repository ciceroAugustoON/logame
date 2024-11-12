package gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIManager {

	public static void loadPrimaryStage(String title, Stage primaryStage, String pathFXML) {
		if (pathFXML == null) {
			throw new RuntimeException("The pathFXML is not defined");
		}
		try {
        	Parent parent = FXMLLoader.load(GUIManager.class.getResource(pathFXML));
			Scene scene = new Scene(parent);
			
			primaryStage.setScene(scene);
			// To ensure that all tasks are terminated.
            primaryStage.setOnCloseRequest(event -> {
                Platform.exit();
                System.exit(0);
            });
            primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
