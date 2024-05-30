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
<h1>직원 상세보기(수정가능)</h1>
	<form action="${path}/emp/empDetail.do" method="post">
    <div class="mb-3 mt-3">
      <label for="employee_id">직원번호 :</label>
      <input type="number" class="form-control" id="employee_id" placeholder="employee_id" name="employee_id" value="${empInfo.employee_id}">
    </div>
    <div class="mb-3 mt-3">
      <label for="first_name">직원이름(first) :</label>
      <input type="text" class="form-control" id="first_name" placeholder="first_name" name="first_name" value="${empInfo.first_name}">
    </div>
    <div class="mb-3 mt-3">
      <label for="last_name">직원이름(last) :</label>
      <input type="text" class="form-control" id="last_name" placeholder="last_name" name="last_name" value = "${empInfo.last_name}">
    </div>
    <div class="mb-3 mt-3">
      <label for="email">email :</label>
      <input type="email" class="form-control" id="email" placeholder="email" name="email" value = "${empInfo.email}">
    </div>
    <div class="mb-3 mt-3">
      <label for="phone_number">phone_number :</label>
      <input type="text" class="form-control" id="phone_number" placeholder="phone_number" name="phone_number" value = "${empInfo.phone_number}">
    </div>
    <div class="mb-3 mt-3">
      <label for="hire_date">hire_date :</label>
      <input type="Date" class="form-control" id="hire_date" placeholder="hire_date" name="hire_date" value = "${empInfo.hire_date}">
    </div>
    
    
    <%-- ScriptLet문법보다는 EL(반복문 없음), JSTL문법을 사용하는 것이 좋다 --%>
    <div class="mb-3 mt-3">
      <label for="job_id">job_id :</label>
      <select name="job_id">
      	<c:forEach items="${joblist}" var="job">
      		<option value="${job.job_id}" ${empInfo.job_id==job.job_id ? "selected":"" }>${job.job_id} / ${job.job_title}</option>
      	</c:forEach>
      </select>
    </div>
    
    
    <div class="mb-3 mt-3">
      <label for="salary">salary :</label>
      <input type="number" class="form-control" id="salary" placeholder="salary" name="salary" value = "${empInfo.salary}">
    </div>
    <div class="mb-3 mt-3">
      <label for="commission_pct">commission_pct :</label>
      <input type="text" class="form-control" id="commission_pct" placeholder="commission_pct" name="commission_pct" value = "${empInfo.commission_pct}">
    </div>
    <div class="mb-3 mt-3">
      <label for="manager_id">manager_id :</label>
      <select name = "manager_id">
      	<option value="0">매니저 없음</option>
      	<c:forEach items="${mlist}" var = "manager">
      		<option value="${manager.employee_id}" ${empInfo.manager_id==manager.employee_id? "selected":""}>${manager.employee_id} / ${manager.fullname }</option>
      	</c:forEach>
      </select>
    </div>
    <div class="mb-3 mt-3">
      <label for="department_id">department_id :</label>l
      <select name ="department_id">
      	<option value="0">부서 없음</option>
    	<c:forEach items="${deptlist}" var="dept">
    		<option value="${dept.department_id}" ${empInfo.department_id==dept.department_id ? "selected":"" }>${dept.department_name}</option>
    	</c:forEach>
      </select>
    </div>
    <input type="submit" class="mb-3 mt-3" value="수정하기">
    
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</body>
</html>