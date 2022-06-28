package com.members.controller;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.members.model.MemberService;
import com.members.model.MemberVO;

import javax.servlet.annotation.WebServlet;

@WebServlet("/loginhandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	   //【實際上應至資料庫搜尋比對】
//  protected boolean allowUser(String account, String password) {
//	  if ("tomcat".equals(account) && "tomcat".equals(password))
//      return true;
//    else
//      return false;
//  }
	
	  public void doGet(HttpServletRequest req, HttpServletResponse res)
              throws ServletException, IOException {
		  doPost(req,res);
	  }
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
	  
    req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
    PrintWriter out = res.getWriter();

    // �i���o�ϥΪ� �b��(account) �K�X(password)�j
//    String account = req.getParameter("account");
//    String password = req.getParameter("password");

    // 【取得使用者 帳號(account) 密碼(password)】  
    String action = req.getParameter("action");
    if("Login".equals(action)) {
    	String account = req.getParameter("account");
    	String password = req.getParameter("password");
    	
    	MemberService memsvc = new MemberService();
    	
    	MemberVO memberVO = memsvc.getfindByAccountAndPassword(account, password);
    	
		if(memberVO!=null){
		HttpSession session = req.getSession();
		session.setAttribute("memberVO", memberVO);
		String location = (String) session.getAttribute("location"); // 寫錯
		if(location != null) {
			res.sendRedirect(location); //寫錯
		}else { 
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front_end/members/Member_Login_Success.jsp");
			failureView.forward(req, res);
			return;//�{�����_
//			res.sendRedirect("/" + req.getContextPath() + "/back_end/member/listOneMember.jsp");
		}
	}else {
		out.println("<BODY>你的帳號 , 密碼無效!<BR>");
		out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/front_end/members/MemberLogin.jsp>重新登入</A>");
		
		
	}
    	
    
    	
//    	List<MemberVO> memVOList = memsvc.getAll();
//    	for(MemberVO memVO : memVOList ) {
//    		
//    		if(accounts.equals(memVO.getMember_email()) && passwords.equals(memVO.getMember_password())){
//    			HttpSession session = req.getSession();
//    			session.setAttribute("account", accounts);
//    			String location = (String) session.getAttribute("location");
//    			if(location != null) {
//    				res.sendRedirect(location);
//    			}else { 
//    				RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/member/listOneMember.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
////    				res.sendRedirect("/" + req.getContextPath() + "/back_end/member/listOneMember.jsp");
//    			}
//    		}else {
//    			out.println("<BODY>�A���b�� , �K�X�L��!<BR>");
//    			out.println("�Ы������s�n�J <A HREF="+req.getContextPath()+"/MemberLogin.jsp>���s�n�J</A>");
//    		}
//    	}
    }   
  }
}
//    if (!allowUser(account, password)) {          //�i�b�� , �K�X�L�Įɡj
//      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
//      out.println("<BODY>�A���b�� , �K�X�L��!<BR>");
//      out.println("�Ы������s�n�J <A HREF="+req.getContextPath()+"/MemberLogin.jsp>���s�n�J</A>");
//      out.println("</BODY></HTML>");
//    }else {                                       //�i�b�� , �K�X���Į�, �~���H�U�u�@�j
//      HttpSession session = req.getSession();
//      session.setAttribute("account", account);   //*�u�@1: �~�bsession�����w�g�n�J�L������
//      
//       try {                                                        
//         String location = (String) session.getAttribute("location");
//         if (location != null) {
//           session.removeAttribute("location");   //*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
//           res.sendRedirect(location);            
//           return;
//         }
//       }catch (Exception ignored) { }
//
//      res.sendRedirect(req.getContextPath()+"/select_member.jsp");  //*�u�@3: (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
//    }