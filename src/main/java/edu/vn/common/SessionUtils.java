package edu.vn.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	
	//cho phep luu thuoc tinh trong dooi tuong session
	public static void add(HttpServletRequest request, String name, Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}
	
	
	//lay gia tri cua thuoc tinh truyen vao cua session
	public static Object get(HttpServletRequest request, String name) {
		HttpSession session = request.getSession();

		return session.getAttribute(name);
	}

	//huy bo session
	public static void invalidate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
	}
	
	
	//kiem tra nguoi dung da dang nhap hay chua
	public static boolean isLogin(HttpServletRequest request) {
		return get(request, "username") != null;
	}
	
	
	//tra ve gia tri cua username da dang nhap trong he thong
	public static String getLoginedUsername(HttpServletRequest request) {
		Object username = get(request, "username");
		return username == null? null : username.toString();
	}
}
