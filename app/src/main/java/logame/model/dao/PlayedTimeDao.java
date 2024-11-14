package logame.model.dao;

import java.util.List;

import logame.entities.enumerations.LogState;
import logame.model.entities.Game;
import logame.model.entities.PlayedTime;

public interface PlayedTimeDao extends Dao<PlayedTime>{
	
	public List<PlayedTime> findByGameId(Integer id);
}
