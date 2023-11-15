package com.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.dao.BoardDao;


@WebServlet("/boardDelete")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String num = request.getParameter("num");
		BoardDao dao = new BoardDao();
		int res = dao.deleteOne(num);
		  if(res == 1) { //삭제 성공시
			  request.setAttribute("msg", "삭제되었습니다.");
			  request.setAttribute("url", "/boardList");
			  request.getRequestDispatcher("msgbox.jsp").forward(request, response);
		  } else{ //삭제 실패시
			  request.setAttribute("msg", "삭제 중 예외가 발생되었습니다.\n 관리자에게 문의하세요.");
			  request.getRequestDispatcher("/msgbox.jsp").forward(request, response);
		  
		  }
		 
		dao.close();
	}


}
