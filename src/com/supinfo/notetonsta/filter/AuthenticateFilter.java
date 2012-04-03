package com.supinfo.notetonsta.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebFilter({ "/interventions/mine", "/interventions/new", "/interventions/remove", "/interventions/update" })
public class AuthenticateFilter implements Filter {

	private FilterConfig config;
	
	@Override
	public void destroy() {
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		if(req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) req;
			HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
			
			if(httpServletRequest.getSession().getAttribute("userId") == null) {
				httpServletResponse.sendRedirect(config.getServletContext().getContextPath() + "/login.jsp");
				return;
			}
		}
		chain.doFilter(req, resp);
	}


	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
