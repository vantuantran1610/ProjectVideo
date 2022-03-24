package edu.vn.site.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.vn.common.CookieUtils;
import edu.vn.common.PageInfo;
import edu.vn.common.PageType;
import edu.vn.common.SessionUtils;
import edu.vn.dao.UserDao;
import edu.vn.domain.LoginForm;
import edu.vn.model.User;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// lay thong tin cua cookie (tu dong dang nhap)
		String username = CookieUtils.get("username", request);

		// neu cookie username da ton tai
		if (username == null) {
			PageInfo.prepareAndForwarSite(request, response, PageType.SITE_LOGIN_PAGE);
			return;
		}
		// tiep tuc luu thong tin cua cookie trong session va chuyen den trang homepage
		SessionUtils.add(request, "username", username);
		request.getRequestDispatcher("/Homepage").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			LoginForm form = new LoginForm();
			// do du lieu do nguoi dung nhap vao form
			BeanUtils.populate(form, request.getParameterMap());
			UserDao dao = new UserDao();
			// tim kiem thong tin nguoi dung theo id
			User user = dao.findById(form.getUsername());

			if (user != null && user.getPassword().equals(form.getPassword())) {
				SessionUtils.add(request, "username", user.getUsername());

				if (form.isRemember()) {
					CookieUtils.add("username", form.getUsername(), 24, response);
				} else {
					CookieUtils.add("username", form.getUsername(), 0, response);
				}
				request.setAttribute("isLogin", true);
				request.setAttribute("name", user.getFullname());
				request.getRequestDispatcher("/Homepage").forward(request, response);
				return;
			}
			request.setAttribute("error", "Invalid username or password");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_LOGIN_PAGE);
	}

}
