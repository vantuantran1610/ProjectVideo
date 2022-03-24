package edu.vn.site.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.vn.common.PageInfo;
import edu.vn.common.PageType;
import edu.vn.dao.VideoDao;
import edu.vn.model.Video;

@WebServlet("/Homepage")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomepageServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			VideoDao dao = new VideoDao();

			List<Video> list = dao.findAll();

			request.setAttribute("videos", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_HOME_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
