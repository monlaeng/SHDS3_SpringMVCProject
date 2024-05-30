package org.firstzone.myapp.emp;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Controller -> Service ->DAO
//			 <-			<-
//Service : 비즈?��?�� 로직?�� ?��?��?��?��.
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
	
	//8. ?��?�� (Delete)
	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}
	
	//7. ?��?�� (Update)
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}
	
	//6. ?��?�� (Insert)
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}
	
	//5.?��?��?�� 조건?���? 조회?���?
	//�??���?(=), 직책�?(=), ?��?��?���? (>=), 급여(>=)
	public List<EmpDTO> selectByCondition(int deptid, String jobid, java.sql.Date hdate, int salary) {
		return empDAO.selectByCondition(deptid, jobid, hdate, salary);
	}
	
	
	//4. ?��?��JOB?�� 직원 조회
	public List<EmpDTO> selectByJOB(String jobid) {
		
		return empDAO.selectByJOB(jobid);
	}
	
	//3. ?��?���??�� 직원 조회
	public List<EmpDTO> selectByDepart(int depid) {
		
		return empDAO.selectByDepart(depid);
	}
	
	//2. ?��?��직원 ?��?��보기
	public EmpDTO selectById(int empid) {
		
		return empDAO.selectById(empid);
	}
	

	//1. 직원 모두 조회
	public List<EmpDTO> selectAll() {
		
		return empDAO.selectAll();
	
	}
	
	//9. 직원 번호�? ?��?��?��?�� 직원?�� ?��름과 직책�? 급여�? 조회?��?��.
	public Map<String, Object> empInfo(int empid) {

		return empDAO.empInfo(empid);
	}
	
	//10. 직원번호�? ?��?��?���? 직운?�� 보너?���?  ?��출한?��.
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
