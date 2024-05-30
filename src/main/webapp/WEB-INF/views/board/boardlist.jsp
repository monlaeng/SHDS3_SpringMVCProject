<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script>
setTimeout(() => {
	var message = "${resultMessage}";
	if(message!="") alert(message);
}, 500);
</script>
</head>
<body>
<%-- 	<c:set var="path" value="${pageContext.request.servletContext.contextPath}/board" />
	<c:set var="cpath" value="${pageContext.request.servletContext.contextPath}" /> --%>
<%@ include file="../common/header.jsp" %>
<a href=${path}/board/boardinsert.do>게시글 등록</a>
	

	<script>
	$(function() {
		$("#btnJSON").on("click", f_jsonCall);
		$("#btnJSON2").on("click", f_jsonCall2);
	})
	
	function f_jsonCall2(){
        var output = "<ul>"
        $.ajax({
            url:"${cpath}/json2",
            type: "get",
            success: function(responseData){
                var obj = JSON.parse(responseData);
                var boardlist = obj["boardlist"];
                $.each(boardlist, function(index, item){
                    output += "<li>" + item.title + "</li>"
                });
                output += "</ul>";
                $("#here").html(output);
            },
            error:function(err){
                alert(err);
            }
        });
    }
	
	function f_jsonCall() {
		var boardObj = {bno:100, title:"커피", content:"제일 맛없는 집", writer:"익명"};
		var boardJSON = JSON.stringify(boardObj);
		console.log(boardObj);
		console.log(boardObj.title);
		console.log(boardJSON);

		
		$.ajax({
			url:"${cpath}/json",
			type:"get",
			data:{"jsonInfo": boardJSON},
			success:function(responseData) {
				console.log(responseData);
			},
			error:function(errorInfo) {
				alert(errorInfo);
			}
		});
	}
</script>
	
	<button id="btnJSON">JSON 보내기</button>
	<button id="btnJSON2">JSON 받기</button>
	<div id="here">여기</div>
	
	<h1>Board 목록</h1>
	<form action="${path}/board/selectDelete.do">
	<table border="1">
		<tr>
			<th>선택</th>
			<th>bno</th>
			<th>title</th>
			<th>content</th>
			<th>writer</th>
			<th>pic</th>
			<th>작성일</th>
			<th></th>
		</tr>
		<c:forEach var="board" items="${blist}">
			<tr>
				<td>
					<input type="checkbox" name="checkBno" value="${board.bno}">
				<td>
					<a href="${path}/board/boardDetail.do?bno=${board.bno}">${board.bno}</a>
				</td>
				<td>${board.title}</td>
				<td>${board.content}</td>
				<td>${board.writer}</td>
				<td>	
				<img alt="${board.pic}" 
					src="${path}/resources/uploads/${board.pic}"
					width="50" height="50">
				</td>
				<td>${board.create_date}</td>
				<td>
					<input type="button" onclick="location.href='${path}/board/boardDelete.do?bno=${board.bno}'" value="삭제">
				</td>
			</tr>
		</c:forEach>
	</table>
	<input type="submit" value="선택삭제">
	</form>
</body>
</html>
<!--  
 form tag 내의 <button>은 submit으로 수행한다.
-->