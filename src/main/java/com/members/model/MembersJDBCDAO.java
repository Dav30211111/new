package com.members.model;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MembersJDBCDAO implements MembersDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CGA102_2?serverTimezone=Asia/Taipei";

	String userid = "root";
	String passwd = "00000000";

	private static final String INSERT_STMT = 
			"insert into members(member_email, member_password, member_name, member_phone, member_address, member_pic, member_status) values(?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"select * from members order by member_id";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM members where member_id = ?";
	private static final String DELETE = 
			"DELETE FROM members where member_id = ?";
	private static final String UPDATE = 
			"UPDATE members set member_name=?, member_email=?, member_phone=?, member_password=?, member_address=? where member_id = ?";
	private static final String FIND_MEMBER_STMT = 
			"select * from CGA102_2.MEMBERS where MEMBER_EMAIL = ? and MEMBER_PASSWORD = ?";
	
	@Override
	public void insert(MemberVO memberVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberVO.getMember_email());
			pstmt.setString(2, memberVO.getMember_password());
			pstmt.setString(3, memberVO.getMember_name());
			pstmt.setString(4, memberVO.getMember_phone());
			pstmt.setString(5, memberVO.getMember_address());
			pstmt.setBytes(6, memberVO.getMember_pic());
			pstmt.setInt(7, memberVO.getMember_status());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
		}
	}

	@Override
	public void update(MemberVO memberVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getMember_name());
			pstmt.setString(2, memberVO.getMember_email());
			pstmt.setString(3, memberVO.getMember_phone());
			pstmt.setString(4, memberVO.getMember_password());
			pstmt.setString(5, memberVO.getMember_address());
			pstmt.setInt(6, memberVO.getMember_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Integer member_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, member_id);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
		}
	}

	@Override
	public MemberVO findByPrimaryKey(Integer empno) {
		
		MemberVO memberVO4 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 銋迂� Domain objects

				memberVO4 = new MemberVO();

				memberVO4.setMember_id(rs.getInt("Member_id"));
				memberVO4.setMember_email(rs.getString("Member_email"));
				memberVO4.setMember_password(rs.getString("member_password"));
				memberVO4.setMember_name(rs.getString("member_name"));
				memberVO4.setMember_phone(rs.getString("member_phone"));
				memberVO4.setMember_address(rs.getString("member_address"));
				memberVO4.setMember_status(rs.getInt("Member_status"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
		}
		return memberVO4;
	}
	
    public MemberVO findByAccountAndPassword(String account, String password) {
    	MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_MEMBER_STMT);
			pstmt.setString(1, account);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				memberVO = new MemberVO();

				memberVO.setMember_id(rs.getInt("Member_id"));
				memberVO.setMember_email(rs.getString("Member_email"));
				memberVO.setMember_name(rs.getString("member_name"));
				memberVO.setMember_phone(rs.getString("member_phone"));
				memberVO.setMember_address(rs.getString("member_address"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ss) {
				ss.printStackTrace();
			}
		}
		return memberVO;
	}
    
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO5 = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
//				 empVO 銋迂� Domain objects
				memberVO5 = new MemberVO();
				memberVO5.setMember_id(rs.getInt("Member_id"));
				memberVO5.setMember_name(rs.getString("Member_name"));
				memberVO5.setMember_email(rs.getString("Member_email"));
				memberVO5.setMember_password(rs.getString("Member_password"));
				memberVO5.setMember_phone(rs.getString("Member_phone"));
				memberVO5.setMember_address(rs.getString("Member_address"));
				memberVO5.setMember_status(rs.getInt("Member_status"));
				list.add(memberVO5); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;

	}

	public static void main(String[] args) {
		MembersJDBCDAO dao = new MembersJDBCDAO();

		// 新增
//		MemberVO memberVO1 = new MemberVO();
//		memberVO1.setMember_email("324a@gmail.com");
//		memberVO1.setMember_address("桃園市中壢區");
//		memberVO1.setMember_name("Jack");
//		memberVO1.setMember_password("abc1234567890");
//		memberVO1.setMember_pic(null);
//		memberVO1.setMember_phone("0900000000");
//		memberVO1.setMember_status(1);
//		dao.insert(memberVO1);

		// 修改

//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setMember_email("222ds2323ae@gmail.com");
//		memberVO2.setMember_address("桃園市平鎮區");
//		memberVO2.setMember_name("吳老師2");
//		memberVO2.setMember_password("parrrrrd000");
//		memberVO2.setMember_pic(null);
//		memberVO2.setMember_phone("0982248341");
//		memberVO2.setMember_status(1);
//		memberVO2.setMember_id(1);
//		dao.update(memberVO2);

//		dao.delete(4);

		// findPrimaryKey
//		MemberVO memberVO3 = dao.findByPrimaryKey(1);
//		System.out.println("Mmeber_id: " + memberVO3.getMember_id() + ",");
//		System.out.println("Member_email: " + memberVO3.getMember_email() + ",");
//		System.out.println("Member_password: " + memberVO3.getMember_password() + ",");
//		System.out.println("Member_name: " + memberVO3.getMember_name() + ",");
//		System.out.println("Member_phone: " + memberVO3.getMember_phone() + ",");
//		System.out.println("Member_address: " + memberVO3.getMember_address() + ",");
//		System.out.println("Member_status: " + memberVO3.getMember_status());
//		System.out.println("---------------------");
		
		// list��
//		List<MemberVO> listMembers = dao.getAll();
//		System.out.println(listMembers );
		
		MemberVO memberVO = dao.findByAccountAndPassword("update@gmail.com", "PA55W@ROD");
		System.out.println("Mmeber_id: " + memberVO.getMember_id() + ",");
		System.out.println("Member_email: " + memberVO.getMember_email() + ",");
		System.out.println("Member_name: " + memberVO.getMember_name() + ",");
		System.out.println("Member_phone: " + memberVO.getMember_phone() + ",");
		System.out.println("Member_address: " + memberVO.getMember_address() + ",");
		System.out.println("---------------------");
	}
}