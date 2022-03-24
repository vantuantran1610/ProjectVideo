package edu.vn.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import edu.vn.common.SessionUtils;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setAttribute("isLogin", SessionUtils.isLogin((HttpServletRequest) request));
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
