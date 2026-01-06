package net.live_on.mayaj_kino_portfolio.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * データベース接続（DataSource）を管理するファクトリクラス。
 * TomcatのJNDIリソースからコネクションプールを取得します。
 */
public class DataSourceFactory {

	private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

	// アプリケーション全体で共有されるデータソース保持用
	private static final DataSource DATA_SOURCE;

	static {
		try {
			// Tomcatの設定（context.xml）を参照するためのコンテキストを生成
			InitialContext context = new InitialContext();

			// JNDI名でDataSourceを照会
			// Tomcat側で設定したコネクションプールが利用可能になる
			DATA_SOURCE = (DataSource) context.lookup("java:comp/env/jdbc/portfolio");

			LOGGER.info("DataSourceの初期化に成功");

		} catch (NamingException e) {
			LOGGER.log(Level.SEVERE, "DataSourceの初期化に失敗", e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * コネクションプールからデータベース接続を取得します。
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DATA_SOURCE.getConnection();
	}
}