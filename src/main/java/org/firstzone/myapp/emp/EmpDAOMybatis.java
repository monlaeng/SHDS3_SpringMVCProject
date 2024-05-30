package org.firstzone.myapp.emp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//DAO(Data Access?��?�� 비�??��?�� 로직?�� 처리?��?�� Object)
@Repository
public class EmpDAOMybatis {

	@Autowired
	SqlSession sqlSession;

	Logger logger = LoggerFactory.getLogger(EmpDAOMybatis.class);
	String namespace = "com.shinhan.emp.";

	public EmpDTO loginCheck(String email, String phone) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("phone", phone);
		return sqlSession.selectOne(namespace+"loginCheck", map);
	}

	public int empDelete(int empid) {
		int result = sqlSession.delete(namespace + "empDelete", empid);
		logger.info(result + "건 삭제됨");
		return result;
	}

	public int empUpdate(EmpDTO emp) {
		int result = sqlSession.update(namespace + "empUpdate", emp);
		logger.info(result + "건 update됨");
		return result;
	}

	public int empInsert(EmpDTO emp) {
		int result = sqlSession.insert(namespace + "empInsert", emp);
		logger.info(result + "건 insert됨");
		return result;
	}


	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary) {
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("deptid", deptid);
		conditionMap.put("jobid", jobid);
		conditionMap.put("hdate", hdate);
		conditionMap.put("salary", salary);
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByCondition", conditionMap);
		logger.info(emplist.toString());
		logger.info(emplist.size() + "건 조회됨");
		
		return emplist;	
	}

	

	public List<EmpDTO> selectByJOB(String jobid) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByJOB", "%" + jobid + "%");
		logger.info(emplist.toString());
		logger.info(emplist.size() + "건 job 조건 조회됨");
		
		return emplist;	
	}
	


	public List<EmpDTO> selectByDepart(int depid) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByDepart", depid);
		logger.info(emplist.toString());
		logger.info(emplist.size() + "건 depid 조건 조회됨");
		
		return emplist;			
	}

	public EmpDTO selectById(int empid) {
		EmpDTO emp = sqlSession.selectOne(namespace + "selectById", empid);
		logger.info(emp!=null?emp.toString():"data없음");
		return emp;
	}

	public int selectByEmail(String email) {
		Integer emp = sqlSession.selectOne(namespace + "selectByEmail", email);
		logger.info(emp.toString());
		return emp;
	}

	public List<EmpDTO> selectAll() {
		List<EmpDTO> emplist = sqlSession.selectList("com.shinhan.emp.selectAll");
		logger.info(emplist.toString());
		logger.info(emplist.size() + "건 조회됨");
		return emplist;
		
	}

	public List<JobDTO> selectAllJob() {
		List<JobDTO> jlist = sqlSession.selectList("com.shinhan.emp.selectAllJob");
		logger.info(jlist.toString());
		logger.info(jlist.size() + "건 AllJob 조회됨");
		return jlist;	
	}

	public List<HashMap<String, Object>> selectAllManager() {
		List<HashMap<String, Object>> mlist = sqlSession.selectList("com.shinhan.emp.selectAllManager");
		logger.info(mlist.toString());
		logger.info(mlist.size() + "건 Allmanager 조회됨");
		return mlist;	

	}

	
	public Map<String, Object> empInfo(int empid) {
		return null;
		
	}

	public double callFunction(int empid) {
		return empid;
		
	}

}
