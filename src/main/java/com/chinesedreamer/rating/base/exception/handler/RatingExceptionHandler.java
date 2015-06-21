package com.chinesedreamer.rating.base.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView view = new ModelAndView("exception");
		String errorMessage = "";
		if (ex instanceof BizException) {
			errorMessage = ex.getMessage();
		}else {
			errorMessage = "系统异常，请联系管理员！";
		}
		view.addObject("errorMessage", errorMessage);
		return view;
	}

}