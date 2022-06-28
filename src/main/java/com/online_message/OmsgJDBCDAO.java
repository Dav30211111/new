package com.online_message;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class OmsgJDBCDAO implements OmsgDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CGA102_2?serverTimezone=Asia/Taipei";
	// 記得修改database => CGA102
	String userid = "root";
	String passwd = "00000000";

	private static final String INSERT_STMT = "INSERT INTO online_message (msg_content, reply_content) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM online_message order by message_id";
	private static final String GET_ONE_STMT = "SELECT * FROM online_message where message_id = ?";
	private static final String DELETE = "DELETE FROM product_order_list where empno = ?";
	private static final String UPDATE = "UPDATE product_order_list set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=? where empno = ?";


	@Override
	public void insert(OmsgVO msgVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, msgVO.getMsg_content());
			pstmt.setString(2, msgVO.getReply_content());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(OmsgVO msgVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer msg_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OmsgVO findByPrimaryKey(Integer msg_id) {
		OmsgVO omsg = new OmsgVO();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, msg_id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				omsg.setMessage_id(rs.getInt("message_id"));
				omsg.setMsg_content(rs.getString("msg_content"));
				omsg.setReply_time(rs.getTimestamp("reply_time"));
				omsg.setReply_content(rs.getString("reply_content"));
				omsg.setMsg_time(rs.getTimestamp("msg_time"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return omsg;
	}

	@Override
	public List<OmsgVO> getAll() {
		
		List<OmsgVO> allOmsg = new ArrayList<OmsgVO>();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OmsgVO omsg = new OmsgVO();
				omsg.setMessage_id(rs.getInt("message_id"));
				omsg.setMsg_content(rs.getString("msg_content"));
				omsg.setReply_time(rs.getTimestamp("reply_time"));
				omsg.setReply_content(rs.getString("reply_content"));
				omsg.setMsg_time(rs.getTimestamp("msg_time"));
				
				allOmsg.add(omsg);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return allOmsg;
		
	}
	
	
	public static void main(String[] args) {
		OmsgJDBCDAO dao = new OmsgJDBCDAO();
		
		// insert
//		OmsgVO omsg = new OmsgVO();
//		omsg.setMsg_content("活動好多人，能不能辦一點比較少人的活動？");
//		omsg.setReply_content("顧客您好，您回家比較快喔");
//		
//		dao.insert(omsg);
		
		//update for what and how?
		// delete for what and how?
		
		// find one 
//		OmsgVO omsg = dao.findByPrimaryKey(4);
//		System.out.println(omsg);
		
		// find all 
		List<OmsgVO> msgList = dao.getAll();
		for(OmsgVO msg : msgList) {
			System.out.println(msg);
			System.out.println("");
		}
		
	}
}
