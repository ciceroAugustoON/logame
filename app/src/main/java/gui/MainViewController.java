package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import logame.entities.enumerations.LogState;
import logame.model.dao.DaoFactory;
import logame.model.dao.GameDao;
import logame.model.entities.Game;

public class MainViewController implements Initializable{
	private GameDao gameDao;
	private List<AnchorPane> gamesComponent = new ArrayList<>();
	@FXML
	private VBox mainVBox;
	@FXML
	private ChoiceBox<String> stateChoiceBox;
	
	private void loadGames(LogState state) {
		ObservableList<Game> gamesList = FXCollections.observableList(gameDao.findByState(state));
		removeAllGamesGUI();
		for (Game game : gamesList) {
			loadGameGUI(game);
		}
	}
	
	private void loadGameGUI(Game game) {
		ImageView iconImageView = new ImageView(this.getClass().getResource("assets/game.png").toExternalForm());
		iconImageView.setFitWidth(32.0);
		iconImageView.setFitHeight(32.0);
		Label label = new Label(game.getName());
		
		AnchorPane anchorPane = new AnchorPane(iconImageView, label);
		anchorPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#fff"), null, null)));
		anchorPane.setPrefHeight(50);
		anchorPane.setCursor(Cursor.HAND);
		
		iconImageView.setLayoutX(15);
		iconImageView.setLayoutY((anchorPane.getPrefHeight() - iconImageView.getFitHeight()) / 2);
		
		label.setFont(new Font(18));
		label.setLayoutX(iconImageView.getLayoutX() + iconImageView.getFitWidth() + 15);
		label.setLayoutY((50 - 30) / 2);
		
		gamesComponent.add(anchorPane);
		mainVBox.getChildren().add(anchorPane);
	}
	
	private void removeAllGamesGUI() {
		for (AnchorPane anchorPane : gamesComponent) {
			mainVBox.getChildren().remove(anchorPane);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gameDao = DaoFactory.createGameDao();
		
		List<String> states = new ArrayList<String>();
		List.of(LogState.values()).forEach(item -> {states.add(item.patternName());});
		
		ObservableList<String> logStates = FXCollections.observableList(states);
		stateChoiceBox.setItems(logStates);
		stateChoiceBox.setValue(states.getFirst());
		
		loadGames(LogState.PLAYING);
		
		stateChoiceBox.valueProperty().addListener((value, oldValue, newValue) -> {
			loadGames(LogState.valueOf(newValue.toUpperCase()));
		});
	}

	
}
