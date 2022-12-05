package com.jbk.API.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.API.Dao.ProductDao;
import com.jbk.API.Entity.Product;
import com.jbk.API.Model.ProductSupplier;
import com.jbk.API.Model.Supplier;

@Service
public class ProductServiceImpl implements ProductService {

	int totalProdutInSheet=0;
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public boolean saveProduct(Product product) {
		boolean isAdded=  productDao.saveProduct(product);
		return isAdded;
	}

	@Override
	public Product getProductById(int productId) {
		Product pr = productDao.getProductById(productId);
		return pr;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> list= productDao.getAllProducts();
		return list;
	}

	@Override
	public boolean deleteProduct(int productId) {
		boolean isDeleted= productDao.deleteProduct(productId);
		return isDeleted;
	}

	@Override
	public List<Product> sortProductById() {
		List<Product> list= productDao.sortProductById();
		return list;
	}

	@Override
	public List<Product> sortProductByIdDESC() {
		List<Product> list= productDao.sortProductByIdDESC();
		return list;
	}

	@Override
	public List<Product> getMaxPriceProduct() {
		List<Product> list= productDao.getMaxPriceProduct();
		return list;
	}

	@Override
	public Double getSumOfPrice() {
		Double sum= productDao.getSumOfPrice();
		return sum;
	}

	@Override
	public Long getCountOfProduct() {
		Long row = productDao.getCountOfProduct();
		return row;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean isUpdated= productDao.updateProduct(product);
		return isUpdated;
	}

	//File upload
	
	public List<Product> readExcel(String path) {
		Product product=null;
		List<Product> list = new ArrayList<>();
	
		try {
			FileInputStream fis= new FileInputStream(new File(path));
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet= workbook.getSheetAt(0);
			totalProdutInSheet =sheet.getLastRowNum();
		Iterator<Row> rows=	sheet.rowIterator();
		int cnt=0;
	
		while(rows.hasNext()) {
			Row row= rows.next();
			product = new Product();
			if(cnt==0) {
				cnt++;
				continue;  //to skip 1st row
			}
			
		Iterator<Cell> cells=	row.cellIterator();
		while(cells.hasNext()) {
			Cell cell = cells.next();
		    int col = cell.getColumnIndex();
		    
		    switch (col) {
			case 0:{
				product.setProductId((int) cell.getNumericCellValue()) ;
				break;
			}
            case 1:{
				product.setProductName(cell.getStringCellValue()); 
				break;
			}
            case 2:{
				product.setProductQty((int) cell.getNumericCellValue()); 
				break;
			}
            case 3:{
            	product.setProductPrice(cell.getNumericCellValue());
	           break;
            }
             case 4:{
            	product.setProductType(cell.getStringCellValue()); 
	           break;
            }
            
			default:
				break;
			}
		}
		
	list.add(product);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list ;
		
	}

	@Override
	public String uploadSheet(MultipartFile file, HttpSession session) {
		String filename = file.getOriginalFilename();
		String path= session.getServletContext().getRealPath("/uploaded");
		int addedCount=0;
		try {
			byte[] data=  file.getBytes();
			FileOutputStream fos = new FileOutputStream(new File(path+File.separator+filename));
			fos.write(data);
			List<Product> list= readExcel(path+File.separator+filename);
			
//			for(Product product : list) {
//				System.out.println(product);
//			}
			addedCount= productDao.excelToDb(list);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return "total product in sheet= "+ totalProdutInSheet +" and added product count= "+addedCount;
	}

	@Override
	public ProductSupplier getProductAndSupplierById(int productId){
		ProductSupplier productSupplier = new ProductSupplier();
		Product product= getProductById(productId);
		productSupplier.setProduct(product);
		
		Supplier supplier = restTemplate.getForObject("http://localhost:8080/supplier/getSupplierById"+product.getSupplierId(), Supplier.class);
		 productSupplier.setSupplier(supplier); 
		
		return productSupplier;
		
	}

	
}
