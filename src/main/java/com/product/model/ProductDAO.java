package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements ProductDAO_interface {
	
	// 建議連線池給jsp
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new javax.naming.InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB1");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = /*"jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";*/
	               "jdbc:mysql://localhost:3306/"
	               + "CGA102_2?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	String userid = "root";
//	String passwd = "password";
	String passwd = "00000000";

	private static final String INSERT_STMT =
			"INSERT INTO product (product_category_id, product_describtion, product_price, product_name, product_quantity, product_status) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT product_id, product_category_id, product_describtion, product_price, product_name, product_quantity, product_status "
			+ "FROM product order by product_id";
	private static final String GET_ONE_STMT =
			"SELECT product_id, product_category_id, product_describtion, product_price, product_name, product_quantity, product_status "
			+ "FROM product where product_id = ?";
	private static final String DELETE =
			"DELETE FROM product where product_id = ?";
	private static final String UPDATE =
			"UPDATE product set product_category_id=?, product_describtion=?, product_price=?, product_name=?, product_quantity=?, product_status=? "
			+ "where product_id = ?";
	private static final String GET_Product_ByProduct_name_STMT =
			"SELECT product_id, product_category_id, product_describtion, product_price, product_name, product_quantity, product_status"
			+ "FROM product where product_name = ? order by product_id";
//	private static final String GET_Product_ByProduct_category_id_STMT =
//	"SELECT product_id, product_category_id, product_describtion, product_price, product_name, product_quantity, product_status"
//	+ "FROM product where product_category_id = ? order by product_id";

	@Override
	public void insert(ProductVO productVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
//			pstmt.setInt(1, product_category_id);
//			pstmt.setString(2, product_describtion);
//			pstmt.setInt(3, product_price);
//			pstmt.setString(4, product_name);
//			pstmt.setInt(5, product_quantity);
//			pstmt.setBoolean(6, product_status);

			pstmt.setInt(1, productVO.getProduct_category_id());
			pstmt.setString(2, productVO.getProduct_describtion());
			pstmt.setInt(3, productVO.getProduct_price());
			pstmt.setString(4, productVO.getProduct_name());
			pstmt.setInt(5, productVO.getProduct_quantity());
			pstmt.setBoolean(6, productVO.getProduct_status());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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
//	public ProductVO update() {
	public void update(ProductVO productVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			con = ds.getConnection();

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
//			pstmt.setInt(1, product_category_id);
//			pstmt.setString(2, product_describtion);
//			pstmt.setInt(3, product_price);
//			pstmt.setString(4, product_name);
//			pstmt.setInt(5, product_quantity);
//			pstmt.setBoolean(6, product_status);
//			pstmt.setInt(7, product_id);

			pstmt.setInt(1, productVO.getProduct_category_id());
			pstmt.setString(2, productVO.getProduct_describtion());
			pstmt.setInt(3, productVO.getProduct_price());
			pstmt.setString(4, productVO.getProduct_name());
			pstmt.setInt(5, productVO.getProduct_quantity());
			pstmt.setBoolean(6, productVO.getProduct_status());
			pstmt.setInt(7, productVO.getProduct_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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

	
	// �n�A�վ�delete �n�s�ʬ������
	@Override
	public void delete(Integer product_id) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			con = ds.getConnection();

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, product_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ProductVO findByPrimarykey(Integer product_id) {
		// TODO Auto-generated method stub

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			con = ds.getConnection();

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, product_id);

			rs = pstmt.executeQuery();

//			"UPDATE product set product_category_id=?, product_describtion=?, product_price=?, product_name=?, product_quantity=?, product_status=? "
//			+ "where product_id = ?";
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_category_id(rs.getInt("product_category_id"));
				productVO.setProduct_describtion(rs.getString("product_describtion"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_quantity(rs.getInt("product_quantity"));
				productVO.setProduct_status(rs.getBoolean("product_status"));
				productVO.setProduct_id(rs.getInt("product_id"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		// TODO Auto-generated method stub
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			con = ds.getConnection();

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				productVO = new ProductVO();
				productVO.setProduct_category_id(rs.getInt("product_category_id"));
				productVO.setProduct_describtion(rs.getString("product_describtion"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_quantity(rs.getInt("product_quantity"));
				productVO.setProduct_status(rs.getBoolean("product_status"));
				productVO.setProduct_id(rs.getInt("product_id"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	@Override
	public ProductVO findByProductName(String product_name) {
		// TODO Auto-generated method stub
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Product_ByProduct_name_STMT);

			pstmt.setString(1, product_name);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProduct_category_id(rs.getInt("product_category_id"));
				productVO.setProduct_describtion(rs.getString("product_describtion"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_quantity(rs.getInt("product_quantity"));
				productVO.setProduct_status(rs.getBoolean("product_status"));
				productVO.setProduct_id(rs.getInt("product_id"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		
		return null;
	}

	

	public static void main(String[] args) {

		ProductDAO dao = new ProductDAO();

		// 新增
//		"INSERT INTO product (product_category_id, product_describtion, product_price, product_name, product_quantity, product_status) "
//		+ "VALUES (?, ?, ?, ?, ?, ?)";
//		ProductVO productVO = new ProductVO();
//		productVO.setProduct_category_id(new Integer(2));
//		productVO.setProduct_describtion("點心一直都是送禮自用兩相宜的伴手聖品，伴手禮會因應節慶、和本身的食材而有不同的情境需求，讓我們一一細數、回味這些伴手禮帶來的美好滋味。");
//		productVO.setProduct_price(new Integer(199));
//		productVO.setProduct_name("紀念吊飾");
//		productVO.setProduct_quantity(new Integer(25));
//		productVO.setProduct_status(new Boolean("true"));
//		dao.insert(productVO);

		// 修改
//		"UPDATE product set product_category_id=?, product_describtion=?, product_price=?, product_name=?, product_quantity=?, product_status=? "
//		+ "where product_id = ?";
//		ProductVO productVO = new ProductVO();
//		productVO.setProduct_id(1);
//		productVO.setProduct_category_id(new Integer(1));
//		productVO.setProduct_describtion("�馡�i�f�����W�ȲզX�]");
//		productVO.setProduct_price(new Integer(299));
//		productVO.setProduct_name("���֯����]");
//		productVO.setProduct_quantity(new Integer(150));
//		productVO.setProduct_status(new Boolean("true"));
//		dao.update(productVO);

		// 刪除
//		dao.delete(2);

//		 找PK
//		ProductVO productVO = dao.findByPrimarykey(4);
//		System.out.println("Product_id: " + productVO.getProduct_id() + ",");
//		System.out.print("Product_category_id: " + productVO.getProduct_category_id() + ",");
//		System.out.print("Product_describtion: " + productVO.getProduct_describtion() + ",");
//		System.out.print("Product_price: " + productVO.getProduct_price() + ",");
//		System.out.print("Product_name: " + productVO.getProduct_name() + ",");
//		System.out.print("Product_quantity: " + productVO.getProduct_quantity() + ",");
//		System.out.print("Product_status: " + productVO.getProduct_status());
//		System.out.println();
//		System.out.println("---------------------");

		// 全部搜尋
		List<ProductVO> list = dao.getAll();
		for (ProductVO aProduct : list) {
			System.out.print(aProduct.getProduct_id() + ",");
			System.out.print(aProduct.getProduct_category_id() + ",");
			System.out.print(aProduct.getProduct_describtion() + ",");
			System.out.print(aProduct.getProduct_price() + ",");
			System.out.print(aProduct.getProduct_name() + ",");
			System.out.print(aProduct.getProduct_quantity() + ",");
			System.out.print(aProduct.getProduct_status());
			System.out.println();			
		}
		
	}
	
	
}
