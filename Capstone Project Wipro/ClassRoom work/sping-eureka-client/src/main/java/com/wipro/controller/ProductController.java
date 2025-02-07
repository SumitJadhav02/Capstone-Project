package com.wipro.controller;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.model.Product;
import com.wipro.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/helloapi")

public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/hello")
    String hello() {
        return "Hello World, Spring Boot.... Welcome to You!";
    }
	
//	@GetMapping(path = "/products", produces = {MediaType.APPLICATION_XML_VALUE})
//	  @GetMapping(path = "/products", produces = {MediaType.APPLICATION_JSON_VALUE})
	@GetMapping("/products")
	    List<Product> products() {
	        return productService.getProductsFromDatabase(); // JAVA Objects
	    }
	
	@GetMapping("/products/{id}")
    Optional<Product> getProductById(@PathVariable int id)
    {
		return productService.getProductById(id);
	}
	@PostMapping("/products")
    public Product createEmployee(@Valid @RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }
	
	@PutMapping("/products/{id}")
    public ResponseEntity<Product> updateEmployee(@PathVariable(value = "id")  Integer productId, @Valid @RequestBody Product newProduct) 
    {
        return productService.updateProduct(productId, newProduct);
    }
	
//	@DeleteMapping("/products/{id}")
//	public void delete(@PathVariable(value="id")Integer productId){
//		
//		productService.deleteProduct(productId);
//	}
	
	@DeleteMapping("/products/{id}")
    public Map<String,Boolean> deleteProduct(@PathVariable (value="id") Integer productId) 
    {
    	return productService.deleteProduct(productId);
    }
	
 }