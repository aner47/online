package com.online.controller.admin.productothername;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.ProductOtherName;
import com.online.entity.online.ProductStandardName;
import com.online.service.ProductOtherNameService;
import com.online.service.ProductStandardNameService;



@Controller
@RequestMapping("/admin/productothername")
public class ProductOtherNameController extends BaseController{
	
	@Autowired
	private ProductOtherNameService  productOtherNameService ;
	@Autowired
	private ProductStandardNameService productStandardNameService;
	@RequestMapping("/list")
	public String list(){
		return "/admin/productothername/list";
	}
	
	/**
	 * 增加产品名称配置页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/productothername/add";
	}

	/**
	 * 修改产品名称配置页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("productOtherName", productOtherNameService.find(id));
		return "/admin/productothername/update";
	}
	
	/**
	 * 查看产品名称配置页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("productOtherName", productOtherNameService.find(id));
		return "/admin/productothername/view";
	}
	
	/**
	 * 查询产品名称配置
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ProductOtherName> query(Pageable pageable,ProductOtherName productOtherName) {
	    String otherName = productOtherName.getOtherName();
	    String industryCode = productOtherName.getIndustryCode();
	    if (StringUtils.isNotEmpty(otherName)) {
            pageable.addFilter("otherName", Operator.like, "%"+otherName.trim()+"%");
        }
	    if (StringUtils.isNotEmpty(industryCode)) {
            pageable.addFilter("industryCode", Operator.like,"%"+industryCode.trim()+"%");
        }
		return productOtherNameService.findPage(pageable);
		
	}
	

	/**
	 * 保存产品名称配置
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ProductOtherName  productOtherName,String pds) {
		productOtherNameService.save(productOtherName,pds);
		return Message.success();
	}

	/**
	 * 更新产品名称配置
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ProductOtherName productOtherName, String pds) {
		productOtherNameService.update(productOtherName,pds);
		return Message.success();
	}

	/**
	 * 删除产品名称配置
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 productOtherNameService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
