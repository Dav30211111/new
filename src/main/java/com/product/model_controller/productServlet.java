package com.product.model_controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.product.model.ProductService;
import com.product.model.ProductVO;



@WebServlet("/Product")
public class productServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductService ProdSvc = new ProductService();

       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
////		String action = req.getParameter("action");
//		
////		String str = req.getParameter("product_id");
//		String str = "3";
//		Integer product_id = null;
//		
//		product_id = Integer.valueOf(str);
//		
//		ProductVO productVO = ProdSvc.getOneProduct(product_id);
////		ProductVO productVO = ProdSvc.getOneProduct(3);
//		
////		ProductVO productVO = dao.findByPrimarykey(3);
//		System.out.println("Product_id: " + productVO.getProduct_id() + ",");
//		System.out.print("Product_category_id: " + productVO.getProduct_category_id() + ",");
//		System.out.print("Product_describtion: " + productVO.getProduct_describtion() + ",");
//		System.out.print("Product_price: " + productVO.getProduct_price() + ",");
//		System.out.print("Product_name: " + productVO.getProduct_name() + ",");
//		System.out.print("Product_quantity: " + productVO.getProduct_quantity() + ",");
//		System.out.print("Product_status: " + productVO.getProduct_status());
//		System.out.println();
//		System.out.println("---------------------");
		
		
		
		//
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("product_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("product_id","�п�J�ӫ~�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					// �n�A�վ�
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer product_id = null;
				try {
					product_id = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("product_id","�ӫ~�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					// �n�A�վ�
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ProductService prodSvc = new ProductService();
				ProductVO productVO = prodSvc.getOneProduct(product_id);
				if (productVO == null) {
					errorMsgs.put("product_id","�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					// �n�A�վ�
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("prodVO", productVO); // ��Ʈw���X��productVO����,�s�Jreq
// �n�A�վ�				
				String url = "/product/listOneProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
		}		// if
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer product_id = Integer.valueOf(req.getParameter("product_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ProductService prodSvc = new ProductService();
				ProductVO productVO = prodSvc.getOneProduct(product_id);
//				EmpVO empVO = empSvc.getOneEmp(empno);
//				ProductService ProdSvc = new ProductService();
//				ProductVO productVO = ProdSvc.getOneProduct(product_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				String param = "?product_id="  +productVO.getProduct_id()+
						       "&product_name="  +productVO.getProduct_name()+
						       "&product_category_id="    +productVO.getProduct_category_id()+
						       "&product_describtion="+productVO.getProduct_describtion()+
						       "&product_price="    +productVO.getProduct_price()+
						       "&product_quantity="   +productVO.getProduct_quantity()+
						       "&product_status=" +productVO.getProduct_status();
				String url = "/product/update_product.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllProds.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.�����ШD�Ѽ�***************************************/
			    System.out.println(req.getParameter("product_id"));
				Integer product_id = Integer.valueOf(req.getParameter("product_id"));
				System.out.println(product_id-3);
				
				/***************************2.�}�l�R�����***************************************/
				ProductService prodSvc = new ProductService();
				prodSvc.deleteProduct(product_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/product/listAllProds.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
		}
		
		if ("update".equals(action)) { // �Ӧ�update_product.jsp���ШD
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				ProductVO prodVO = new ProductVO();
				Integer product_id = Integer.valueOf(req.getParameter("product_id").trim());
//				prodVO.setProduct_id(product_id);
				
				String product_name = req.getParameter("product_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.put("product_name","�ӫ~�W��: �ФŪť�");
				} else if(!product_name.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("product_name","�ӫ~�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
//				prodVO.setProduct_name(product_name);
				
				
				String product_describtion = req.getParameter("product_describtion");
				if (product_describtion == null || product_describtion.trim().length() == 0) {
					errorMsgs.put("product_describtion","�ӫ~�����ФŪť�");
				}
//				prodVO.setProduct_describtion(product_describtion);
				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					errorMsgs.put("hiredate","�п�J���");
//				}
//				
				Integer product_price = null;
				try {
					product_price = Integer.valueOf(req.getParameter("product_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("product_price","����ж�Ʀr");
				}
//				prodVO.setProduct_price(product_price);
				
				
//				Integer product_quantity = null;
//				try {
//					product_quantity = Integer.valueOf(req.getParameter("product_quantity").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("product_price","�ӫ~�ƶq�ж�Ʀr");
//				}
				
				Integer product_quantity = null;
				String product_quantitys = req.getParameter("product_quantity");
				if (product_quantitys == null || product_quantitys.trim().length() == 0) {
					errorMsgs.put("product_quantity","�ӫ~�ƶq: �ФŪť�");					
				} else {
					try {
						product_quantity = Integer.valueOf(req.getParameter("product_quantity").trim());
					} catch (NumberFormatException e) {
						errorMsgs.put("product_price","�ӫ~�ƶq�ж�Ʀr");
					}
				}
//				try {
//					product_quantity = Integer.valueOf(req.getParameter("product_quantity").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("product_quantity","�ӫ~�ƶq�ж�Ʀr");
//				}
//				prodVO.setProduct_quantity(product_quantity);
				
				
				Boolean product_status = null;
				product_status = Boolean.valueOf(req.getParameter("product_status"));
//				prodVO.setProduct_status(product_status);
//				System.out.println(product_status);
				
				Integer product_category_id = null;
				product_category_id = Integer.valueOf(req.getParameter("product_category_id"));
//				prodVO.setProduct_category_id(product_category_id);
//				Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/update_product.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ProductService prodSvc = new ProductService();
				ProductVO prodVO = new ProductVO();
				prodVO = prodSvc.updateProduct(product_id ,product_category_id, product_describtion, product_price, product_name, product_quantity, product_status);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("prodVO", prodVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/product/listOneProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
		}
		
		if ("insert".equals(action)) { // �Ӧ�update_product.jsp���ШD
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				String product_name = req.getParameter("product_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.put("product_name","�ӫ~�W��: �ФŪť�");
				} else if(!product_name.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("product_name","�ӫ~�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
//				prodVO.setProduct_name(product_name);
				
				
				String product_describtion = req.getParameter("product_describtion");
				if (product_describtion == null || product_describtion.trim().length() == 0) {
					errorMsgs.put("product_describtion","�ӫ~�����ФŪť�");
				}

				Integer product_price = null;
				try {
					product_price = Integer.valueOf(req.getParameter("product_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("product_price","����ж�Ʀr");
				}
//				prodVO.setProduct_price(product_price);
				
				
//				Integer product_quantity = null;
//				try {
//					product_quantity = Integer.valueOf(req.getParameter("product_quantity").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("product_price","�ӫ~�ƶq�ж�Ʀr");
//				}
				
				Integer product_quantity = null;
				try {
					product_quantity = Integer.valueOf(req.getParameter("product_quantity").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("product_quantity","����ж�Ʀr");
				}
				
//				String product_quantitys = req.getParameter("product_quantity");
//				if (product_quantitys == null || product_quantitys.trim().length() == 0) {
//					errorMsgs.put("product_quantity","�ӫ~�ƶq: �ФŪť�");					
//				} else {
//					try {
//						product_quantity = Integer.valueOf(req.getParameter("product_quantity").trim());
//					} catch (NumberFormatException e) {
//						errorMsgs.put("product_price","�ӫ~�ƶq�ж�Ʀr");
//					}
//				}
//				try {
//					product_quantity = Integer.valueOf(req.getParameter("product_quantity").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("product_quantity","�ӫ~�ƶq�ж�Ʀr");
//				}
//				prodVO.setProduct_quantity(product_quantity);
				
				
				Boolean product_status = null;
				product_status = Boolean.valueOf(req.getParameter("product_status"));
				
				Integer product_category_id = null;
				product_category_id = Integer.valueOf(req.getParameter("product_category_id"));

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/addProduct.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ProductService prodSvc = new ProductService();
				ProductVO prodVO = new ProductVO();
				prodVO = prodSvc.addOneProduct(product_category_id, product_describtion, product_price, product_name, product_quantity, product_status);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("prodVO", prodVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/product/listOneProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		
		
		
		
		
		
		

	}

}
