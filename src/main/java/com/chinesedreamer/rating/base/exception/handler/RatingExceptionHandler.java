package com.chinesedreamer.rating.base.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.chinesedreamer.rating.base.exception.category.BizException;

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
		ModelAndView view = new ModelAndView("exception");
		String errorMessage = "";
		logger.error("{}",ex);
		if (ex instanceof BizException) {
			//errorMessage = ex.getMessage();
			errorMessage = "<a herf='login'>超时，请重新登录</a>";
		}else {
			errorMessage = "系统异常，请联系管理员！";
		}
		view.addObject("errorMessage", errorMessage);
		return view;
	}

}
