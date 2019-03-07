package com.online.controller.admin.productstandardname;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.Filter.Operator;
import com.online.controller.base.BaseController;
import com.online.entity.online.ProductStandardName;
import com.online.service.ProductStandardNameService;



@Controller
@RequestMapping("/admin/productstandardname")
public class ProductStandardNameController extends BaseController{
	
	@Autowired
	private ProductStandardNameService  productStandardNameService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/productstandardname/list";
	}
	
	/**
	 * 增加产品标准名称配置页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/productstandardname/add";
	}

	/**
	 * 修改产品标准名称配置页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("productStandardName", productStandardNameService.find(id));
		return "/admin/productstandardname/update";
	}
	
	/**
	 * 查看产品标准名称配置页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("productStandardName", productStandardNameService.find(id));
		return "/admin/productstandardname/view";
	}
	
	/**
	 * 查询产品标准名称配置
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ProductStandardName> query(Pageable pageable,ProductStandardName productStandardName) {
	    String standardName = productStandardName.getStandardName();
        String industryCode = productStandardName.getIndustryCode();
        if (StringUtils.isNotEmpty(standardName)) {
            pageable.addFilter("standardName", Operator.like, "%"+standardName.trim()+"%");
        }
        if (StringUtils.isNotEmpty(industryCode)) {
            pageable.addFilter("industryCode", Operator.like,"%"+industryCode.trim()+"%");
        }
		return productStandardNameService.findPage(pageable);
		
	}
	

	/**
	 * 保存产品标准名称配置
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ProductStandardName  productStandardName) {
		 productStandardNameService.save(productStandardName);
		return Message.success();
	}

	/**
	 * 更新产品标准名称配置
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ProductStandardName productStandardName) {
		productStandardNameService.update(productStandardName);
		return Message.success();
	}

	/**
	 * 删除产品标准名称配置
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 productStandardNameService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
