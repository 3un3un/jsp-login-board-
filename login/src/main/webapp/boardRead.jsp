<%@page import="com.login.dto.BoardDto"%>
<%@page import="com.login.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">

window.onload = function(){
	//목록버튼 클릭
	document.querySelector('#listBtn').addEventListener('click', function(){
		location.href = '/boardList';
	});
	//삭제버튼 클릭
	document.querySelector('#deleteBtn').addEventListener('click', function() {
		<%
		BoardDto dto = new BoardDto();
		%>
		alert('삭제버튼 클릭');
		location.href = '/boardDelete?num=<%=request.getParameter("num") %>';
		
	});
	
} 
</script>

	<button id='listBtn'>목록</button>
	<button id='editBtn'>수정</button>
	<button id='deleteBtn'>삭제</button>
<%
/* BoardDto dto = new BoardDto(); */
if(request.getAttribute("read")!=null) {
	dto = (BoardDto)request.getAttribute("read");
}
%>
	
<h2>상세보기</h2>
<ul>
	<li>번호 : <%= dto.getNum() %></li>
	<li>제목 : <%= dto.getTitle()%></li>
	<li>내용 : <%= dto.getContent()%></li>
	<li>아이디 : <%= dto.getId()%></li>
	<li>게시날 : <%= dto.getPostdate()%></li>
	<li>조회수 : <%= dto.getVisitcount()%></li>
</ul>



</body>
</html>