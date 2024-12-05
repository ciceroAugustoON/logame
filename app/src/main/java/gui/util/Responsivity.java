package gui.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class Responsivity {
	private Double width;
	private Integer columns;
	private Double columnSize;
	private Double paddingX;
	
	private static Map<Parent,Responsivity> listeners = new HashMap<>();
	
	private static Map<Region, Integer> elementsWidth = new HashMap<>();
	private static Map<Pane, Double> elementsPadding = new HashMap<>();
	
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
		if (width >= 480) {
			paddingX = width * 0.10;
		} else {
			paddingX = 5.0;
		}
		columnSize = (width - paddingX) / columns;
	}
	
	public static void listen(Pane pane, Integer columnsNumber) {
		var resp = new Responsivity(pane.getWidth(), columnsNumber);
		listeners.put(pane, resp);
		pane.widthProperty().addListener((obs, oldValue, newValue) -> {
			resp.setWidth(newValue);
			updateElements();
		});
	}
	
	public static void setPadding(Pane pane, Double paddingTop) {
		Parent parent = (pane.getParent() == null) ? pane : pane.getParent();
		Double padding = listeners.get(parent).getPaddingX();
		pane.setPadding(new Insets(paddingTop, padding, 0, padding));
	}
	
	public static void setWidth(Region region, Integer columns) {
		Parent parent = (region.getParent() == null) ? region : region.getParent();
		Double width = listeners.get(parent).getColumnSize() * columns;
		region.setPrefWidth(width);
	}
	
	public static void keepWidth(Region region, Integer columns) {
		elementsWidth.put(region, columns);
	}
	
	public static void keepPadding(Pane pane, Double topPadding) {
		elementsPadding.put(pane, topPadding);
	}
	
	private static void updateElements() {
		elementsWidth.forEach((key, value) -> {
			setWidth(key, value);
		});
		elementsPadding.forEach((key, value) -> {
			setPadding(key, value);
		});
	}
}
