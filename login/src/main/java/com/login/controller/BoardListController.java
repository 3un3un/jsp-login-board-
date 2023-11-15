package com.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.dao.BoardDao;

@WebServlet("/boardList")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 게시글 목록을 조회
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	BoardDao dao = new BoardDao();
	
	int pageNo = 1; //페이지번호
	int amount = 10; //페이지당 게시물 수
	//전달된 값이 있으면 변경, 없으면 기본값
	try {
		if(request.getParameter("pageNo") != null
					&&!"".equals(request.getParameter("pageNo"))){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("amount") != null
				&&!"".equals(request.getParameter("amount"))){
			amount = Integer.parseInt(request.getParameter("amount"));
		}
	} catch (Exception e) {
		System.out.println("파라메터 수집중 예외사항이 발생하였습니다.");
	}

	//시작번호와 끝번호를 계산
   
    // 끝번호 = 페이지번호 * 페이지당 게시물 수
	int endNum = pageNo*amount;
	// 시작번호 = 끝번호 - (페이지당 게시물 수 - 1)
	int startNum = endNum - (amount - 1);
	request.setAttribute("list", dao.getList(startNum, endNum));
	request.getRequestDispatcher("/board/board.jsp").forward(request, response);
	
	dao.close();
	

	
	}

}
