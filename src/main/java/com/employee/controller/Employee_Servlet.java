package com.employee.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;

//@WebServlet 
//@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)

public class Employee_Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

			String str = req.getParameter("emp_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("�п�J���u�s��");
			}

			if (!errorMsgs.isEmpty()) { // �P�_errorMsgs�O���O�ŭ� �p�G�O���~�T�����O�� ���� �p�G���~�T���O�� �N�|return
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/select_employee.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			Integer emp_no = null;
			try {
				emp_no = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("���u�s���榡�����T");
			}

			// �P�_��Ʈ榡���Ʀr �Y���O�Ʀr�h����
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/select_employee.jsp");
				failureView.forward(req, res);
				return;// �{�����_

			}

			/*************************** 2.�}�l�d�߸�� *****************************************/
			EmployeeService empSvc = new EmployeeService();
			EmployeeVO employeeVO = empSvc.getOneEmployee(emp_no);
			if (employeeVO == null) {
				errorMsgs.add("�d�L���");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/select_employee.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
			req.setAttribute("employeeVO", employeeVO); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/back_end/emp/listOneEmployee.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp1.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� ****************************************/
			Integer emp_no = Integer.valueOf(req.getParameter("emp_no"));

			/*************************** 2.�}�l�d�߸�� ****************************************/

			EmployeeService empSvc = new EmployeeService();
			EmployeeVO employeeVO = empSvc.getOneEmployee(emp_no);

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
			req.setAttribute("employeeVO", employeeVO); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/back_end/emp/update_employee_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);

		}

		if ("update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			Integer emp_no = Integer.valueOf(req.getParameter("emp_no").trim());
			System.out.println(emp_no);

			Integer job_id = Integer.valueOf(req.getParameter("job_id").trim());
			System.out.println(job_id);
			
			String emp_name = req.getParameter("emp_name");
			
			
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (emp_name == null || emp_name.trim().length() == 0) {
				errorMsgs.add("���u�m�W: �ФŪť�");
			} else if (!emp_name.trim().matches(enameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}

			Integer emp_status = Integer.valueOf(req.getParameter("emp_status").trim());
			

//Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

			EmployeeVO employeeVO = new EmployeeVO();
			employeeVO.setEmp_no(emp_no);
			employeeVO.setJob_id(job_id);
			employeeVO.setEmp_name(emp_name);
			employeeVO.setEmp_status(emp_status);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/update_employee_input.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}

			/*************************** 2.�}�l�ק��� *****************************************/
			EmployeeService empSvc = new EmployeeService();
			employeeVO = empSvc.updateEmployee(emp_no, job_id, emp_name, emp_status);

			/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
			req.setAttribute("employeeVO", employeeVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			String url = "/back_end/emp/listOneEmployee.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			String str = req.getParameter("job_id").trim();
			if (str == null || str.trim().length() == 0) {
				errorMsgs.add("¾��s���ФŪť�");
			}
			Integer job_id = null;
			try {
				job_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("¾��s�������T");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/addEmployee.jsp"); // �Y¾��s���ťթΤ����T
																									// �b��e������ܿ��~
				failureView.forward(req, res);
				return;// �{�����_
			}

			String emp_name = req.getParameter("emp_name");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (emp_name == null || emp_name.trim().length() == 0) {
				errorMsgs.add("���u�m�W: �ФŪť�");
			} else if (!emp_name.trim().matches(enameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}

			String str1 = req.getParameter("emp_status").trim();
			if (str1 == null || str1.trim().length() == 0) {
				errorMsgs.add("�b¾���A�ФŪť�");
			}
			Integer emp_status = null;
			try {
				emp_status = Integer.valueOf(str1);
			} catch (Exception e) {
				errorMsgs.add("�b¾���A�����T");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/addEmployee.jsp"); // �p�Gstatus��J���~�����e�������
				failureView.forward(req, res);
				return;// �{�����_
			}


			EmployeeVO employeeVO = new EmployeeVO();
			employeeVO.setJob_id(job_id);
			employeeVO.setEmp_name(emp_name);
			employeeVO.setEmp_status(emp_status);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq �N�O��J���~ ��L���諸���|����
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/emp/addEmployee.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�s�W��� ***************************************/
			EmployeeService empSvc = new EmployeeService();
			employeeVO = empSvc.addEmployee(job_id, emp_name, emp_status);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			String url = "/back_end/emp/listAllEmployee.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� ***************************************/
			Integer emp_no = Integer.valueOf(req.getParameter("emp_no"));

			/*************************** 2.�}�l�R����� ***************************************/
			EmployeeService empSvc = new EmployeeService();
			empSvc.deleteEmployee(emp_no);

			/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
			String url = "/back_end/emp/listAllEmployee.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
		}
	}
}