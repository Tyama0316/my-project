package baseball.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import baseball.model.PlayerBean;
import baseball.service.PlayerInsert;

@WebServlet("/PlayerInsertController")
public class PlayerInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//リクエスト処理
		request.setCharacterEncoding("UTF-8");
		
		
		response.sendRedirect("player_insert.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエスト処理
		request.setCharacterEncoding("UTF-8");
		String playerName = request.getParameter("playerName");
		String uniformNumber = request.getParameter("uniformNumber");
		String position = request.getParameter("position");
		String jsp;
		try {
			//nullチェック
			if(playerName == null || playerName.isEmpty()
					|| uniformNumber == null || uniformNumber.isEmpty()
					|| position == null || position.isEmpty()) {
				request.setAttribute("errorMessage", "すべての項目を入力してください");
				request.setAttribute("backAddress", "player_insert.jsp");
				jsp = "/error.jsp";
			} else {
				int uniformNumberInt = Integer.parseInt(uniformNumber);
				
				PlayerBean player = new PlayerBean();

				player.setPlayerName(playerName);
				player.setUniformNumber(uniformNumberInt);
				player.setPosition(position);
				
				PlayerInsert insert = new PlayerInsert();
				insert.execute(player);
				
				jsp = "/complete.jsp";
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "背番号には数値を入力してください");
			request.setAttribute("backAddress","player_insert.jsp");
			jsp = "/error.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("backAddress","player_insert.jsp");
			jsp = "/error.jsp";
		}

		// 転送処理
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}
}
