package com.jbk.API.Model;

import com.jbk.API.Entity.Product;

public class ProductSupplier {

	private Product product;
	private Supplier supplier;
	
	public ProductSupplier() {
		// TODO Auto-generated constructor stub
	}

	public ProductSupplier(Product product, Supplier supplier) {
		super();
		this.product = product;
		this.supplier = supplier;
	}
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "ProductSupplier [product=" + product + ", supplier=" + supplier + "]";
	}
	
}
