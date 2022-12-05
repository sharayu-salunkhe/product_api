package com.jbk.API.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.API.Entity.Product;
import com.jbk.API.Model.ProductSupplier;

public interface ProductService {


	public boolean saveProduct(Product product);
	public Product getProductById(int productId);
	public List<Product> getAllProducts();
	public boolean deleteProduct(int productId);
	public List<Product> sortProductById();
	public List<Product> sortProductByIdDESC();
	public List<Product> getMaxPriceProduct();
	public Double getSumOfPrice();
	public Long getCountOfProduct();
	public boolean updateProduct(Product product);
	
	public String uploadSheet(MultipartFile file,HttpSession session);
	public ProductSupplier getProductAndSupplierById(int productId);
}
