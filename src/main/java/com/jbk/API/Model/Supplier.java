package com.jbk.API.Model;


public class Supplier {

	private int supplierId;
	private String supplierName;
	private String supplierCountry;
	private String supplierEmail;
	private Long supplierPhone;
	
	public Supplier() {
		// TODO Auto-generated constructor stub
	}

	public Supplier(int supplierId, String supplierName, String supplierCountry, String supplierEmail,
			Long supplierPhone) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierCountry = supplierCountry;
		this.supplierEmail = supplierEmail;
		this.supplierPhone = supplierPhone;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCountry() {
		return supplierCountry;
	}

	public void setSupplierCountry(String supplierCountry) {
		this.supplierCountry = supplierCountry;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public Long getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(Long supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierCountry="
				+ supplierCountry + ", supplierEmail=" + supplierEmail + ", supplierPhone=" + supplierPhone + "]";
	}

	
	
	
}

