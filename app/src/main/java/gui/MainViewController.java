package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import logame.entities.enumerations.LogState;
import logame.model.dao.Dao;
import logame.model.dao.DaoFactory;
import logame.model.entities.Game;

public class MainViewController implements Initializable{
	private Dao<Game> gameDao;
	
	@FXML
	private ChoiceBox<String> stateChoiceBox;
	@FXML
	private ListView<Game> gameListView;
	
	private void loadGames(LogState state) {
		ObservableList<Game> gamesList = FXCollections.observableList(gameDao.findAll());
		gameListView.setItems(gamesList);
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
