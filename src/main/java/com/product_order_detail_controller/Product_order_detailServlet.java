package com.product_order_detail_controller;

import com.members.model.*;
import com.product.model.ProductService;
import com.product.model.ProductVO;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.sql.Blob;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.product_order_detail.model.*;
import com.product_order_list.model.PolVO;

@WebServlet("/Product_order_detailServlet")
public class Product_order_detailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;

//	public Blob uploadPicFromDB(Part uploadPic) {
//		return null;
//	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		String action = req.getParameter("action");

		if ("getOne_Product_Detail".equals(action)) { // from select_page.jsp
			List<String> errorMsg = new LinkedList<String>();
			// 確保前台輸入錯誤，儲存錯誤提示於request scope及List中並顯示至前台頁面
			req.setAttribute("errorMsg", errorMsg);

			// 取的input text的name參數，並檢視其輸入是否為空值或無填寫
			String str = req.getParameter("product_id");
			if (str == null || str.trim().length() == 0) {
				errorMsg.add("請輸入正確訂單編號"); // true時儲存一個錯誤提示至list中
			}

			// 若errorMsg並非無值時，代表有錯誤發生需有提示
//			，故將其頁面forward至select_page.jsp，並顯示提示
			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_order_detail/homepage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			// 檢查是否輸入格式為數字，而非其他字元符號
			Integer product_id = null;
			try {
				product_id = Integer.parseInt(str);
			} catch (Exception e) {
				errorMsg.add("訂單編號格式不正確");

			}

			// 若errorMsg並非無值時，代表有錯誤發生需有提示
//			，故將其頁面forward至select_page.jsp，並顯示提示
			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_order_detail/homepage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			// 以上審查無錯誤，開始查詢資料...

//			呼叫工地主任
			Product_order_detailService posvc = new Product_order_detailService();
//			呼叫他的工人開始查詢
			Product_order_detailVO poVO = posvc.getOneOrderDetail(product_id);
			if (poVO.getProduct_order_id() == null) {
				errorMsg.add("查無資料");

			}

			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_order_detail/homepage.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

//			查詢成功，工作完成，轉交至另一隻jsp，並view出
			req.setAttribute("poVO", poVO); // 資料庫取出的poVO物件,存入req
			String url = "/back_end/product_order_detail/listOneProduct_order_detail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

//		==================================圖片==============================
		if ("upload".equals(action)) {

			String theURL = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
			ServletOutputStream out = res.getOutputStream();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(theURL, "root", "00000000");
				Statement stmt = con.createStatement();
				String test = req.getParameter("test");
				ResultSet rs = stmt.executeQuery("SELECT PIC FROM club WHERE ID = " + test);
				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("pic"));
					// or InputSream in = rs.getBinaryStream("image");
//						InputStream in = new BufferedInputStream(rs.getBinaryStream("image")); 

					byte[] buf = new byte[in.available()]; // 4K buffer

//						while ((len = in.read(buf)) != -1) {
//							out.write(buf, 0, len);
//						}
					in.read(buf);
					out.write(buf);

					in.close();
					out.close();
				} else {
					res.sendError(HttpServletResponse.SC_NOT_FOUND);
//					InputStream in = getServletContext().getResourceAsStream("/NoData/404.png");
//					byte[] b = new byte[in.available()];
//					in.read(b);
//					out.write(b);
//					in.close();

				}
				rs.close();
				stmt.close();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

