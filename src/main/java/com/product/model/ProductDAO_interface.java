package com.product.model;

import java.util.List;

public interface ProductDAO_interface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void delete(Integer product_id);
	public ProductVO findByPrimarykey(Integer product_id);
	public List<ProductVO> getAll();
	
	public ProductVO findByProductName(String product_name);
//	public List<ProductVO> getAllProductsCateGoryId(Integer product_cateGory_id);

}

//Integer product_id, Integer product_category_id, String product_describtion, Integer product_price, String product_name, Integer product_quantity, Boolean product_status