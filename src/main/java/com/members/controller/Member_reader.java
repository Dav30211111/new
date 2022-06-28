package com.members.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class Member_reader extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	       

	 Connection con;


//	    public Mebmer_reader() {
//	        super();
//	        // TODO Auto-generated constructor stub
//	    }

	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  doPost(req, res);
	 }
	 /**
	  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	  */
	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  // TODO Auto-generated method stub

	  
	  
	  res.setContentType("image/gif");
	  ServletOutputStream out = res.getOutputStream();
	  
	  try (/*Connection con = ds.getConnection();*/
	    PreparedStatement pstmt = con.prepareStatement("SELECT product_photo FROM product_pics WHERE product_photo_id = ? ");)
	  {
	   String id = req.getParameter("product_photo_id");
	   
	   pstmt.setInt(1, Integer.valueOf(id));
	   ResultSet rs = pstmt.executeQuery();

	   if (rs.next()) {
	    BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("product_photo"));

	    byte[] buf = new byte[in.available()];
//	    byte[] buf = new byte[4 * 1024]; // 4K buffer
	    int len;
	    while ((len = in.read(buf)) != -1) {
	     out.write(buf, 0, len);
	    }
	    
	    in.read(buf);
//	    out.write(buf);
//	    out.close();
	    in.close();
	    rs.close();
	   } 
	//   stmt.close();
	  } catch (Exception e) {
	//   InputStream in = getServletContext()
	   System.out.println(e);
	  	} 
	 }

	 public void init() throws ServletException {
	  //  §ï¥Îconnection pools
	  try {
	   Context ctx = new javax.naming.InitialContext();
	   DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB1");
	   con = ds.getConnection();
	  } catch (NamingException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } catch (SQLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  	}
	 }

	 public void destroy() {
	  try {
	   if (con != null) con.close();
	  } catch (SQLException e) {
	   System.out.println(e);
	  		}	
	 	}
	}