//			==========針對以會員編號做查詢訂單===========
		if ("getOne_Product_DetailbyMember".equals(action)) {
			Integer memberId = Integer.parseInt(req.getParameter("memId"));
			Product_order_detailService posvc = new Product_order_detailService();
//			呼叫他的工人開始查詢
			List<Product_order_detailVO> orderList = posvc.getOrderListByMemberId(memberId);
			req.setAttribute("orderList", orderList);
			String url = "/back_end/product_order_detail/listProduct_order_detailbyMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

//		==========針對以會員編號做刪除訂單===========
		if ("delete_member_order".equals(action)) {
			Integer productId = Integer.valueOf(req.getParameter("product_order_id"));
			Product_order_detailService posvc = new Product_order_detailService();
			posvc.delete(productId);

			String url = "/back_end/product_order_detail/listProduct_order_detailbyMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		// ==============新增資料============
		if ("insert".equals(action)) {
			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			// 針對會員編號輸入格式

			Integer memberId = null;
			try {
				memberId = Integer.valueOf(req.getParameter("memberId").trim());
				MembersJDBCDAO members = new MembersJDBCDAO();
				if (members.findByPrimaryKey(memberId) == null) {
					errorMsg.add("查無此會員資料");
				} // 這裡是join
			} catch (NumberFormatException e) {
				memberId = 0;
				errorMsg.add("請填寫會員編號並為數字");
			}

			// 針對購買商品數量
			Integer productNums = null;
			try {
				productNums = Integer.valueOf(req.getParameter("productNums").trim());
				if (productNums == 0) {
					errorMsg.add("請填寫商品數量不可為零");
				}
			} catch (NumberFormatException e) {
				productNums = 0;
				errorMsg.add("請填寫商品數量並為數字");
			}

			// 針對付款方式 + 付款狀態
//			Integer productOrderId = Integer.parseInt(req.getParameter("product_order_id").trim());
//			java.sql.Date orderDate = java.sql.Date.valueOf(req.getParameter("orderDate").trim());
			Integer paymentMethod = Integer.parseInt(req.getParameter("paymentMethod").trim());
			Integer orderStatus = Integer.parseInt(req.getParameter("orderStatus").trim());
			Integer productId = Integer.parseInt(req.getParameter("productId").trim());
			// 開始新增資料
			Product_order_detailVO poVO = new Product_order_detailVO();

			poVO.setProduct_id(productId);
			poVO.setMember_id(memberId);
			poVO.setProduct_amount(productNums);
			poVO.setPayment_method(paymentMethod);
			poVO.setOrder_status(orderStatus);

			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/product_order_detail/addProduct_order.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			Product_order_detailService posvc = new Product_order_detailService();
			posvc.addProductOrderDetail(productId, memberId, productNums, paymentMethod, orderStatus);
			// 新增完畢顯示資料
			req.setAttribute("poVO", poVO);
			String url = "/back_end/product_order_detail/homepage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}
//				===============修改資料================
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			// 取的當前欲修改的那筆訂單資料
			Integer productOderId = Integer.valueOf(req.getParameter("product_order_id"));
			Product_order_detailVO poVo = new Product_order_detailVO();
			Product_order_detailService posvc = new Product_order_detailService();
			poVo = posvc.getOneOrderDetail(productOderId);
			req.setAttribute("poVo", poVo);
			// 將該筆資料轉交至update_product_order.jsp
			String url = "/back_end/product_order_detail/update_product_order.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
//			===================修改判斷========================
		if ("update".equals(action)) {
			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			// 判斷產品數量是否輸入正確
			Integer productNums = null;
			try {
				productNums = Integer.valueOf(req.getParameter("productNums"));
				if (productNums == null || productNums < 1) {
					errorMsg.add("請填寫正確數量");
				}
			} catch (NumberFormatException e) {
				errorMsg.add("請填寫數字");

			}
//			

			Integer productOrderId = Integer.parseInt(req.getParameter("product_order_id"));
			Integer memberId = Integer.parseInt(req.getParameter("memberId"));
			java.sql.Date orderDate = java.sql.Date.valueOf(req.getParameter("orderDate"));
			Integer paymentMethod = Integer.parseInt(req.getParameter("payMethod"));
			Integer orderStatus = Integer.parseInt(req.getParameter("orderStatus"));

			Product_order_detailVO poVo = new Product_order_detailVO();
			poVo.setProduct_order_id(productOrderId);
			poVo.setMember_id(memberId);
			poVo.setProduct_order_date(orderDate);
			poVo.setProduct_amount(productNums);
			poVo.setPayment_method(paymentMethod);
			poVo.setOrder_status(orderStatus);

			if (!errorMsg.isEmpty()) {
				req.setAttribute("poVo", poVo);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/product_order_detail/update_product_order.jsp");
				failureView.forward(req, res);
				return;
			}

			Product_order_detailService posvc = new Product_order_detailService();
			posvc.updateProductOrderDetail(orderStatus, productNums, paymentMethod, orderStatus);
			req.setAttribute("poVO", poVo);
			// 轉交
			String url = "/back_end/product_order_detail/listOneProduct_order_detail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

//			===============刪除資料================
		if ("delete".equals(action)) {
			Integer productId = Integer.valueOf(req.getParameter("product_order_id"));
			Product_order_detailService posvc = new Product_order_detailService();
			posvc.delete(productId);

			String url = "/back_end/product_order_detail/listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

//		===============針對前往付款按鈕=============
		if ("buy_now".equals(action)) {
			if("前往付款".equals(req.getParameter("payNow"))){
			Integer amounts = Integer.valueOf(req.getParameter("input-amounts"));
//			取得數量
			Integer productId = null;

			String id = req.getParameter("productId");
			//ID
			productId = Integer.valueOf(id);
//			HttpSession session = req.getSession();取的會員資料
//														這個1必須由session取得
//			podVO = podsvc.addProductOrderDetail(productId, 1, amounts, 1, 0);
//			List<PolVO> polVOlist = new ArrayList<PolVO>();
//			PolVO polVO = new PolVO();
//			polVO.setOrder_quantity(amounts);
//			polVO.setProduct_id(productId);
//			
//			polVOlist.add(polVO);
////			
//			podsvc.insertToPol(podVO, polVOlist);
			
			
			ProductService posvc = new ProductService();
			ProductVO poVo = posvc.getOneProduct(productId);
			req.setAttribute("amounts", amounts); //數量
			req.setAttribute("poVo", poVo);// 產品
			String url = "/front_end/purchasehomepage/Payment.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			}
		}
		
//		==================來自信用卡付款按鈕==============
		if("來自信用卡付款".equals(action)) {
			System.out.println("信用卡付款成功");
			
			Integer productId = Integer.valueOf(req.getParameter("productId"));
			System.out.println("商品編號: " + productId);
			Integer amounts = Integer.valueOf(req.getParameter("amounts"));
			System.out.println("數量: " + amounts);
			String name = req.getParameter("input-name");
			System.out.println("會員姓名:" + name);
			
			Product_order_detailVO podVO = new Product_order_detailVO();
			Product_order_detailService podsvc = new Product_order_detailService();
//			HttpSession session = req.getSession();取的會員資料
//															這個1必須由會員session取得
			podVO = podsvc.addProductOrderDetail(productId, 1, amounts, 0, 0); // 一定是信用卡+已付款 0, 0的訂單
			
			List<PolVO> polVOlist = new ArrayList<PolVO>();
			PolVO polVO = new PolVO(); // 訂單明細
			polVO.setOrder_quantity(amounts);
			polVO.setProduct_id(productId);

			polVOlist.add(polVO);

			podsvc.insertToPol(podVO, polVOlist);
			String url = "/front_end/purchasehomepage/PurchaseHomePage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
//				=============購物車===========
		if ("buy_now".equals(action)) {
			if("加入購物車".equals(req.getParameter("goCar"))){
				
				System.out.println("近來購物車頁面了");
				
				
			}
			
		}
		
		
		

	}

}
