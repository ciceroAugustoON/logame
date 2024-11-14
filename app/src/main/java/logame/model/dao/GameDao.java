package logame.model.dao;

import java.util.List;

import logame.entities.enumerations.LogState;
import logame.model.entities.Game;

public interface GameDao extends Dao<Game>{
	
	public List<Game> findByState(LogState state);
}
