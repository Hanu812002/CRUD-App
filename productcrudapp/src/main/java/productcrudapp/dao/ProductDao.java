package productcrudapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import productcrudapp.model.Product;

@Component
public class ProductDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public void createProduct(Product product) {
		this.hibernateTemplate.saveOrUpdate(product);
	}

	
	//get all product
	public List<Product>getProducts(){
		List<Product> pro=this.hibernateTemplate.loadAll(Product.class);
		return pro;
	}
	
	
	
	//delete
	@Transactional
	public void deleteproduct(int pid) {
		Product p=this.hibernateTemplate.load(Product.class,pid);
		this.hibernateTemplate.delete(p);
		
	}
	
	public Product getProduct(int pid) {
		
		return this.hibernateTemplate.get(Product.class, pid);
	}
	
	
	
}
