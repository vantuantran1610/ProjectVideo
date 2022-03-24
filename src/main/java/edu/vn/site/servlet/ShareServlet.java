package edu.vn.site.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.vn.common.EmailUtils;
import edu.vn.common.PageInfo;
import edu.vn.common.PageType;
import edu.vn.common.SessionUtils;
import edu.vn.dao.ShareDao;
import edu.vn.domain.Email;
import edu.vn.model.Share;
import edu.vn.model.User;
import edu.vn.model.Video;

@WebServlet("/ShareVideo")
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShareServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!SessionUtils.isLogin(request)) {
			response.sendRedirect("Login");
			return;
		}
		String videoId = request.getParameter("videoId");

		if (videoId == null) {
			response.sendRedirect("/Hompage");
			return;
		}

		request.setAttribute("videoId", videoId);
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_SHARE_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String videoId = request.getParameter("videoId");

			if (videoId == null) {
				request.setAttribute("error", "Video Id is null!");
			} else {
				Email email = new Email();
				email.setFrom("vantuandev161099@gmail.com");
				email.setFromPassword("Tuantran16101999");
				email.setTo(emailAddress);
				email.setSubject("Share Favorite Video");

				StringBuilder sb = new StringBuilder();
				sb.append("Dear Ms/Mr. <br> ");
				sb.append("The video is more interesting and I want to share with you? <br>");
				sb.append("Please click the link<br>").append(String.format("<a href=''>View Video</a><br>", videoId));
				sb.append("Regards<br>");
				sb.append("Adminnistrator");

				email.setContent(sb.toString());
				EmailUtils.send(email);

				ShareDao dao = new ShareDao();

				Share share = new Share();

				share.setEmails(emailAddress);
				share.setSharedDate(new Date());
				String username = SessionUtils.getLoginedUsername(request);
				User user = new User();
				user.setUsername(username);

				share.setUser(user);
				Video video = new Video();
				video.setVideoId(videoId);
				share.setVideo(video);

				dao.insert(share);
				request.setAttribute("message", "video link has been sent");

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwarSite(request, response, PageType.SITE_SHARE_PAGE);
	}
}
