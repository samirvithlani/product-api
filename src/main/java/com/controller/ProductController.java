package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ProductBean;
import com.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/addproduct")
	public ResponseEntity<ProductBean> addProduct(@RequestBody ProductBean productBean) {

		int res = productService.addProduct(productBean);
		if (res > 0) {

			return new ResponseEntity<>(productBean, HttpStatus.CREATED);
		}

		return new ResponseEntity<>(HttpStatus.CONFLICT);

	}

	@GetMapping("/getproduct")
	public ResponseEntity<List<ProductBean>> getAllProducts() {

		List<ProductBean> products = productService.getAllProducts();
		if (products.size() > 0) {

			return new ResponseEntity<List<ProductBean>>(products, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
	}

	@DeleteMapping("/deleteproduct/{pId}")
	public ResponseEntity deleteProduct(@PathVariable("pId") int pId) {

		int res = productService.deleteProduct(pId);
		if (res > 0) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);

	}

	@GetMapping("/getproduct/{pId}")
	public ResponseEntity<ProductBean> getProductById(@PathVariable("pId") int pId) {

		ProductBean productBean = productService.getProductById(pId);
		if (productBean != null) {

			return new ResponseEntity<ProductBean>(productBean, HttpStatus.OK);
		}
		return new ResponseEntity<ProductBean>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("/updateproduct")
	public ResponseEntity<ProductBean> updateProduct(@RequestBody ProductBean productBean){
		
		
		int res = productService.updateProduct(productBean);
		if(res>0) {
			
			return new ResponseEntity<ProductBean>(HttpStatus.CREATED);
		}
		return new ResponseEntity<ProductBean>(HttpStatus.CONFLICT);
		
		
	}
	
	

}
