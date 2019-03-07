package com.online.controller.admin.sysproductindustry;


import java.util.List;

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
import com.online.entity.SysProductIndustry;
import com.online.service.SysProductIndustryService;

/**
 * 产品行业配置
 */

@Controller
@RequestMapping("/admin/sysproductindustry")
public class SysProductIndustryController extends BaseController{
	
	@Autowired
	private SysProductIndustryService  sysProductIndustryService ;
	
	@RequestMapping("/list")
	public String list(){
		return "/admin/sysproductindustry/list";
	}
	
	/**
	 * 增加产品行业页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/sysproductindustry/add";
	}

	/**
	 * 修改产品行业页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("sysProductIndustry", sysProductIndustryService.find(id));
		return "/admin/sysproductindustry/update";
	}
	
	/**
	 * 查看产品行业页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("sysproductindustry", sysProductIndustryService.find(id));
		return "/admin/sysproductindustry/view";
	}
	
	/**
	 * 查询产品行业
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SysProductIndustry> query(Pageable pageable,SysProductIndustry sysProductIndustry) {
	    String name = sysProductIndustry.getIndustryName();
        if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("industryName", Operator.like,"%" +name.trim()+"%");
        }
		return sysProductIndustryService.findPage(pageable);
		
	}
	
	/**
	 * 查询产品行业
	 */
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public List<SysProductIndustry> queryAll(Pageable pageable,SysProductIndustry sysProductIndustry) {
		return sysProductIndustryService.findAll();
		
	}
	
	

	/**
	 * 保存产品行业
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SysProductIndustry  sysProductIndustry) {
		 sysProductIndustryService.save(sysProductIndustry);
		return Message.success();
	}

	
	
	/**
	 * 更新产品行业
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(SysProductIndustry sysProductIndustry) {
		sysProductIndustryService.update(sysProductIndustry);
		return Message.success();
	}

	/**
	 * 删除产品行业
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			sysProductIndustryService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
