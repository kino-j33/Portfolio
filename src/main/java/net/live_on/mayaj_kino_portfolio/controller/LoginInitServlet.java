package net.live_on.mayaj_kino_portfolio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.live_on.mayaj_kino_portfolio.constant.Constant;
import net.live_on.mayaj_kino_portfolio.entity.AccountEntity;

@WebServlet("/public/LoginInitServlet")
public class LoginInitServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// セッションからログイン情報を取得
		// @formatter:off
		AccountEntity accountEntity = (AccountEntity) session
				.getAttribute(Constant.SESSION_KEY_LOGIN_ACCOUNT);
		// @formatter:on

		if (accountEntity != null) {
			// ログイン済みのためポートフォリオページへ遷移
			response.sendRedirect(request.getContextPath() + "/PortfolioInitServlet");
			return;
		}

		// エラーメッセージの取得
		String flashMessage = (String) session.getAttribute("flash_error");
		List<String> errorMessageList = new ArrayList<>();

		// エラーメッセージの確認
		if (flashMessage != null) {
			errorMessageList.add(flashMessage);
			// セッションから削除
			session.removeAttribute("flash_error");
		}

		if (!errorMessageList.isEmpty()) {
			request.setAttribute(Constant.ERROR_MESSAGE_LIST, errorMessageList);
		}

		// ログインページへ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}