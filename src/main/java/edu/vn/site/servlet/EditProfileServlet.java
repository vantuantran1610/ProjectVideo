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
import edu.vn.model.User;

@WebServlet("/EditProfile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doc thong tin username nguoi dung da dang nhap
		String username = SessionUtils.getLoginedUsername(request);
		
		//kiem tra da dang nhap chua
		if (username == null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}
		
		//da dang nhap thanh cong lay duoc username tim kiem theo username de tra ve user
		try {
			UserDao dao = new UserDao();
			User user = dao.findById(username);
			
			//hien thi lai tren form
			request.setAttribute("user", user);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//lay thong tin cua nguoi dung tu form
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			//lay thong tin username da dang nhap hien tai
			String username = SessionUtils.getLoginedUsername(request);
			UserDao dao = new UserDao();
			//tim kiem username trong csdl hien tai de tra ve cac doi tuong user
			User oldUser = dao.findById(username);
			
			//chinh sua lai cac truong khong duoc sua doi (admin)
			user.setAdmin(oldUser.getAdmin());
			//cap nhat thong tin cua user vao csdl
			dao.update(user);
			request.setAttribute("message", "User profile updated!!");
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		//hien thi trang edit profile
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}

}
