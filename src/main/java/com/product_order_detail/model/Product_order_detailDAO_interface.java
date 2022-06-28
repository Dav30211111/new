package com.product_order_detail.model;

import java.util.List;

import com.product_order_list.model.PolVO;


public interface Product_order_detailDAO_interface {
	public void insert(Product_order_detailVO order);
    public void update(Product_order_detailVO order, Integer order_id);
    public void delete(Integer order_id);
    public Product_order_detailVO findByPrimaryKey(Integer order_id);
    public List<Product_order_detailVO> getAll();
    public List<Product_order_detailVO>getOrderListByMemberId(Integer order_id);
    public void insertToPol(Product_order_detailVO podVO, List<PolVO> polVO);
    
}
