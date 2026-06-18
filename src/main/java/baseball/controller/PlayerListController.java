package baseball.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import baseball.model.PlayerBean;
import baseball.service.PlayerList;

@WebServlet("/PlayerListController")
public class PlayerListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String jsp = "/player_list.jsp";

		try {

			PlayerList list = new PlayerList();

			ArrayList<PlayerBean> playerList =
					list.execute();

			request.setAttribute("playerList", playerList);

		} catch (Exception e) {

			e.printStackTrace();

			jsp = "/error.jsp";

			request.setAttribute("message",
					"選手一覧の取得に失敗しました");

		} finally {

			ServletContext context =
					getServletContext();

			RequestDispatcher rd =
					context.getRequestDispatcher(jsp);

			rd.forward(request, response);

		}
	}
}