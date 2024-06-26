package org.firstzone.myapp.dept;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//Controller->Service->DAO
//			<-		 <-
//Service : λΉμ???€ λ‘μ§? ????€. (DB?? κ΄?? ¨?? λ‘μ§ ??? ? ?©)
@Service
public class DeptService {
	
	@Autowired
	@Qualifier("deptmybatis")
	DeptDAOInterface deptDAO;

	// ?΄λ¦? ?¬?Ό? ???°, κ°μΌλ©? ??λ³΄κΈ° ?½?€.
	

	//8. ?­? (Delete)
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);

	}

	// 7. ?? (Update)
	public int deptUpdate(DeptDTO dept) {
		return deptDAO.deptUpdate(dept);
	}

	// 6. ?? ₯(Insert)
	public int deptInsert(DeptDTO dept) {
		return deptDAO.deptInsert(dept);
	}

	// 5. ?€?? μ‘°κ±΄?Όλ‘? μ‘°ν?κΈ?
	public List<DeptDTO> selectByCondition(int deptid, String deptname, int mid, int lid) {
		return deptDAO.selectByCondition(deptid, deptname, mid, lid);
	}

//	// 4. ?Ή? JOB?Έ μ§μμ‘°ν
//	public List<DeptDTO> selectByJob(String jobid) {
//		return empDAO.selectByJob(jobid);
//	}
//
//	// 3. ?Ή? μ§μ? μ§μμ‘°ν
//	public List<DeptDTO> selectByDept(int deptid) {
//		return empDAO.selectByDept(deptid);
//	}

	// 2. ?Ή? λΆ??? ??Έλ³΄κΈ°
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}

	// 1. μ§μλͺ¨λμ‘°ν
	public List<DeptDTO> selectAll() {
		return deptDAO.selectAll();
	}
}