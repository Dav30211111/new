package com.product_order_detail.model;

import java.util.List;

import com.product_order_list.model.PolVO;

public class Product_order_detailService {
	private Product_order_detailDAO_interface dao;
	
	public Product_order_detailService() {
		dao = new PodJDBCDAO();
	}
	
	public Product_order_detailVO addProductOrderDetail(Integer product_id, Integer member_id, Integer product_amounts,Integer payment_method, Integer order_status) {
		Product_order_detailVO order_detail = new Product_order_detailVO();
		
		order_detail.setProduct_id(product_id);
		order_detail.setMember_id(member_id);
		order_detail.setPayment_method(payment_method);
		order_detail.setProduct_amount(product_amounts);
		order_detail.setOrder_status(order_status);
		dao.insert(order_detail);
		
		return order_detail;
	};
    public Product_order_detailVO updateProductOrderDetail(Integer order_id, Integer product_amounts,Integer payment_method, Integer order_status) {
    	Product_order_detailVO order_detail = new Product_order_detailVO();
		order_detail.setPayment_method(payment_method);
		order_detail.setProduct_amount(product_amounts);
		order_detail.setOrder_status(order_status);
		dao.update(order_detail, order_id);
		return order_detail;
    };
    public void delete(Integer order_id) {
    	dao.delete(order_id);
    };
    public Product_order_detailVO getOneOrderDetail(Integer order_id) {
    	
    	return dao.findByPrimaryKey(order_id);
    };
    public List<Product_order_detailVO> getAll(){
    	return dao.getAll();
    };
    public List<Product_order_detailVO>getOrderListByMemberId(Integer memberId){
    	return dao.getOrderListByMemberId(memberId);
    }
    
    public void insertToPol(Product_order_detailVO podVO, List<PolVO> PolVOlist) {
    	dao.insertToPol(podVO, PolVOlist);
    }
    
    
}
