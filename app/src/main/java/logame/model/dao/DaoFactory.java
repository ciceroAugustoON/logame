package logame.model.dao;

import db.DB;
import logame.model.dao.impl.GameDaoJDBC;
import logame.model.dao.impl.PlayedTimeDaoJDBC;
import logame.model.entities.Game;
import logame.model.entities.PlayedTime;

public class DaoFactory {
	
	public static GameDao createGameDao() {
		return new GameDaoJDBC(DB.getConnection(), createPlayedTimeDao());
	}
	
	public static PlayedTimeDao createPlayedTimeDao() {
		return new PlayedTimeDaoJDBC(DB.getConnection());
	}

}
