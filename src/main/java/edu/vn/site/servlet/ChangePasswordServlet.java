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
import edu.vn.common.SessionUtils;
import edu.vn.dao.UserDao;
import edu.vn.domain.ChangPasswordForm;

@WebServlet("/ChangePassword")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangePasswordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//kiem tra username hien dang nhap hay khong
		String username = SessionUtils.getLoginedUsername(request);
		
		//kiem tra username chua dang nhap 
		if (username == null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}
		
		//neu da dang nhap roi thi set lai username
		request.setAttribute("username", username);
		//forwar den trang changpasswrod
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//lay thong tin username cua nguoi dung hien dang dang nhap
			String username = SessionUtils.getLoginedUsername(request);
			//tao ra doi tuong changpassword
			ChangPasswordForm form = new ChangPasswordForm();
			//do du lieu tu nguoi dung vao
			BeanUtils.populate(form, request.getParameterMap());
			//thiet lap lai username
			request.setAttribute("username", username);
			
			//kiem tra mat khau moi ma nhap lai khong trung nhau
			if (!form.getConfirmPassword().equals(form.getPassword())) {
				request.setAttribute("error", "new passwrod and new confirm passowrd are not identical!");
			} else {
				UserDao dao = new UserDao();
				dao.changPassword(form.getUsername(), form.getCurrentPassowrd(), form.getPassword());
				request.setAttribute("message", "Password has been changed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);
	}

}
