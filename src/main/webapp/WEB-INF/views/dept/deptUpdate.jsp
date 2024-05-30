<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<h1>부서등록</h1>
<form action = "${path}/dept/deptDetail.do" method="post">
부서번호 : <input type = "number" name = "department_id" value="${dept.department_id}" readonly="readonly" ><br>
부서이름 : <input type = "text" name = "department_name" value="${dept.department_name}" readonly="readonly"><br>
매니져 : <input type="number" name="manager_id" value="${dept.manager_id}" readonly="readonly"> <br>
지역코드 : <input type = "number" name = "location_id" value="${dept.location_id}" readonly="readonly"><br>
<input type="submit" value="수정하러가기">
</form>

</body>
</html>