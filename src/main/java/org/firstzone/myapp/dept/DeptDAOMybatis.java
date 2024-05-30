package org.firstzone.myapp.dept;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//DAO(Data Access?��?�� 비�??��?��로직?�� ?��?��?��?�� Object)
@Repository("deptmybatis")
public class DeptDAOMybatis implements DeptDAOInterface {
	
	//Ÿ���� ������ Injection
	//���� Ÿ���� �������̸� ����
	@Autowired
	SqlSession sqlSession;
	
	Logger logger = LoggerFactory.getLogger(DeptDAOMybatis.class);
	String namespace = "com.shinhan.dept.";

	
	public List<DeptDTO> selectAll() {
		System.out.println("========");
		logger.info("DeptDAOMybatis.....selectAll()");
		return sqlSession.selectList("com.shinhan.dept.selectAll");
		
	}
	
	public DeptDTO selectById(int deptid) {
		logger.info("DeptDAOMybatis.....selectById()");
		return sqlSession.selectOne(namespace + "selectById", deptid);
		
	}
	
	public List<DeptDTO> selectByName(String deptName) {
		logger.info("DeptDAOMybatis.....selectByName()");
		return sqlSession.selectList(namespace + "selectByName", deptName);
	}
	
	public int deptInsert(DeptDTO dept) {
		logger.info("DeptDAOMybatis.....deptInsert()");
		return sqlSession.insert(namespace + "deptInsert", dept);
	}
	
	public int deptUpdate(DeptDTO dept) {
		logger.info("DeptDAOMybatis.....deptUpdate()");

		return sqlSession.update(namespace + "deptUpdate", dept);		
	}	
	public int deptDelete(int deptid) {
		logger.info("DeptDAOMybatis.....deptDelete()");
		return sqlSession.delete(namespace + "deptDelete", deptid);		
	}

	@Override
	public List<DeptDTO> selectByCondition(int deptid, String deptname, int mid, int lid) {
		// TODO Auto-generated method stub
		return null;
	}

}