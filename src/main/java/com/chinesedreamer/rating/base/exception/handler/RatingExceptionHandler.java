package com.chinesedreamer.rating.base.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.chinesedreamer.rating.base.exception.category.BizException;
import com.chinesedreamer.rating.system.session.exception.SessionOverdueException;

/** 
 * Description: 统一处理异常
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午8:37:30 
 * Copyright:   Copyright (c)2015
 */
@Component
public class RatingExceptionHandler implements HandlerExceptionResolver{
	private Logger logger = LoggerFactory.getLogger(RatingExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView view = null;
		String errorMessage = "";
		logger.error("{}",ex);
		if(ex instanceof SessionOverdueException){
			errorMessage = "超时，请重新登录！";
			view = new ModelAndView("login");
		}else if (ex instanceof BizException) {
			errorMessage = "系统异常，请联系管理员！";
			view = new ModelAndView("exception");
		}
		view.addObject("errorMessage", errorMessage);
		return view;
	}

//	 String viewName = determineViewName(ex, request);  
//     if (viewName != null) {// JSP格式返回  
//         if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
//                 .getHeader("X-Requested-With")!= null && request  
//                 .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
//             // 如果不是异步请求  
//             // Apply HTTP status code for error views, if specified.  
//             // Only apply it if we're processing a top-level request.  
//             Integer statusCode = determineStatusCode(request, viewName);  
//             if (statusCode != null) {  
//                 applyStatusCodeIfPossible(request, response, statusCode);  
//             }  
//             return getModelAndView(viewName, ex, request);  
//         } else {// JSON格式返回  
//             try {  
//                 PrintWriter writer = response.getWriter();  
//                 writer.write(ex.getMessage());  
//                 writer.flush();  
//             } catch (IOException e) {  
//                 e.printStackTrace();  
//             }  
//             return null;  
//
//         }  
//     } else {  
//         return null;  
//     }  
}
