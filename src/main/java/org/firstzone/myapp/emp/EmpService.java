package org.firstzone.myapp.emp;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Controller -> Service ->DAO
//			 <-			<-
//Service : ë¹„ì¦ˆ?‹ˆ?Š¤ ë¡œì§?„ ?ˆ˜?–‰?•œ?‹¤.
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
	
	//8. ?‚­? œ (Delete)
	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}
	
	//7. ?ˆ˜? • (Update)
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}
	
	//6. ?…? ¥ (Insert)
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}
	
	//5.?‹¤?–‘?•œ ì¡°ê±´?œ¼ë¡? ì¡°íšŒ?•˜ê¸?
	//ë¶??„œë³?(=), ì§ì±…ë³?(=), ?…?‚¬?¼ë³? (>=), ê¸‰ì—¬(>=)
	public List<EmpDTO> selectByCondition(int deptid, String jobid, java.sql.Date hdate, int salary) {
		return empDAO.selectByCondition(deptid, jobid, hdate, salary);
	}
	
	
	//4. ?Š¹? •JOB?¸ ì§ì› ì¡°íšŒ
	public List<EmpDTO> selectByJOB(String jobid) {
		
		return empDAO.selectByJOB(jobid);
	}
	
	//3. ?Š¹? •ë¶??„œ ì§ì› ì¡°íšŒ
	public List<EmpDTO> selectByDepart(int depid) {
		
		return empDAO.selectByDepart(depid);
	}
	
	//2. ?Š¹? •ì§ì› ?ƒ?„¸ë³´ê¸°
	public EmpDTO selectById(int empid) {
		
		return empDAO.selectById(empid);
	}
	

	//1. ì§ì› ëª¨ë‘ ì¡°íšŒ
	public List<EmpDTO> selectAll() {
		
		return empDAO.selectAll();
	
	}
	
	//9. ì§ì› ë²ˆí˜¸ë¥? ?´?š©?•´?„œ ì§ì›?˜ ?´ë¦„ê³¼ ì§ì±…ê³? ê¸‰ì—¬ë¥? ì¡°íšŒ?•œ?‹¤.
	public Map<String, Object> empInfo(int empid) {

		return empDAO.empInfo(empid);
	}
	
	//10. ì§ì›ë²ˆí˜¸ê°? ?“¤?–´?˜¤ë©? ì§ìš´?˜ ë³´ë„ˆ?Š¤ë¥?  ?˜¸ì¶œí•œ?””.
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
