package net.live_on.mayaj_kino_portfolio.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordUtil {
	private PasswordUtil() {
	}

	private static final Logger LOGGER = Logger.getLogger(PasswordUtil.class.getName());
	private static final Argon2 ARGON2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

	/**
	 * パスワードを照合します（ログイン時）
	 * @param hashedPassword DBに保存されているハッシュ
	 * @param password 入力された生パスワード
	 * @return 一致すればtrue
	 */
	public static boolean verify(final String hashedPassword, final char[] password) {
		try {
			// nullチェックを追加
			if (hashedPassword == null || password == null) {
				LOGGER.log(Level.WARNING, "パスワード照合に失敗：引数にnullが渡されました");
				return false;
			}

			return ARGON2.verify(hashedPassword, password);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "パスワード検証中に予期しないエラーが発生しました", e);
			return false;
		} finally {
			ARGON2.wipeArray(password); // パスワードのクリア
		}
	}
}