package com.jbk.API.Dao;

import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.API.Entity.Product;
import com.jbk.API.Model.ProductSupplier;
import com.jbk.API.Service.ProductService;


@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean saveProduct(Product product) {
		boolean isAdded=false;
		
		Session session =sessionFactory.openSession();

		try {
			Product pro=  session.get(Product.class, product.getProductId());
			if(pro==null) {
				Transaction transaction = session.beginTransaction();
				session.save(product);
				transaction.commit();
				isAdded=true;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			if(session!=null) {
				session.close();
			}
			
		}
		return isAdded;
	}

	@Override
	public Product getProductById(int productId) {
		Session session =sessionFactory.openSession();
		Product product=null;
		try {
			product=session.get(Product.class, productId);
			if(product==null) {
				session.save(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return product;
		
	}

	@Override
	public List<Product> getAllProducts() {
		Session session =sessionFactory.openSession();
		List<Product> list= null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("productName"));
			list= criteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public boolean deleteProduct(int productId) {

		Session session = sessionFactory.openSession();
		boolean isDeleted=false;
		
		try {
			Product product= session.get(Product.class, productId);
			Transaction transaction = session.beginTransaction();
			if(product!=null) {
				session.delete(product);
				transaction.commit();
				isDeleted=true;
			}
				
		} catch (Exception e) {
			e.printStackTrace();		
			}finally {
				if(session!=null) {
					session.close();
				}
			}
		return isDeleted;
	}

	@Override
	public List<Product> sortProductById() {
		Session session =sessionFactory.openSession();
		List<Product> list= null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("productId"));
			list= criteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Product> sortProductByIdDESC() {
		Session session =sessionFactory.openSession();
		List<Product> list= null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.desc("productId"));
			list= criteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Product> getMaxPriceProduct() {
		Session session =sessionFactory.openSession();
		List<Product> maxProduct=null;
		double maxPrice=0;
		try {
			Criteria maxcriteria = session.createCriteria(Product.class);
			Criteria eqcriteria = session.createCriteria(Product.class);
			maxcriteria.setProjection(Projections.max("productPrice"));
			List<Double> list= maxcriteria.list();
			
			eqcriteria.add(Restrictions.eq("productPrice",maxPrice ));
			maxProduct= eqcriteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return maxProduct;
	}

	@Override
	public Double getSumOfPrice() {
		Session session =sessionFactory.openSession();
		List<Double> list= null;
		double sum=0;

		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.sum("productPrice"));
			 list = criteria.list();
			 if(!list.isEmpty()) {
				 sum = list.get(0);
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return sum;
	}

	@Override
	public Long getCountOfProduct() {
		Session session =sessionFactory.openSession();
		List<Double> list= null;
		Long row=null;

		try {
			Criteria criteria = session.createCriteria(Product.class);
			  criteria.setProjection(Projections.rowCount());
			 List product = criteria.list();
	            if (product!=null) {
	                row = (Long) product.get(0);
	            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return row;


	}

	@Override
	public boolean updateProduct(Product product) {
		Session session =sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isUpdated=false;
		try {
			Product product2 = session.get(Product.class, product.getProductId());
			session.evict(product2);
	            if (product2!=null) {
	            	
	   			     session.update(product);
	                 transaction.commit();
	                 isUpdated=true;
	            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return isUpdated;
	}

	@Override
	public int excelToDb(List<Product> list) {
		int addedCount=0;
		boolean isAdded=false;
		for(Product product : list) {
			 isAdded= saveProduct(product);	
			 if(isAdded) {
				addedCount = addedCount+1; 
			 }
			 }
		return addedCount;
	}

	



	
	

}
