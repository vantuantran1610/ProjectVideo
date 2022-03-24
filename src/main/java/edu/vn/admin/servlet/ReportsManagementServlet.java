package edu.vn.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.vn.common.PageInfo;
import edu.vn.common.PageType;
import edu.vn.dao.FavoriteDao;
import edu.vn.dao.VideoDao;
import edu.vn.domain.FavoriteReport;
import edu.vn.domain.FavoriteUserReport;
import edu.vn.model.Video;

@WebServlet("/ReportsManagementServlet")
public class ReportsManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReportsManagementServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		reportFavoritesByVideos(request, response);
		reportFavoriteUsersByVideos(request, response);
		PageInfo.prepareAndForwar(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void reportFavoritesByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			FavoriteDao dao = new FavoriteDao();

			List<FavoriteReport> list = dao.reportsFavoritesByVideos();
			request.setAttribute("favList", list);
		} catch (Exception e) {
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
	
	
	protected void reportFavoriteUsersByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String videoUserId = request.getParameter("videoUserId");
			VideoDao vdao = new VideoDao();
			List<Video> vlist = vdao.findAll();
			if (videoUserId == null && vlist.size() > 0) {
				videoUserId = vlist.get(0).getVideoId();
			}

			FavoriteDao dao = new FavoriteDao();
			List<FavoriteUserReport> list = dao.reportFavoriteUsersByVideo(videoUserId);

			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vidList", vlist);
			request.setAttribute("favUserList", list);
		} catch (Exception e) {
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

}
