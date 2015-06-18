package com.chinesedreamer.rating.web.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinesedreamer.rating.system.session.service.UserSessionService;

/**
 * Description: 
 * @author Paris
 * @date May 12, 20153:13:27 PM
 * @version beta
 */
public class RatingFilter implements Filter{

	@Resource
	private UserSessionService userSessionService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//增加ctx路径
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		//查看用户是否已经登录
		if(session != null && this.userSessionService.userLogged(session.getId())){
			String ctx = req.getContextPath();
    		session.setAttribute("ctx", (null == ctx  ? "" : ctx) );
		}else {
			((HttpServletResponse)response).sendRedirect("/exception/sessionTimeout");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
