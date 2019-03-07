package com.online.controller.admin.categorymenuconfig;


import javax.persistence.PersistenceException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
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
import com.online.entity.online.CategoryMenuConfig;
import com.online.service.CategoryMenuConfigService;



@Controller
@RequestMapping("/admin/categorymenuconfig")
public class CategoryMenuConfigController extends BaseController{
	
	@Autowired
	private CategoryMenuConfigService  categoryMenuConfigService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/categorymenuconfig/list";
	}
	
	/**
	 * 增加行业分类页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/categorymenuconfig/add";
	}

	/**
	 * 修改行业分类页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("categoryMenuConfig", categoryMenuConfigService.find(id));
		return "/admin/categorymenuconfig/update";
	}
	
	/**
	 * 查看行业分类页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("categoryMenuConfig", categoryMenuConfigService.find(id));
		return "/admin/categorymenuconfig/view";
	}
	
	/**
	 * 查询行业分类
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CategoryMenuConfig> query(Pageable pageable,CategoryMenuConfig categoryMenuConfig
			,String industryCategoryCodeSmall,String industryCategoryCodeMiddle,String industryCategoryCodeMain) {
	    if(StringUtils.isNotBlank(categoryMenuConfig.getEnterpriseType())){
	    	pageable.addFilter("enterpriseType",Operator.eq,categoryMenuConfig.getEnterpriseType());
	    }
		
	    if(StringUtils.isNotBlank(industryCategoryCodeSmall)){
			categoryMenuConfig.setCategoryId(industryCategoryCodeSmall);
			pageable.addFilter("categoryId",Operator.eq,industryCategoryCodeSmall);
		}else if(StringUtils.isNotBlank(industryCategoryCodeMiddle)){
			categoryMenuConfig.setCategoryId(industryCategoryCodeMiddle);
			pageable.addFilter("categoryId",Operator.eq,industryCategoryCodeMiddle);
		}else if(StringUtils.isNotBlank(industryCategoryCodeMain)){
			categoryMenuConfig.setCategoryId(industryCategoryCodeMain);
			pageable.addFilter("categoryId",Operator.eq,industryCategoryCodeMain);
		}
		
		return categoryMenuConfigService.findPage(pageable);
		
	}
	

	/**
	 * 保存行业分类
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(CategoryMenuConfig  categoryMenuConfig,String industryCategoryCodeMain,String industryCategoryCodeMiddle
			,String industryCategoryCodeSmall) {
		if(StringUtils.isNotBlank(industryCategoryCodeSmall)){
			categoryMenuConfig.setCategoryId(industryCategoryCodeSmall);
		}else if(StringUtils.isNotBlank(industryCategoryCodeMiddle)){
			categoryMenuConfig.setCategoryId(industryCategoryCodeMiddle);
		}else if(StringUtils.isNotBlank(industryCategoryCodeMain)){
			categoryMenuConfig.setCategoryId(industryCategoryCodeMain);
		}
		try {
			categoryMenuConfigService.save(categoryMenuConfig);
			return Message.success();
		} catch (PersistenceException | ConstraintViolationException e) {
			return Message.error("同一行业只能添加一次", null);
		}
		
	}

	/**
	 * 更新行业分类
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(CategoryMenuConfig categoryMenuConfig,String industryCategoryCodeMain,String industryCategoryCodeMiddle
			,String industryCategoryCodeSmall,String categoryId) {
		if(StringUtils.isNotBlank(industryCategoryCodeSmall)){
			categoryMenuConfig.setCategoryId(industryCategoryCodeSmall);
		}else if(StringUtils.isNotBlank(industryCategoryCodeMiddle)){
			categoryMenuConfig.setCategoryId(industryCategoryCodeMiddle);
		}else if(StringUtils.isNotBlank(industryCategoryCodeMain)){
			categoryMenuConfig.setCategoryId(industryCategoryCodeMain);
		}else{
			categoryMenuConfig.setCategoryId(categoryId);
		}
		categoryMenuConfigService.update(categoryMenuConfig);
		return Message.success();
	}

	/**
	 * 删除行业分类
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			categoryMenuConfigService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
