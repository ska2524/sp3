package org.zerock.interceptor;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import org.zerock.domain.MemberVO;
import org.zerock.web.LoginController;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	LoginController controller;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); //로그인한적이있는 쿠키가 있냐?
		//쿠키 잇는지 확인
		if(loginCookie != null && request.getSession().getAttribute("member")==null){
			//login한 쿠키가 있어? -> 저장을 map에 저장할거야. //하고 member라는 애는 session에 없다. 
			String sid = loginCookie.getValue();
			
			MemberVO vo =  controller.getMap().get(sid); 
			
			request.getSession().setAttribute("member", vo); //멤버에서 가져온것에서 다시 세션으로 집어넣어준다.
			
			return true; //널이 아니면 있다.
		}
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("member") == null){
			System.out.println("THIS USER IS NOT LOGINED..."); //멤버가져온게 없으면 로그인 안되고 sendredirect
			
			String uri = request.getRequestURI();
			session.setAttribute("dest", uri);//기존에 가려고 하는 uri값을 가져오고 set해줌
			
			
			response.sendRedirect("/login");
			return false; //로그인 안되면 false해서 못들어가게 한다.
			
		}
		return super.preHandle(request, response, handler);
	}
	
	
	
}
