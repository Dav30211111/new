package com.product.model_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;


@WebServlet("/productTypeSearch")
public class productTypeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public productTypeSearch() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			String action = req.getParameter("action");
			
			if("searchTyping".equals(action)) {
				//針對文字搜尋
			}
			
			if("sort-bar".equals(action)) {
				//針對排序
				Integer priceSort = Integer.valueOf(req.getParameter("show-product-price-sort"));
				ProductVO poVO = new ProductVO();
				ProductService posvc = new ProductService();
				if(priceSort == 0) {
//					要求從高到低
					
					req.setAttribute("posvc", posvc);
					req.setAttribute("poVO", poVO);
					
					String url = "/front_end/purchasehomepage/PriceFromHtoL.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 
					successView.forward(req, res);
				}
				if(priceSort == 1) {
//					要求低到高
					req.setAttribute("posvc", posvc);
					req.setAttribute("poVO", poVO);
					
					String url = "/front_end/purchasehomepage/PriceFromLtoH.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 
					successView.forward(req, res);
				}
				
				
			}
		
	}

}
