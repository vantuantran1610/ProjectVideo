package edu.vn.site.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.vn.common.SessionUtils;
import edu.vn.dao.FavoriteDao;
import edu.vn.model.Favorite;
import edu.vn.model.User;
import edu.vn.model.Video;

@WebServlet("/LikeVideo")
public class LikeVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LikeVideoServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!SessionUtils.isLogin(request)) {
			response.sendRedirect("Login");
			return;
		}
		String page = request.getParameter("page");
		String videoId = request.getParameter("videoId");

		if (videoId == null) {
			response.sendRedirect("/Hompage");
			return;
		}

		try {
			FavoriteDao dao = new FavoriteDao();
			Favorite favorite = new Favorite();
			Video video = new Video();
			video.setVideoId(videoId);
			favorite.setVideo(video);

			String username = SessionUtils.getLoginedUsername(request);

			User user = new User();
			user.setUsername(username);
			favorite.setUser(user);

			favorite.setLikedDate(new Date());

			dao.insert(favorite);

			request.setAttribute("message", "Video is added to Favorite");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		if (page == null) {
			page = "/Homepage";

		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
