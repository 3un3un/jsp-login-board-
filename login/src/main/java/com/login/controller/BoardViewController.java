package com.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.dao.BoardDao;


@WebServlet("/boardRead")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//상세
		BoardDao dao = new BoardDao();
		String num = request.getParameter("num");
		
		//조회수
		dao.visitCnt(num);
		//상세보기
		request.setAttribute("read", dao.getOne(num));
		
		//페이지 전환
		request.getRequestDispatcher("boardRead.jsp").forward(request, response);
		
		//삭제를 위하여 번호 request영역에 저장해놓기
		request.setAttribute("num", num);
		
		dao.close();
		
		
		
	}



}
