package com.online.controller.admin.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.Product;
import com.online.service.ProductService;



@Controller("productController")
@RequestMapping("/admin/product")
public class ProductController extends BaseController{
	
	@Autowired
	private ProductService  productService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/product/list";
	}
	
	/**
	 * 增加产品页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/product/add";
	}

	/**
	 * 修改产品页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("product", productService.find(id));
		return "/admin/product/update";
	}
	
	/**
	 * 查看产品页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("product", productService.find(id));
		return "/admin/product/view";
	}
	
	/**
	 * 查询产品
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Product> query(Pageable pageable,Product product) {
		pageable.addEntity(product);
		return productService.findPage(pageable);
		
	}
	

	/**
	 * 保存产品
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Product  product) {
		 productService.save(product);
		return Message.success();
	}

	/**
	 * 更新产品
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Product product) {
		productService.update(product);
		return Message.success();
	}

	/**
	 * 删除产品
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 productService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
