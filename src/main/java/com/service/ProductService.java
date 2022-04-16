package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bean.ProductBean;

@Service
public interface ProductService {

	public int addProduct(ProductBean productBean);

	public int deleteProduct(int pId);

	public int updateProduct(ProductBean productBean);

	public List<ProductBean> getAllProducts();

	public ProductBean getProductById(int pId);

}
