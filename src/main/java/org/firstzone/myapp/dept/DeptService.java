package org.firstzone.myapp.dept;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//Controller->Service->DAO
//			<-		 <-
//Service : 비�??��?�� 로직?�� ?��?��?��?��. (DB?? �??��?��?�� 로직 ?��?��?�� ?��?��)
@Service
public class DeptService {
	
	@Autowired
	@Qualifier("deptmybatis")
	DeptDAOInterface deptDAO;

	// ?���? ?��?��?�� ?��?��?��, 같으�? ?��?��보기 ?��?��.
	

	//8. ?��?��(Delete)
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);

	}

	// 7. ?��?��(Update)
	public int deptUpdate(DeptDTO dept) {
		return deptDAO.deptUpdate(dept);
	}

	// 6. ?��?��(Insert)
	public int deptInsert(DeptDTO dept) {
		return deptDAO.deptInsert(dept);
	}

	// 5. ?��?��?�� 조건?���? 조회?���?
	public List<DeptDTO> selectByCondition(int deptid, String deptname, int mid, int lid) {
		return deptDAO.selectByCondition(deptid, deptname, mid, lid);
	}

//	// 4. ?��?��JOB?�� 직원조회
//	public List<DeptDTO> selectByJob(String jobid) {
//		return empDAO.selectByJob(jobid);
//	}
//
//	// 3. ?��?��직원?�� 직원조회
//	public List<DeptDTO> selectByDept(int deptid) {
//		return empDAO.selectByDept(deptid);
//	}

	// 2. ?��?���??��?�� ?��?��보기
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}

	// 1. 직원모두조회
	public List<DeptDTO> selectAll() {
		return deptDAO.selectAll();
	}
}