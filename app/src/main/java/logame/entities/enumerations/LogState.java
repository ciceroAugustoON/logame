package logame.entities.enumerations;

import gui.util.LogColor;

public enum LogState {
    PLAYING(LogColor.rgb(30, 145, 214)),
    NEXT(LogColor.rgb(151, 71, 255)),
    BACKLOG(LogColor.rgb(31, 32, 32)),
    FINISHED(LogColor.rgb(238, 186, 11));
	
	private LogColor color;
	
	private LogState(LogColor color) {
		this.color = color;
	}
	
	public LogColor getColor() {
		return color;
	}
	
    public String patternName() {
    	String name = this.toString();
    	return name.substring(0, 1) + name.substring(1).toLowerCase();
    }
}
