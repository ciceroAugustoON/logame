package gui.util;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Responsivity {
	private Double width;
	private Integer columns;
	private Double columnSize;
	private Double paddingX;
	
	private static Map<Stage,Responsivity> listeners = new HashMap<>();
	
	private Responsivity(Double width, Integer columns) {
		this.width = width;
		this.columns = columns;
		calculate();
	}
	
	public void setWidth(Number width) {
		this.width = (Double)width;
		calculate();
	}
	
	public Double getWidth() {
		return width;
	}
	
	public Double getColumnSize() {
		return columnSize;
	}

	public Double getPaddingX() {
		return paddingX;
	}

	public void calculate() {
		if (width >= 1280) {
			paddingX = width * 0.10;
		} else {
			paddingX = 5.0;
		}
		columnSize = (width - paddingX) / columns;
	}
	
	public static void listen(Stage stage, Integer columnsNumber) {
		var resp = new Responsivity(stage.getWidth(), columnsNumber);
		listeners.put(stage, resp);
		stage.widthProperty().addListener((obs, oldValue, newValue) -> {
			resp.setWidth(newValue);
			System.out.println(resp.getWidth());
		});
	}
	
	public static void setPadding(Pane pane) {
		Stage stage = (Stage) pane.getScene().getWindow();
		Double padding = listeners.get(stage).getPaddingX();
		pane.setPadding(new Insets(0, padding, 0, padding));
	}
	
	public static void setWidth(Pane pane, Integer columns) {
		Stage stage = (Stage) pane.getScene().getWindow();
		Double width = listeners.get(stage).getColumnSize() * columns;
		pane.setPrefWidth(width);
	}
}
