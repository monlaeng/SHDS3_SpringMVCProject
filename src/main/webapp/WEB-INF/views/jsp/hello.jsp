<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Hello!!!!
<h1>이름은 ${myname}</h1>
<h1>점수는 ${myscore}</h1>

<h2>Get 방식 요청(param 이용: 값 동일, field 존재, 존재하지 않음)</h2>
<form action="${pageContext.servletContext.contextPath}/sample/hello6.do">
	email : <input type="email" name="email" value="monlang@naver.com"><br>
	password : <input type="password" name="pwd" value="1234"><br>
	phone : <input type="text" name="phone" value="010-1111-1111" ><br>
	<input type="submit" value="서버전송(get)">
</form>

<hr>

<h2>Post 방식 요청</h2>
<form 
method="post"
action="${pageContext.servletContext.contextPath}/sample/hello6.do">
	email : <input type="email" name="email" value="monlang@naver.com"><br>
	password : <input type="password" name="pwd" value="1234"><br>
	phone : <input type="text" name="phone" value="010-1111-1111" ><br>
	<input type="submit" value="서버전송(get)">
</form>

</body>
</html>