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
	let listBtn = document.querySelector('#listBtn');
	if(listBtn != null) {
		listBtn.addEventListener('click', function(){
			location.href = '/boardList';
		});
	}

	//삭제버튼 클릭
	//삭제 성공 -> 리스트페이지 호출
	//삭제 실패 -> '삭제 중 예외 발생' 메세지 출력 후 상세페이지(뒤로 가기)
	let deleteBtn = document.querySelector('#deleteBtn');
	if(deleteBtn != null) {
		deleteBtn.addEventListener('click', function(){
			deleteFnc();
		
<%-- 	location.href = '/boardDelete?num=<%=request.getParameter("num") %>'; --%>
		
	});
	
	}
	//삭제 컨펌창 메소드
	function deleteFnc() {
		//컨펌창 : 확인, 취소 버튼일 출력
		//확인 = true를 반환, 취소 = false를 반환
		if(confirm('정말 삭제하시겠습니까?')){
			alert('확인 클릭');
			//버튼 클릭하면 삭제 컨트롤러 요청
			viewForm.action='/boardDelete';
			viewForm.submit();
		} else {
			alert('취소 클릭');
		} 
		
	}
}
</script>
<h2>상세보기</h2>
<form method="post" name="viewForm">

	<input type="hidden" name="num" value="<%=request.getParameter("num")%>">
</form>
<button id='listBtn'>리스트</button>
	
<!-- 글의 작성자만 수정, 삭제가 가능하다.
게시글의 id가 로그인한 id와 동일하다면 수정, 삭제 버튼 보여주기 -->

<%
BoardDto dto = new BoardDto(); 
if(request.getAttribute("read")!=null) {
	dto = (BoardDto)request.getAttribute("read");
	
	String userId = "";
	if(session.getAttribute("userId") != null) {
		userId = session.getAttribute("userId").toString();
		if(userId.equals(dto.getId())) {
			out.print("<button id='deleteBtn'>삭제</button> &nbsp");
			out.print("<button id='editBtn'>수정</button>");
		}
		
	}
}
%>
	

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