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

import net.live_on.mayaj_kino_portfolio.constant.Constant;
import net.live_on.mayaj_kino_portfolio.entity.WorksEntity;
import net.live_on.mayaj_kino_portfolio.exception.DataAccessException;
import net.live_on.mayaj_kino_portfolio.service.WorksService;

/**
 * Portfolioへ遷移する
 * @author Yamashita
 */
@WebServlet("/PortfolioInitServlet")
public class PortfolioInitServlet extends HttpServlet {
	private final WorksService worksService = new WorksService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> errorMessageList = new ArrayList<>();
		List<WorksEntity> worksList = new ArrayList<>();

		try {
			worksList = worksService.getWorks();
			worksList.sort(WorksEntity.workDateComparator().reversed());

		} catch (DataAccessException e) {
			e.printStackTrace();
			errorMessageList.add(e.getMessage());
		}

		if (!worksList.isEmpty()) {
			request.setAttribute("worksList", worksList);
		}

		if (!errorMessageList.isEmpty()) {
			request.setAttribute(Constant.ERROR_MESSAGE_LIST, errorMessageList);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/portfolio.jsp");
		dispatcher.forward(request, response);
	}
}
