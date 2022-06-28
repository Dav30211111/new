package com.product.model;

import java.util.List;

public class ProductService {
	private ProductDAO_interface dao;
	public ProductService() {
//		dao = new ProductJDBCDAO();
		dao = new ProductDAO();
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	 
	public ProductVO addOneProduct(Integer product_category_id, String product_describtion, Integer product_price, String product_name, Integer product_quantity, Boolean product_status) {
		ProductVO productVO = new ProductVO();
		productVO.setProduct_category_id(product_category_id);
		productVO.setProduct_describtion(product_describtion);
//		productVO.setProduct_id(product_id);
		productVO.setProduct_name(product_name);
		productVO.setProduct_price(product_price);
		productVO.setProduct_quantity(product_quantity);
		productVO.setProduct_status(product_status);
		dao.insert(productVO);
		
		return productVO;
	}
	
	// �w�d
	public void addOneProduct(ProductVO productVO) {
		dao.insert(productVO);
	}
// -----------------------------------
	public ProductVO getOneProduct(Integer product_id) {
		return dao.findByPrimarykey(product_id);
	}
// -----------------------------------------------	
	public ProductVO updateProduct(Integer product_id, Integer product_category_id, String product_describtion, Integer product_price, String product_name, Integer product_quantity, Boolean product_status) {
		ProductVO productVO = new ProductVO();
		productVO.setProduct_id(product_id);
		productVO.setProduct_category_id(product_category_id);
		productVO.setProduct_describtion(product_describtion);
		productVO.setProduct_id(product_id);
		productVO.setProduct_name(product_name);
		productVO.setProduct_price(product_price);
		productVO.setProduct_quantity(product_quantity);
		productVO.setProduct_status(product_status);
		
		dao.update(productVO);
		return productVO;
	}

	public void updateProduct(ProductVO productVO) {
		dao.update(productVO);
	}

//	public Set<ProductVO> getEmpsByProduct(Integer product_id) {
//		return dao.getEmpsByDeptno(product_id);
//	}

	public void deleteProduct(Integer product_id) {
		dao.delete(product_id);
	}
	
	public ProductVO getOneProductByName(String product_name) {
		return dao.findByProductName(product_name);
	}

}
