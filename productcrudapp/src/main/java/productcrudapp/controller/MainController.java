package productcrudapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.view.RedirectView;
import java.util.ArrayList;
import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;

@Controller
public class MainController {

	@Autowired
	private ProductDao productdao;
	
	
	@RequestMapping("/")
	public String home(Model m) {
		
		ArrayList<Product> list=(ArrayList<Product>) productdao.getProducts();
		m.addAttribute("product", list);
		return "index";
	}
	//show add product form
	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		m.addAttribute("title","Add Product");
		return "add_product_form";
	}
	
	
	
	
	//handle and product form
	@RequestMapping(value="/handle-product", method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product p,HttpServletRequest request) {
		System.out.println(p);
		productdao.createProduct(p);
	 		RedirectView r=new RedirectView();
	 		r.setUrl(request.getContextPath()+ "/");
		return r;
	}
	
	
	//delete handler
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productid,HttpServletRequest request) {
		this.productdao.deleteproduct(productid);
 		RedirectView r=new RedirectView();
 		r.setUrl(request.getContextPath()+ "/");
	return r;
	}
	
	@RequestMapping("/update/{productId}")
	public String updateForm(@PathVariable("productId") int productid,Model m) {
		Product p=this.productdao.getProduct(productid);
		m.addAttribute("product", p);
		return "update_form";
	}
	
}
