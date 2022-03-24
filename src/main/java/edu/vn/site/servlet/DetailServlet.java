package edu.vn.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.vn.common.PageInfo;
import edu.vn.common.PageType;

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DetailServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_DETAIL_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
