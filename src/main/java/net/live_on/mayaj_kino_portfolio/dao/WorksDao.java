package net.live_on.mayaj_kino_portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import net.live_on.mayaj_kino_portfolio.data.DataSourceFactory;
import net.live_on.mayaj_kino_portfolio.entity.WorksEntity;

public class WorksDao {
	public List<WorksEntity> getWorks()
			throws NamingException, SQLException {
		try (Connection connection = DataSourceFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"select id, title, languages, img_path, git_url, demo_url, demo_information, demo_login_credentials, work_date, description, position, conditions, approach, learning from works");) {

			List<WorksEntity> worksList = new ArrayList<>();

			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {
		// @formatter:off
					WorksEntity worksEntity = new WorksEntity(
						      resultSet.getString("title")			// 作品名
						    , resultSet.getString("languages")		// 使用言語
						    , resultSet.getDate("work_date")		// 作成日
						    , resultSet.getString("img_path")		// 画像パス
						    , resultSet.getString("git_url")		// GitHub URL
						    , resultSet.getString("demo_url")		// デモURL
						    , resultSet.getString("demo_information")		// デモ用の情報
						    , resultSet.getString("demo_login_credentials")	// デモ用のログイン情報
						    , resultSet.getString("description")	// 概要
						    , resultSet.getString("position")		// ポジション
						    , resultSet.getString("conditions")		// 開発条件
						    , resultSet.getString("approach")		// 取り組み方
						    , resultSet.getString("learning")		// 学び
						);
					// @formatter:on

					worksList.add(worksEntity);
				}
				return worksList;
			}
		}
	}
}
