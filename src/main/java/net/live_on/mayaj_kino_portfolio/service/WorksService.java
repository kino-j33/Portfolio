package net.live_on.mayaj_kino_portfolio.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import net.live_on.mayaj_kino_portfolio.dao.WorksDao;
import net.live_on.mayaj_kino_portfolio.entity.WorksEntity;
import net.live_on.mayaj_kino_portfolio.exception.DataAccessException;

public class WorksService {
	private static final Logger LOGGER = Logger.getLogger(WorksService.class.getName());
	private final WorksDao worksDao = new WorksDao();

	public List<WorksEntity> getWorks() throws DataAccessException {
		try {
			return worksDao.getWorks();
		} catch (NamingException | SQLException e) {
			LOGGER.log(Level.SEVERE, "作品情報の取得中にデータベースエラーが発生しました", e);
			throw new DataAccessException("Works の取得に失敗しました", e);
		}
	}
}
