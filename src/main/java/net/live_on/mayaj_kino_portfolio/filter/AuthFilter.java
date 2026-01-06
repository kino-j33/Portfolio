package net.live_on.mayaj_kino_portfolio.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.live_on.mayaj_kino_portfolio.constant.Constant;
import net.live_on.mayaj_kino_portfolio.entity.AccountEntity;

public class AuthFilter implements Filter {

	private static final String[] excludeUris = {
			"^/$",
			"^/public/.*",
			"^/static/.*" };

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse,
			FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		uri = uri.substring(contextPath.length());

		if (isFilterTarget(uri)) {
			HttpSession session = request.getSession(false);
			AccountEntity entity = null;

			if (session != null) {
				entity = (AccountEntity) session.getAttribute(Constant.SESSION_KEY_LOGIN_ACCOUNT);
			}

			//ログインしていなければ、ログイン画面へ遷移する
			if (entity == null) {
				session = request.getSession(true);
				// エラーメッセージのセット
				session.setAttribute("flash_error", "ログインしてください。");
				response.sendRedirect(
						request.getContextPath() + "/public/LoginInitServlet");
				return;
			}
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	private boolean isFilterTarget(String uri) {
		for (String excludeUri : excludeUris) {
			if (uri.matches(excludeUri)) {
				return false;
			}
		}
		return true;
	}
}
