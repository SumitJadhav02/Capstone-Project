package com.wipro.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wipro.ProductRepository;
import com.wipro.model.Product;

import jakarta.validation.Valid; 

@Service
public class ProductService {
//	public List<Product> findAllProducts() {
//		ArrayList<Product> products = new ArrayList<Product>();
//		products.add(new Product(100, "Laptop", 9000.0));  
//		products.add(new Product(101, "Smart TV", 60000.00));  
//		products.add(new Product(102, "Mobile",  9000.00));  
//		return products;
//	}
	@Autowired
	ProductRepository productRepo;
	
	public List<Product> getProductsFromDatabase() {
		return (productRepo.findAll());
	}
	public Optional<Product> getProductById(int n) {
		return productRepo.findById(n);
	}
	
	public Product createProduct(@Valid Product newProduct) {
		
		return productRepo.save(newProduct);
	}
	public ResponseEntity<Product> updateProduct(Integer productId, @Valid Product newProduct) {
		
		Optional<Product> updatedProduct = productRepo.findById(productId);
		 Product p1 = updatedProduct.get();
		 
		p1.setPname(newProduct.getPname());
			p1.setPrice(newProduct.getPrice());
			productRepo.save(p1);
		 System.out.println(p1);
		return ResponseEntity.ok(p1);	 
	}
	
	public Map<String, Boolean> deleteProduct(Integer productId){
		
//		productRepo.deleteById(id);
//		productRepo.deleteById(productId);
		Optional<Product> deleteProductObj = productRepo.findById(productId);
		productRepo.delete(deleteProductObj.get());
		Map<String,Boolean> response  = new HashMap<>();
		response.put("Product has been Deleted", Boolean.TRUE);
		
		return response;
		
	}
}
