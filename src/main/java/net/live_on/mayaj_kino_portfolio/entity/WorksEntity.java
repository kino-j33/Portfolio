package net.live_on.mayaj_kino_portfolio.entity;

import java.sql.Date;
import java.util.Comparator;

public class WorksEntity {

	// @formatter:off
 private String title;        // 作品名
 private String languages;    // 使用言語
 private Date workDate;       // 作品作成日
 private String imgPath;      // 画像パス
 private String gitUrl;       // GitのURL
 private String demoUrl;      // デモサイトのURL
 private String demoInformation;       // デモ用ログインID
 private String demoLoginCredentials; // デモ用ログインPassword
 private String description;  // 概要
 private String position;     // ポジション・担当
 private String conditions;   // 開発条件
 private String approach;     // 取り組み方
 private String learning;     // 学び
 // @formatter:on

	/**
	* Works 情報を保持する Entity
	*/
	public WorksEntity(
			String title,
			String languages,
			Date workDate,
			String imgPath,
			String gitUrl,
			String demoUrl,
			String demoInformation,
			String demoLoginCredentials,
			String description,
			String position,
			String conditions,
			String approach,
			String learning) {
		this.title = title;
		this.languages = languages;
		this.workDate = workDate;
		this.imgPath = imgPath;
		this.gitUrl = gitUrl;
		this.demoUrl = demoUrl;
		this.demoInformation = demoInformation;
		this.demoLoginCredentials = demoLoginCredentials;
		this.description = description;
		this.position = position;
		this.conditions = conditions;
		this.approach = approach;
		this.learning = learning;
	}

	public String getTitle() {
		return title;
	}

	public String getLanguages() {
		return languages;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public String getImgPath() {
		return imgPath;
	}

	public String getGitUrl() {
		return gitUrl;
	}

	public String getDemoUrl() {
		return demoUrl;
	}

	public String getDemoInformation() {
		return demoInformation;
	}

	public String getDemoLoginCredentials() {
		return demoLoginCredentials;
	}

	public String getDescription() {
		return description;
	}

	public String getPosition() {
		return position;
	}

	public String getConditions() {
		return conditions;
	}

	public String getApproach() {
		return approach;
	}

	public String getLearning() {
		return learning;
	}

	@Override
	public String toString() {
		return "WorksEntity [title=" + title
				+ ", languages=" + languages
				+ ", workDate=" + workDate
				+ ", imgPath=" + imgPath
				+ ", gitUrl=" + gitUrl
				+ ", demoUrl=" + demoUrl
				+ ", demoInformation=" + demoInformation
				+ ", demoLoginCredentials=" + demoLoginCredentials
				+ ", description=" + description
				+ ", position=" + position
				+ ", conditions=" + conditions
				+ ", approach=" + approach
				+ ", learning=" + learning
				+ "]";
	}

	public static Comparator<WorksEntity> workDateComparator() {
		return Comparator.comparing(WorksEntity::getWorkDate, Comparator.nullsLast(
				Comparator.naturalOrder()))
				.thenComparing(WorksEntity::getTitle);
	}
}
