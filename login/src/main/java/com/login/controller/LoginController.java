package com.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.MemberDao;
import com.login.dto.MemberDto;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/loginAction")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 로그인 처리
	 *  - 요청데이터 확인
	 *  	id, pw가 일치하는 사용자가 있는지 확인 후
	 *  	사용자가 있으면 - 로그인 성공
	 *  		-> 사용자정보를 세션에 저장 후 board.jsp 페이지로 이동
	 *  	사용자가 없으면 - 로그인 실패
	 *  		-> 'id, 비밀번호를 확인해주세요' 메세지를 띄우고 이전페이지로 이동
	 *  - 요청메서드에 따라 doGet, doPost 메서드가 실행된다.
	 *  요청방식(method) - 요청방식 지정하지 않으면 get방식
	 *  			  - 요청방식에 해당하는 메서드가 구현되지 않으면 405에러가 발생할 수 있다.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//1. 요청 데이터 수집
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//2. 사용자 정보 조회 (MemeberDao.login(id, pw))
		MemberDao dao = new MemberDao();
		MemberDto dto = dao.login(id, pw);
		
		//3. 화면 전환
		if(dto != null) { //로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("dto", dto);
			session.setAttribute("userId", dto.getId());
			//세션에 저장하기 때문에 Redirect사용하여도 상관 X!!
			//request.getRequestDispatcher("/board/board.jsp").forward(request, response);
			
			//BoardList 서블릿을 사용하기 위하여 sendRedirect 사용
			//forward방식으로 전환시 405 오류 발생 가능성 O
			//loginAction -> post
			//list -> get
			//요청받을 당시 방식(메소드)가 유지되므로 405 오류 발생 가능성!!
			response.sendRedirect("/boardList");
		} else {
			//로그인 실패
			//1번 방식
			//request.getRequestDispatcher("/login.jsp?isError=1").forward(request, response);
			
			//2번 방식 - 서브릿의 한글깨짐 처리
//			response.setContentType("text/html; charset=UTF-8");
//			response.getWriter().append("<script>");
//			response.getWriter().append("	alert('아이디 비밀번호를 확인해주세요.');");
//			response.getWriter().append("	history.back();");
//			response.getWriter().append("</script>");
			
			//3번 방식 - 2번처럼 서블릿이 아닌 jsp 이용하기 : msgbox 만들기
			request.setAttribute("msg", "아이디 비밀번호를 확인해주세요.");
			request.getRequestDispatcher("/msgbox.jsp").forward(request, response);
		}
		dao.close();
		
	}

}
