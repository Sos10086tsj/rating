package com.chinesedreamer.rating.web.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinesedreamer.rating.base.exception.AccessInvalidException;
import com.chinesedreamer.rating.system.session.service.UserSessionService;

/**
 * Description: 
 * @author Paris
 * @date Jun 19, 20154:55:48 PM
 * @version beta
 */
public class SessionFilter implements Filter{
	
	@Autowired
	private UserSessionService userSessionService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String uri = httpServletRequest.getServletPath();
		SessionContext.setContext(request);
		
		if (StringUtils.isNotEmpty(uri) && !uri.equals("/index")) {
			//保存session
			this.userSessionService.validateSession();
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public static class SessionContext implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2096933209364358832L;
		
		public static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
		
		public static HttpServletRequest getContext(){  
			if (null == threadLocal.get()) {
				throw new AccessInvalidException("session失效，请重新登录！");
			}
			return (HttpServletRequest)threadLocal.get();
		}
		
		public static void setContext(Object request){   
			threadLocal.set(request);
		}   
		
		public static void cleanContext(){   
			threadLocal.set(null);   
		}
	}
}
