package com.members.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.members.model.*;

@WebServlet 
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)

public class Member_Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

			String str = req.getParameter("member_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("�п�J�|���s��");
			}

			if (!errorMsgs.isEmpty()) { // �P�_errorMsgs�O���O�ŭ� �p�G�O���~�T�����O�� ���� �p�G���~�T���O�� �N�|return
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/select_member.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			Integer member_id = null;
			try {
				member_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("�|���s���榡�����T");
			}

			// �P�_��Ʈ榡���Ʀr �Y���O�Ʀr�h����
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/select_member.jsp");
				failureView.forward(req, res);
				return;// �{�����_

			}

			/*************************** 2.�}�l�d�߸�� *****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(member_id);
			if (memberVO == null) {
				errorMsgs.add("�d�L���");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/select_Member.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/back_end/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp1.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� ****************************************/
			Integer member_id = Integer.valueOf(req.getParameter("member_id"));

			/*************************** 2.�}�l�d�߸�� ****************************************/

			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(member_id);

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
			req.setAttribute("memberVO", memberVO); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/back_end/member/update_Member_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);

		}

		if ("update".equals(action)) { // �Ӧ�listAllMember.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			Integer member_id = Integer.valueOf(req.getParameter("member_id").trim());
			System.out.println(member_id);
			
			String member_email = req.getParameter("member_email");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (member_email == null || member_email.trim().length() == 0) {
//				errorMsgs.add("�|��Email: �ФŪť�");
//			} else if (!member_email.trim().matches(enameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//				errorMsgs.add("�|��Eamil: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//			}		
			String member_password = req.getParameter("member_password");
			
			String member_name = req.getParameter("member_name");
			
			String member_phone = req.getParameter("member_phone");
			
			String member_address = req.getParameter("member_address");
			
			String str = req.getParameter("member_status").trim();
			if (str == null || str.trim().length() == 0) {
				errorMsgs.add("�|�����A�ФŪť�");
			}
			Integer member_status = null;
			try {
				member_status = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("�|�����A�����T");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/addMember.jsp"); // �Y¾��s���ťթΤ����T
																									// �b��e������ܿ��~
				failureView.forward(req, res);
				return;// �{�����_
			}

			MemberVO memberVO = new MemberVO();
			memberVO.setMember_id(member_id);
			memberVO.setMember_email(member_email);
			memberVO.setMember_password(member_password);
			memberVO.setMember_name(member_name);
			memberVO.setMember_phone(member_phone);
			memberVO.setMember_address(member_address);
			memberVO.setMember_status(member_status);

//			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/update_member_input.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}

//			/*************************** 2.�}�l�ק��� *****************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMember(member_id, member_email, member_password, member_name, member_phone,
					member_address, member_status);

			/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			String url = "/back_end/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			
			String member_email = req.getParameter("member_email");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (member_email == null || member_email.trim().length() == 0) {
//				errorMsgs.add("�|��Email: �ФŪť�");
//			} else if (!member_email.trim().matches(enameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//				errorMsgs.add("�|��Eamil: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//			}
//			
			String member_password = req.getParameter("member_password");
			
			String member_name = req.getParameter("member_name");
			
			String member_phone = req.getParameter("member_phone");
			
			String member_address = req.getParameter("member_address");
			
			
			String str = req.getParameter("member_status").trim();
			if (str == null || str.trim().length() == 0) {
				errorMsgs.add("�|�����A�ФŪť�");
			}
			Integer member_status = null;
			try {
				member_status = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("�|�����A�����T");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/addMember.jsp"); // �Y¾��s���ťթΤ����T
																									// �b��e������ܿ��~
				failureView.forward(req, res);
				return;// �{�����_
			}

			MemberVO memberVO = new MemberVO();
			memberVO.setMember_email(member_email);
			memberVO.setMember_password(member_password);
			memberVO.setMember_name(member_name);
			memberVO.setMember_phone(member_phone);
			memberVO.setMember_address(member_address);
			memberVO.setMember_status(member_status);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); // �t����J�榡���~��empVO����,�]�s�Jreq �N�O��J���~ ��L���諸���|����
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/addMember.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�s�W��� ***************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMember(member_email, member_password, member_name, member_phone,
					member_address, member_status);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			String url = "/back_end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� ***************************************/
			Integer member_id = Integer.valueOf(req.getParameter("member_id"));

			/*************************** 2.�}�l�R����� ***************************************/
			MemberService memberSvc = new MemberService();
			memberSvc.deleteMember(member_id);

			/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
			String url = "/back_end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
		}
	}
}