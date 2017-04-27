package org.zerock.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 모델andview에 model에 저장된 회원정보가 있으면 반환해준다.(로그인처리가 된다.)
		super.postHandle(request, response, handler, modelAndView);
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle.............");
		System.out.println("preHandle.............");
		
		System.out.println(handler); //homecontroller의 메소드이다.
//		Method method = (Method)handler; 
		
		HandlerMethod method = (HandlerMethod)handler;
		
		System.out.println(method.getBean());
		
		String uid = request.getParameter("uid");
		
		if(uid == null || uid.isEmpty()){
			
			response.sendRedirect("login");
			return false;
			
		}
		
		
		return true;
	}

}
