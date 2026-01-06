package net.live_on.mayaj_kino_portfolio.entity;

public class AccountEntity {
	private String id;
	private String password;

	public AccountEntity(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
}
