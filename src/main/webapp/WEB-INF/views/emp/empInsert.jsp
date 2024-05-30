
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <c:set var="path" value="${pageContext.servletContext.contextPath}"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script>
  $(function() {
	  emp = "";
	  
	  $("form").on("submit", call);
	  
	  $("#btnDupCheck").on("click", f_dupCheck);
  });
  
  function f_dupCheck() {
	  var empid = $("#employee_id").val();
	  if(empid==null) {
		  alert("직원번호를 입력하세요.");
		  return;
	  }
	  $.ajax({
		  url:"${path}/emp/empIdCheck.do",
	  	  data:{"empid" : empid},
	  	  type:"get",
	  	  success:function(responseData) {
	  		  var message = "";
	  		  if(responseData=="0") {
	  			  message = "사용가능";
	  		  } else {
	  			  message = "사용불가능";
	  			  $("#employee_id").val("");
	  			  document.querySelector("#employee_id").focus();
	  		  }
	  		  $("#resultMessage").val(message);
	  	  },
	  	  error:function(data) {
	  		  alert(data);
	  	  }
	  })
  }
  
  
  function call(event) {
	  var salary = $("#salary").val();
	  if(salary == "" || salary <= 0) {
		  alert("salary 값은 0보다 큰 값이어야 한다.");
		  event.preventDefault();	//default 이벤트 취소()
		  document.querySelector("#salary").focus();
	  }
  }
  </script>
</head>
<body>

<div class="container mt-3">
<a href="${path}/emp/empAll.do">직원목록</a>
  <h2>신규직원 등록</h2>
  <form action="${path}/emp/empInsert.do" method="post">
    <div class="mb-3 mt-3">
      <label for="employee_id">직원번호 :</label>
      <input type="number" class="form-control" id="employee_id" placeholder="employee_id" name="employee_id" required="required">
    	<input type="button" value="중복체크" id="btnDupCheck">
    	<input type="text" value="ID 입력 후 중복체크" id="resultMessage">
    </div>
    <div class="mb-3 mt-3">
      <label for="first_name">직원이름(first) :</label>
      <input type="text" class="form-control" id="first_name" placeholder="first_name" name="first_name">
    </div>
    <div class="mb-3 mt-3">
      <label for="last_name">직원이름(last) :</label>
      <input type="text" class="form-control" id="last_name" placeholder="last_name" name="last_name">
    </div>
    <div class="mb-3 mt-3">
      <label for="email">email :</label>
      <input type="email" class="form-control" id="email" placeholder="email" name="email">
    </div>
    <div class="mb-3 mt-3">
      <label for="phone_number">phone_number :</label>
      <input type="text" class="form-control" id="phone_number" placeholder="phone_number" name="phone_number">
    </div>
    <div class="mb-3 mt-3">
      <label for="hire_date">hire_date :</label>
      <input type="Date" class="form-control" id="hire_date" placeholder="hire_date" name="hire_date">
    </div>
    
    
    <%-- ScriptLet문법보다는 EL(반복문 없음), JSTL문법을 사용하는 것이 좋다 --%>
    <div class="mb-3 mt-3">
      <label for="job_id">job_id :</label>
      <select name="job_id">
      	<c:forEach items="${joblist}" var="job">
      		<option value="${job.job_id}">${job.job_title}</option>
      	</c:forEach>
      </select>
    </div>
    
    
    <div class="mb-3 mt-3">
      <label for="salary">salary :</label>
      <input type="number" class="form-control" id="salary" placeholder="salary" name="salary" required="required">
    </div>
    <div class="mb-3 mt-3">
      <label for="commission_pct">commission_pct :</label>
      <input type="text" class="form-control" id="commission_pct" placeholder="commission_pct" name="commission_pct">
    </div>
    <div class="mb-3 mt-3">
      <label for="manager_id">manager_id :</label>
      <select name = "manager_id">
      	<option value="0">매니저 없음</option>
      	<c:forEach items="${mlist}" var = "manager">
      		<option value="${manager.employee_id}">${manager.fullname }</option>
      	</c:forEach>
      </select>
    </div>
    <div class="mb-3 mt-3">
      <label for="department_id">department_id :</label>
      <select name ="department_id">
      	<option value="0">부서 없음</option>
    	<c:forEach items="${deptlist}" var="dept">
    		<option value="${dept.department_id}">${dept.department_name}</option>
    	</c:forEach>
      </select>
    </div>
    
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>
<div id="here"></div>


</body>
</html>
