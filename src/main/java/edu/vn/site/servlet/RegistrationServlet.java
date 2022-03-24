package edu.vn.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.vn.common.PageInfo;
import edu.vn.common.PageType;
import edu.vn.dao.UserDao;
import edu.vn.model.User;

@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_REGISTRATION_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {		
			//do du lieu tu form
			BeanUtils.populate(user, request.getParameterMap());
			
			UserDao dao = new UserDao();
			dao.insert(user);
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.setAttribute("user", user);
		
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_REGISTRATION_PAGE);
	}

}
