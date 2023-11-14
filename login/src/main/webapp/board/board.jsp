<%@page import="com.login.dto.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(session.getAttribute("userId") != null
		&& !"".equals(session.getAttribute("userId").toString())){
	
	out.print(session.getAttribute("userId").toString()+"님 환영합니다.");
	out.print("<button id = 'logoutBtn'>로그아웃</button>");
} else { // 로그인 안하고 접근할 때
	out.print("<button id = 'loginBtn'>로그인</button>");
	
}
%>
<script type="text/javascript">
window.onload = function(){
	//로그아웃 버튼 처리
	let logoutBtn = document.querySelector('#logoutBtn');
	if(logoutBtn != null){
		logoutBtn.onclick = function() {
			//로그아웃 처리 -> 로그인 페이지로 이동
			location.href="/logoutAction";
		}
	}
	//로그인 버튼 처리
	let loginBtn = document.querySelector('#loginBtn');
	if(loginBtn != null) {
		document.querySelector('#loginBtn').addEventListener('click', function(){
			//로그인 페이지로 이동
			location.href="/login.jsp";
		});
		
	}

	
}

</script>
<%-- <%
if(session.getAttribute("userId") != null
&& !"".equals(session.getAttribute("userId").toString())){
	out.print(session.getAttribute("userId").toString()+"님 환영합니다.");
%>
<button id = "logoutBtn">로그아웃</button>
<% 
} else { // 로그인 안하고 접근할 때
%>
<button id = "loginBtn">로그인</button>

<% } %> --%>



<h2>게시글 목록 조회</h2>
<table border="1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>

<%
if(request.getAttribute("list") == null) {
	out.print("<td colspan='4'>게시글이 존재하지 않습니다.</td>");
} else {
	List<BoardDto> list = (List<BoardDto>)request.getAttribute("list");

	for(BoardDto dto : list){
%>
	<tr>
		<td><%=dto.getNum() %></td>
		<td><a href="/boardRead?num=<%=dto.getNum()%>"><%=dto.getTitle() %></a></td>
		<td><%=dto.getId() %></td>
		<td><%=dto.getPostdate() %></td>
	</tr>
<% 		
	}

}
%>

</table>
</body>
</html>