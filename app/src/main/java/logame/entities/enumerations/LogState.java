package logame.entities.enumerations;

public enum LogState {
    PLAYING,
    NEXT,
    BACKLOG,
    FINISHED;
	
    public String patternName() {
    	String name = this.toString();
    	return name.substring(0, 1) + name.substring(1).toLowerCase();
    }
}
