package com.coding404.myweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//@Component // bean 방식 , bean의 생성주기에 따라 실행될 수도 안될수도 있다.
public class UserAuthHandler implements HandlerInterceptor{
	// 로그인 여부를 확인하는 인터셉터 
	// pre - 컨토롤러 들어가기전에 실행
	// post - 컨트롤러에서 dispatcherServlet으로 리턴 전에 실행
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("Controller 요청 전 Handler 실행");
		
		// 로그인이 된 사람 확인
		// true - 컨트롤러가 실행됨, false - 컨트롤러 실행 x 
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		// null 일 경우 error
		if(user_id != null) { // 로그인이 된 사람
			return true; // 컨트롤러를 실행
		}else {
			response.sendRedirect( request.getContextPath() + "/user/login");
			return false;
		}
		
		
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("Controller 요청 후 Handler 실행");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
}
