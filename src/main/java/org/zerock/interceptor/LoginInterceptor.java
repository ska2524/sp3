package org.zerock.interceptor;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.zerock.domain.MemberVO;
import org.zerock.web.LoginController;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(request.getMethod().equals("GET")){ //uid 와 upw가 같으면 ? get
			System.out.println(request.getMethod());
			return;
		}
		
		Object result = modelAndView.getModel().get("result");//(모델에 uid와 upw가 있는지 확인한다.)
		
		if(result == null){
			response.sendRedirect("/login?error=fail"); //틀리면 틀렸다고 함.(로그인 실패)
			return;
			
		}
		
		request.getSession().setAttribute("member", result);//멤버를 가져온다 .result로 담는다.
		
		//generate login cookie(쿠키를 원한다면?)
		Cookie loginCookie = new Cookie("loginCookie", (request.getSession().getId())); //쿠키에서는 uid만 가져오지만
		//session에는 memberVO에 들어있는데 둘다 쓸떄는 서로의 정보가 일치하지 않기떄문에 표현할 수가 없다.
		
		//session을 가져오면 정보를 다 담을 수 있다. cookie는 단지 id밖에 못가져온다.
		loginCookie.setMaxAge(60*60); //uid를 가져온다.
		response.addCookie(loginCookie);
		
		HandlerMethod hmethod = (HandlerMethod)handler;
		
		LoginController controller = (LoginController) hmethod.getBean();
		
		controller.getMap().put(request.getSession().getId(), (MemberVO)result); //result -> membervo
		//controller에서 map을 가져와서 넣어줬다.(loginController에서 session id를 보관하고있다. 객체와함께.)
		
		
		Object dest = request.getSession().getAttribute("dest"); //기존경로를 가져오는것
		
		if(dest == null){
			response.sendRedirect("/"); //널이면 /dlek.
		}else{
			response.sendRedirect((String)dest); //아니면 경로 수정해줌
		}
		
		
		
	}
	
	
	
	
}
