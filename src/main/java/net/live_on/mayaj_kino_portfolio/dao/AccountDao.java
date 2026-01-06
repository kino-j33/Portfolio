package net.live_on.mayaj_kino_portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import net.live_on.mayaj_kino_portfolio.data.DataSourceFactory;
import net.live_on.mayaj_kino_portfolio.entity.AccountEntity;

public class AccountDao {
	public AccountEntity getAccountByIdAndPassword(String id)
			throws NamingException, SQLException {
		try (Connection connection = DataSourceFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(
						"select id, password from account "
								+ "where id = ?");) {

			ps.setString(1, id);
			try (ResultSet resultSet = ps.executeQuery();) {
				AccountEntity accountEntity = null;

				if (resultSet.next()) {
					// @formatter:off
					accountEntity = new AccountEntity(
											resultSet.getString("id")
										  , resultSet.getString("password"));
					// @formatter:on
				}

				return accountEntity;
			}
		}
	}
}
