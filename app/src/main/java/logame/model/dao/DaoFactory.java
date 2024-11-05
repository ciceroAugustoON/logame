package logame.model.dao;

import logame.db.DB;
import logame.model.dao.impl.GameDaoJDBC;
import logame.model.dao.impl.PlayedTimeDaoJDBC;
import logame.model.entities.Game;
import logame.model.entities.PlayedTime;

public class DaoFactory {
	
	public static Dao<Game> createGameDao() {
		return new GameDaoJDBC(DB.getConnection());
	}
	
	public static Dao<PlayedTime> createPlayedTimeDao() {
		return new PlayedTimeDaoJDBC(DB.getConnection());
	}

}
