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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		//System.out.println("dfsdfsd");
		//String num = request.getParameter("num");
		//System.out.println("num" + num);
		
		  if(request.getParameter("num") != null) {
			 String num = request.getParameter("num");
			 System.out.println("num" + num);
			 dao.deleteOne(num);
			 request.getRequestDispatcher("/boardList").forward(request, response);
		  }
		 
		dao.close();
	}


}
