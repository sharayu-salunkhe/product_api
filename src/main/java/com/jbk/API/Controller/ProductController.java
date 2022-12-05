package com.jbk.API.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.API.Entity.Product;
import com.jbk.API.Exception.ListIsEmptyException;
import com.jbk.API.Exception.ProductAlreadyExistException;

import com.jbk.API.Exception.ProductNotFoundWithIdException;
import com.jbk.API.Model.ProductSupplier;
import com.jbk.API.Model.Supplier;
import com.jbk.API.Service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	
	@PostMapping(value = "/saveproduct")
	public ResponseEntity<Boolean> saveProduct(@RequestBody Product product) {
		boolean isAdded= productService.saveProduct(product);
		if(isAdded) {
			return new ResponseEntity<Boolean>(isAdded,HttpStatus.CREATED);
		}else {
			throw new ProductAlreadyExistException("product already exist id --> "+product.getProductId());
		}
	
		
	}
	@PutMapping(value = "/updateproduct")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product){
		boolean isUpdated= productService.updateProduct(product);
		
			return new ResponseEntity<Boolean> (isUpdated,HttpStatus.OK);
	
		
	}

	@GetMapping(value = "/getproductbyid{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable int productId){
	Product pr= 	productService.getProductById(productId);
	if(pr!=null) {
		return new ResponseEntity<Product>(pr,HttpStatus.OK);
	}else {
		throw new ProductNotFoundWithIdException("product not found with id--> "+productId);
	}
	
	
}
	@GetMapping(value = "getProductAndSupplierById{productId}")
	public ResponseEntity<ProductSupplier> getProductAndSupplierById(@PathVariable int productId){
		ProductSupplier productSupplier= productService.getProductAndSupplierById(productId);
			return new ResponseEntity<ProductSupplier> (productSupplier, HttpStatus.OK);	
		}
	
	@GetMapping(value = "/getallproducts")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> list=  productService.getAllProducts();
		
		if(!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
		}else {
			throw new ListIsEmptyException("product list is empty");
		}
		
	}
	
	@DeleteMapping(value = "/deleteproduct{productId}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable int productId){
		boolean isDeleted= productService.deleteProduct(productId);
		if(isDeleted) {
			return new ResponseEntity<Boolean> (isDeleted,HttpStatus.OK);
		}else {
		throw new ProductNotFoundWithIdException("product not found to delete id "+productId);
				
		}
		
		
	} 
	@GetMapping(value = "/sortproductASC")
	public ResponseEntity<List<Product>> sortProductById(){
		List<Product> list= productService.sortProductById();
		if(!list.isEmpty()) {
			return new ResponseEntity<List<Product>> (list,HttpStatus.OK);
		}else {
			throw new ListIsEmptyException("no product to sort");
		}
		
		
	}
	@GetMapping(value = "/sortproductDESC")
	public ResponseEntity<List<Product>> sortProductByIdDESC(){
		List<Product> list= productService.sortProductByIdDESC();
		if(!list.isEmpty()) {
			return new ResponseEntity<List<Product>> (list,HttpStatus.OK);
		}else {
			throw new ListIsEmptyException("no product to sort");
		}
		
		
	}
	@GetMapping(value = "/getmaxprice")
	public ResponseEntity<List<Product>> getMaxPriceProduct(){
		List<Product> maxPriceProduct= productService.getMaxPriceProduct();
	
			return new ResponseEntity<List<Product>>(maxPriceProduct, HttpStatus.OK);
		
		}
		
	@GetMapping(value = "/getsumofprice")
	public ResponseEntity<Double> getSumOfPrice(){
		Double sum=  productService.getSumOfPrice();
			return new ResponseEntity<Double>(sum,HttpStatus.OK);
		}
		
		
	@GetMapping(value = "/getcountofproduct")
	public ResponseEntity<Long> getCountOfProduct(){
		Long row= productService.getCountOfProduct();
		return new ResponseEntity<Long>(row,HttpStatus.OK);
		
		
	}
	@PostMapping(value = "/uploadsheet")
	public ResponseEntity<String> uploadSheet(@RequestParam MultipartFile file,HttpSession session){
		String msg = productService.uploadSheet(file, session);
		
		
		return new ResponseEntity<String>(msg+" record added",HttpStatus.OK);
		
	}

	
		
	
}


