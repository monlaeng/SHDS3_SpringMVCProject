package org.firstzone.myapp.emp;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Controller -> Service ->DAO
//			 <-			<-
//Service : λΉμ¦??€ λ‘μ§? ????€.
@Service
public class EmpService {
		
	@Autowired
	EmpDAOMybatis empDAO;
	
	public EmpDTO loginChk(String email, String phone) {
		return empDAO.loginCheck(email, phone);
	}
	
	public int selectByEmail(String email) {
		return empDAO.selectByEmail(email);
	}
	
	//8. ?­?  (Delete)
	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}
	
	//7. ??  (Update)
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}
	
	//6. ?? ₯ (Insert)
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}
	
	//5.?€?? μ‘°κ±΄?Όλ‘? μ‘°ν?κΈ?
	//λΆ??λ³?(=), μ§μ±λ³?(=), ??¬?Όλ³? (>=), κΈμ¬(>=)
	public List<EmpDTO> selectByCondition(int deptid, String jobid, java.sql.Date hdate, int salary) {
		return empDAO.selectByCondition(deptid, jobid, hdate, salary);
	}
	
	
	//4. ?Ή? JOB?Έ μ§μ μ‘°ν
	public List<EmpDTO> selectByJOB(String jobid) {
		
		return empDAO.selectByJOB(jobid);
	}
	
	//3. ?Ή? λΆ?? μ§μ μ‘°ν
	public List<EmpDTO> selectByDepart(int depid) {
		
		return empDAO.selectByDepart(depid);
	}
	
	//2. ?Ή? μ§μ ??Έλ³΄κΈ°
	public EmpDTO selectById(int empid) {
		
		return empDAO.selectById(empid);
	}
	

	//1. μ§μ λͺ¨λ μ‘°ν
	public List<EmpDTO> selectAll() {
		
		return empDAO.selectAll();
	
	}
	
	//9. μ§μ λ²νΈλ₯? ?΄?©?΄? μ§μ? ?΄λ¦κ³Ό μ§μ±κ³? κΈμ¬λ₯? μ‘°ν??€.
	public Map<String, Object> empInfo(int empid) {

		return empDAO.empInfo(empid);
	}
	
	//10. μ§μλ²νΈκ°? ?€?΄?€λ©? μ§μ΄? λ³΄λ?€λ₯?  ?ΈμΆν?.
	public double callFunction (int empid) {
		return empDAO.callFunction(empid);
	}
	
	public List<HashMap<String, Object>> selectAllManager() {
		return empDAO.selectAllManager();
	}
	
	public List<JobDTO> selectAllJob() {
		return empDAO.selectAllJob();
	}
}
