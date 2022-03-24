package edu.vn.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.vn.common.EmailUtils;
import edu.vn.common.PageInfo;
import edu.vn.common.PageType;
import edu.vn.dao.UserDao;
import edu.vn.domain.Email;
import edu.vn.model.User;

@WebServlet("/ForgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForgotPasswordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_FORGOT_PASSWORD_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String username = request.getParameter("username");

			UserDao dao = new UserDao();
			User user = dao.findByUsernameAndEmail(username, emailAddress);
			if (user == null) {
				request.setAttribute("error", "Username or email are incorrect");
			} else {
				Email email = new Email();
				email.setFrom("vantuandev161099@gmail.com");
				email.setFromPassword("Tuantran16101999");
				email.setTo(emailAddress);
				email.setSubject("Forgot Passwrod Funciton");

				StringBuilder sb = new StringBuilder();
				sb.append("Dear ").append(username).append("<br>");
				sb.append("You are userd the forgot password function. <br>");
				sb.append("You password is <br>").append(user.getPassword()).append("<br>");
				sb.append("reards<br>");
				sb.append("Adminnistrator");

				email.setContent(sb.toString());
				EmailUtils.send(email);
				request.setAttribute("message",
						"Email sent to the email Address. " + "Please check and get your Passwrod");

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_FORGOT_PASSWORD_PAGE);
	}

}
