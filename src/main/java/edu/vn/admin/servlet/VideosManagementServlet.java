package edu.vn.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.vn.common.PageInfo;
import edu.vn.common.PageType;
import edu.vn.common.UploadUtils;
import edu.vn.dao.VideoDao;
import edu.vn.model.Video;

@WebServlet(urlPatterns = { "/Admin/VideosManagement", "/Admin/VideosManagement/create",
		"/Admin/VideosManagement/update", "/Admin/VideosManagement/delete", "/Admin/VideosManagement/reset",
		"/Admin/VideosManagement/edit", })
@MultipartConfig
public class VideosManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();

		if (url.contains("edit")) {
			edit(request, response);
			return;
		}
		if (url.contains("delete")) {
			delete(request, response);
			return;
		}
		if (url.contains("reset")) {
			reset(request, response);
			return;
		}

		Video video = new Video();
		video.setPoster("/images/destop.jsp");
		findAll(request, response);
		request.setAttribute("video", video);
		PageInfo.prepareAndForwar(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();

		if (url.contains("create")) {
			create(request, response);
			return;
		}

		if (url.contains("delete")) {
			delete(request, response);
			return;
		}

		if (url.contains("update")) {
			update(request, response);
			return;
		}

		if (url.contains("reset")) {
			reset(request, response);
			return;
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoId");

		if (id == null) {
			request.setAttribute("error", "Video id is required!");
			PageInfo.prepareAndForwar(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}

		try {
			VideoDao dao = new VideoDao();
			Video video = dao.findById(id);

			if (video == null) {
				request.setAttribute("error", "Video id not found!");
				PageInfo.prepareAndForwar(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
				return;
			}
			dao.delete(id);
			request.setAttribute("message", "Video is deleted!");
			request.setAttribute("video", new Video());
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForwar(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();

		try {
			// do thong tin cho nguoi dung nhap vao
			BeanUtils.populate(video, request.getParameterMap());
			VideoDao dao = new VideoDao();
			Video oldVideo = dao.findById(video.getVideoId());

			// neu nguoi dung khong lua chon anh de upload len thi ta chon lai anh cu
			if (request.getPart("cover").getSize() == 0) {
				video.setPoster(oldVideo.getPoster());

			} else {
				video.setPoster("uploads/" + UploadUtils.processUploadField("cover", video.getVideoId(), "/uploads", request));
			}
			dao.update(video);
			request.setAttribute("video", video);
			request.setAttribute("message", "Video is updated!");
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForwar(request, response, PageType.VIDEO_MANAGEMENT_PAGE);

	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) {

		try {
			VideoDao dao = new VideoDao();
			List<Video> list = dao.findAll();
			request.setAttribute("videos", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Video video = new Video();
		try {
			// do du lieu tu form vao doi tuong video
			BeanUtils.populate(video, request.getParameterMap());

			// thiet lap thong tin luu tru cua poster
			video.setPoster("/uploads/" + UploadUtils.processUploadField("cover", video.getVideoId(), "/uploads", request));

			// chen vao csdl
			VideoDao dao = new VideoDao();
			dao.insert(video);
			request.setAttribute("video", video);
			request.setAttribute("message", "Video is inserted!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());

		}
		findAll(request, response);
		// hien thi ra view
		PageInfo.prepareAndForwar(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		video.setPoster("images/destop.jpg");
		request.setAttribute("video", video);
		findAll(request, response);
		PageInfo.prepareAndForwar(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoId");

		if (id == null) {
			request.setAttribute("error", "Video id is required!");
			PageInfo.prepareAndForwar(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}

		try {
			VideoDao dao = new VideoDao();
			Video video = dao.findById(id);
			request.setAttribute("video", video);
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForwar(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

}
