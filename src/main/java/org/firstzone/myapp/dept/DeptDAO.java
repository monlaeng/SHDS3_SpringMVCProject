package org.firstzone.myapp.dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.util.DBUtil;

//DAO(Data Access?•˜?Š” ë¹„ì??‹ˆ?Š¤ë¡œì§?„ ?‘?„±?•˜?Š” Object)
@Repository
public class DeptDAO implements DeptDAOInterface {
	
	//Å¸ÀÔÀÌ °°À¸¸é Injection
	//°°Àº Å¸ÀÔÀÌ ¿©·¯°³ÀÌ¸é ¿À·ù
	@Autowired
	//°°Àº Å¸ÀÔÀÌ ¿©·¯°³ ÀÖÀ¸¸é ÀÌ¸§À» ºñ±³ÇØ¼­ °°Àº ÀÌ¸§ÀÇ BeanÀ» Injection
	@Qualifier("dataSource")
	DataSource ds;
	
	Connection conn;
	Statement st;
	PreparedStatement pst; //Statementë¥? ?ƒ?†ë°›ìŒ, ë°”ì¸?”©ë³??ˆ˜ ì§??›
	ResultSet rs;
	
	
	//8. ?‚­? œ(Delete)
		public int deptDelete(int deptid) {
			int result = 0;
			String sql = "delete from departments"
					+ " where DEPARTMENT_ID=?";
			try {
				conn = ds.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setInt(1, deptid);
				result = pst.executeUpdate(); //DMLë¬¸ì¥?? executeUpdate, Selectë¬¸ì? executeQuery //ê±´ìˆ˜, ëª»í•˜ë©? 0, ?—?Ÿ¬?‚˜ë©? -1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbDisConnect(conn, pst, rs);
			}
			return result;
		}
		
	
	//7. ?ˆ˜? •(Update)
	public int deptUpdate(DeptDTO dept) {
		int result = 0;
		String sql = "update departments"
				+ " set DEPARTMENT_NAME=?,"
				+ " MANAGER_ID=?,"
				+ " LOCATION_ID=?"
				+ " where DEPARTMENT_ID=?";
		try {
			conn = ds.getConnection();			
			pst = conn.prepareStatement(sql);
			pst.setString(1, dept.getDepartment_name());
			pst.setInt(2, dept.getManager_id());
			pst.setInt(3, dept.getLocation_id());
			pst.setInt(4, dept.getDepartment_id());
			result = pst.executeUpdate(); //DMLë¬¸ì¥?? executeUpdate, Selectë¬¸ì? executeQuery //ê±´ìˆ˜, ëª»í•˜ë©? 0, ?—?Ÿ¬?‚˜ë©? -1
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
		return result;
	}
	
	
	//6. ?…? ¥(Insert)
	public int deptInsert(DeptDTO dept) {
		int result = 0;
		String sql = "insert into departments values(?,?,?,?)";
		try {
			conn = ds.getConnection();			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, dept.getDepartment_id());
			pst.setString(2, dept.getDepartment_name());
			pst.setInt(3, dept.getManager_id());
			pst.setInt(4, dept.getLocation_id());
			result = pst.executeUpdate(); //DMLë¬¸ì¥?? executeUpdate, Selectë¬¸ì? executeQuery //ê±´ìˆ˜, ëª»í•˜ë©? 0, ?—?Ÿ¬?‚˜ë©? -1
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
		return result;
	}
	
	
	//5. ?‹¤?–‘?•œ ì¡°ê±´?œ¼ë¡? ì¡°íšŒ?•˜ê¸?
	//ë¶??„œë³?(=), ì§ì±…ë³?(=), ?…?‚¬?¼ë³?(>=), ê¸‰ì—¬(>=)
	public List<DeptDTO> selectByCondition(int deptid, String deptname, int mid, int lid) {
		List<DeptDTO> deptlist = new ArrayList<DeptDTO>();
		String sql = "select * "
				+ " from departments "
				+ " where DEPARTMENT_ID= ?"
				+ " and DEPARTMENT_NAME = ?"
				+ " and MANAGER_ID = ?"
				+ " and LOCATION_ID = ?";
		
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			pst.setString(2, deptname);
			pst.setInt(3, mid);
			pst.setInt(4, lid);
			rs = pst.executeQuery(); //sql?„£?œ¼ë©? ?•ˆ?¨
			
			while(rs.next()) {
				DeptDTO dept = makeDept(rs);
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {			
			DBUtil.dbDisConnect(conn, pst, rs); //??‹-ë¶?ëª¨ë‹ˆê¹? pstê°??Š¥
		}
		return deptlist;
	}
	
	
	//2. ?Š¹? •ë¶??„œ?˜ ?ƒ?„¸ë³´ê¸°
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = null;
		String sql = "select * from departments where department_id = " + deptid;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				dept = makeDept(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dept;
	}
	
	//1. ë¶??„œëª¨ë‘ì¡°íšŒ
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = new ArrayList<DeptDTO>();
		String sql = "select * from departments";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				DeptDTO dept = makeDept(rs);
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {			
			DBUtil.dbDisConnect(conn, st, rs);
		}
		return deptlist;
	}
	
	
	//?Š¹? •ì§ì› 1ëª? ì¡°íšŒ
	private DeptDTO makeDept(ResultSet rs) throws SQLException {
		DeptDTO dept = new DeptDTO();
		//ì´? 4ê°?
		dept.setDepartment_id(rs.getInt("department_id"));
		dept.setDepartment_name(rs.getString("department_name"));
		dept.setLocation_id(rs.getInt("location_id"));
		dept.setManager_id(rs.getInt("manager_id"));
		
		return dept;
	}
}