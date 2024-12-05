package gui.util;

import javafx.scene.paint.Color;

public class LogColor {
	private Color color;
	
	private LogColor(Color color) {
		this.color = color;
	}
	
	public static LogColor rgb(int red, int green, int blue) {
		return new LogColor(Color.rgb(red, green, blue));
	}
	
	// Helper method
	private String format(double val) {
	    String in = Integer.toHexString((int) Math.round(val * 255));
	    return in.length() == 1 ? "0" + in : in;
	}

	public String toHexString() {
	    return "#" + (format(color.getRed()) + format(color.getGreen()) + format(color.getBlue()) + format(color.getOpacity()))
	            .toUpperCase();
	}
}
