package com.online.controller.admin.productunitconfig;


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
import com.online.entity.online.ProductUnitConfig;
import com.online.service.ProductOtherNameService;
import com.online.service.ProductUnitConfigService;



@Controller
@RequestMapping("/admin/productunitconfig")
public class ProductUnitConfigController extends BaseController{
	
	@Autowired
	private ProductUnitConfigService  productUnitConfigService ;
	@Autowired
	private ProductOtherNameService productOtherNameService;
	@RequestMapping("/list")
	public String list(){
		return "/admin/productunitconfig/list";
	}
	
	/**
	 * 增加产品单位配置页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/productunitconfig/add";
	}

	/**
	 * 修改产品单位配置页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("productUnitConfig", productUnitConfigService.find(id));
		return "/admin/productunitconfig/update";
	}
	
	/**
	 * 查看产品单位配置页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("productUnitConfig", productUnitConfigService.find(id));
		return "/admin/productunitconfig/view";
	}
	
	/**
	 * 查询产品单位配置
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ProductUnitConfig> query(Pageable pageable,ProductUnitConfig productUnitConfig,String otherName) {
        if (StringUtils.isNotEmpty(otherName)) {
            pageable.addFilter("productOtherName.otherName", Operator.like, "%" + otherName.trim() + "%");
        }
		return productUnitConfigService.findPage(pageable);
		
	}
	

	/**
	 * 保存产品单位配置
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ProductUnitConfig  productUnitConfig,String po) {
	    if (StringUtils.isNotEmpty(po)) {
	        ProductOtherName otherName = productOtherNameService.find(Integer.parseInt(po));
	        productUnitConfig.setProductOtherName(otherName);
        }
		 productUnitConfigService.save(productUnitConfig);
		return Message.success();
	}

	/**
	 * 更新产品单位配置
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ProductUnitConfig productUnitConfig,String po) {
		if (StringUtils.isNotEmpty(po)) {
	        ProductOtherName otherName = productOtherNameService.find(Integer.parseInt(po));
	        productUnitConfig.setProductOtherName(otherName);
        }
		productUnitConfigService.update(productUnitConfig);
		return Message.success();
	}

	/**
	 * 删除产品单位配置
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 productUnitConfigService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
