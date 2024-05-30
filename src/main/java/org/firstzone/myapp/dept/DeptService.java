package org.firstzone.myapp.dept;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//Controller->Service->DAO
//			<-		 <-
//Service : ë¹„ì??‹ˆ?Š¤ ë¡œì§?„ ?ˆ˜?–‰?•œ?‹¤. (DB?? ê´?? ¨?—†?Š” ë¡œì§ ?ˆ?„?•Œ ?œ ?š©)
@Service
public class DeptService {
	
	@Autowired
	@Qualifier("deptmybatis")
	DeptDAOInterface deptDAO;

	// ?´ë¦? ?‹¬?¼?„ ?˜?Š”?°, ê°™ìœ¼ë©? ?•Œ?•„ë³´ê¸° ?‰½?‹¤.
	

	//8. ?‚­? œ(Delete)
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);

	}

	// 7. ?ˆ˜? •(Update)
	public int deptUpdate(DeptDTO dept) {
		return deptDAO.deptUpdate(dept);
	}

	// 6. ?…? ¥(Insert)
	public int deptInsert(DeptDTO dept) {
		return deptDAO.deptInsert(dept);
	}

	// 5. ?‹¤?–‘?•œ ì¡°ê±´?œ¼ë¡? ì¡°íšŒ?•˜ê¸?
	public List<DeptDTO> selectByCondition(int deptid, String deptname, int mid, int lid) {
		return deptDAO.selectByCondition(deptid, deptname, mid, lid);
	}

//	// 4. ?Š¹? •JOB?¸ ì§ì›ì¡°íšŒ
//	public List<DeptDTO> selectByJob(String jobid) {
//		return empDAO.selectByJob(jobid);
//	}
//
//	// 3. ?Š¹? •ì§ì›?˜ ì§ì›ì¡°íšŒ
//	public List<DeptDTO> selectByDept(int deptid) {
//		return empDAO.selectByDept(deptid);
//	}

	// 2. ?Š¹? •ë¶??„œ?˜ ?ƒ?„¸ë³´ê¸°
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}

	// 1. ì§ì›ëª¨ë‘ì¡°íšŒ
	public List<DeptDTO> selectAll() {
		return deptDAO.selectAll();
	}
}