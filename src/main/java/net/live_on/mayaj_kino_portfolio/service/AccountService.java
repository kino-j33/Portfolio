package net.live_on.mayaj_kino_portfolio.service;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import net.live_on.mayaj_kino_portfolio.dao.AccountDao;
import net.live_on.mayaj_kino_portfolio.entity.AccountEntity;
import net.live_on.mayaj_kino_portfolio.exception.DataAccessException;

public class AccountService {
	private static final Logger LOGGER = Logger.getLogger(AccountService.class.getName());
	private final AccountDao accountDao = new AccountDao();

	public AccountEntity getAccountByIdPassword(String id) throws DataAccessException {
		try {
			return accountDao.getAccountByIdAndPassword(id);
		} catch (NamingException | SQLException e) {
			LOGGER.log(Level.SEVERE, "ユーザーIDによるアカウント取得中にエラーが発生しました。ID: " + id, e);
			throw new DataAccessException("ユーザー情報の取得に失敗しました", e);
		}
	}
}
