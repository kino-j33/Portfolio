package net.live_on.mayaj_kino_portfolio.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.live_on.mayaj_kino_portfolio.constant.Constant;
import net.live_on.mayaj_kino_portfolio.entity.AccountEntity;
import net.live_on.mayaj_kino_portfolio.exception.DataAccessException;
import net.live_on.mayaj_kino_portfolio.service.AccountService;
import net.live_on.mayaj_kino_portfolio.util.PasswordUtil;

/**
 * @author Yamashita
 */
@WebServlet("/public/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger
			.getLogger(LoginServlet.class.getName());
	private final AccountService accountService = new AccountService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// doGet（ブックマークなど）での遷移のためログインページへ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/public/LoginInitServlet");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("userId");
		String password = request.getParameter("password");

		HttpSession session;

		if (id == null || password == null) {
			// 新しいセッションを作成
			session = renewSession(request);
			// IDかパスワードが空の場合はログイン画面に戻す
			session.setAttribute("flash_error", "User ID または Password が違います。");
			response.sendRedirect(request.getContextPath() + "/public/LoginInitServlet");
			return;
		}

		char[] passwordChars = password.toCharArray();
		password = null; // passwordのクリア

		AccountEntity accountEntity;
		try {
			accountEntity = accountService.getAccountByIdPassword(id);

			// @formatter:off
			if (accountEntity != null && PasswordUtil.verify(
											accountEntity.getPassword()
										  , passwordChars)) {
			// @formatter:on
				// ログイン成功
				// 新しいセッションを作成
				session = renewSession(request);
				session.setAttribute(Constant.SESSION_KEY_LOGIN_ACCOUNT, accountEntity);

				// ポートフォリオページへ遷移
				response.sendRedirect(request.getContextPath() + "/PortfolioInitServlet");

			} else { // ログイン失敗
				// 新しいセッションを作成
				session = renewSession(request);

				// エラーメッセージのセット
				session.setAttribute("flash_error", "User ID または Password が違います。");
				// ログインページへ遷移
				response.sendRedirect(
						request.getContextPath() + "/public/LoginInitServlet");
				return;
			}

		} catch (DataAccessException e) { // ログイン失敗
			LOGGER.log(java.util.logging.Level.SEVERE, "ログイン処理中にデータベースエラーが発生しました", e);

			// 新しいセッションを作成
			session = renewSession(request);

			// エラーメッセージのセット
			session.setAttribute("flash_error", "ログイン中にエラーが発生しました。お手数ですが、時間をおいて再度お試しください。");
			// ログインページへ遷移
			response.sendRedirect(
					request.getContextPath() + "/public/LoginInitServlet");
			return;

		} finally {
			// 配列の中身をすべて半角スペースで上書き
			java.util.Arrays.fill(passwordChars, ' ');
		}
	}

	/**
	 * 現在のセッションを破棄し、新しいセッションを生成して返します。
	 * セッション固定攻撃対策として使用します。
	 */
	private HttpSession renewSession(HttpServletRequest request) {
		HttpSession oldSession = request.getSession(false);
		if (oldSession != null) {
			oldSession.invalidate();
		}
		return request.getSession(true);
	}
}
