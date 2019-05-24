package com.cafe24.mysite.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.vo.UserVo;

/**
 * 게시물 접근 권한 확인
 * : 세션과 게시물 소유자 여부
 * @author jgseo
 *
 */
public class AuthBoardInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. @Auth 여부 확인
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		if(auth == null) {
			return true;
		}
		
		//3. session 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/board");
			return false;
		}
		
		//4. session의 authUser null 여부 확인
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/board");
			return false;
		}
		
		//5. 경로변수 userNo 값이 있다면 해당 게시물 작성자와 같은지 비교
		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long userNo = Long.parseLong((String) pathVariables.get("userNo"));	//게시물 작성자 No.
		
		if(userNo != null && authUser.getNo() != userNo) {
			response.sendRedirect(request.getContextPath() + "/board");
			return false;
		}
		
		return true;
	}

}
