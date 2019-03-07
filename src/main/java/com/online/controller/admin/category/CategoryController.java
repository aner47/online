package com.online.controller.admin.category;


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
import com.online.entity.online.Category;
import com.online.entity.online.Category.CategoryLevel;
import com.online.service.CategoryService;



@Controller
@RequestMapping("/admin/category")
public class CategoryController extends BaseController{
	
	@Autowired
	private CategoryService  categoryService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/category/list";
	}
	
	/**
	 * 增加行业分类页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/category/add";
	}

	/**
	 * 修改行业分类页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("category", categoryService.find(id));
		return "/admin/category/update";
	}
	
	/**
	 * 查看行业分类页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("category", categoryService.find(id));
		return "/admin/category/view";
	}
	
	/**
	 * 查询行业分类
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Category> query(Pageable pageable,Category category) {
	    String name = category.getName();
	    String code = category.getCode();
	    String parent = category.getParent();
	    CategoryLevel categoryLevel = category.getCategoryLevel();
	    if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("name", Operator.like, "%"+name.trim()+"%");
        }
	    if (StringUtils.isNotEmpty(code)) {
            pageable.addFilter("code", Operator.like,"%"+code.trim()+"%");
        }
	    if (StringUtils.isNotEmpty(parent)) {
            pageable.addFilter("parent", Operator.like, "%"+parent.trim()+"%");
        }
	    if (categoryLevel!=null) {
            pageable.addFilter("categoryLevel", Operator.eq, categoryLevel);
        }
		return categoryService.findPage(pageable);
		
	}
	

	/**
	 * 保存行业分类
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Category  category) {
		 categoryService.save(category);
		return Message.success();
	}

	/**
	 * 更新行业分类
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Category category) {
		categoryService.update(category);
		return Message.success();
	}

	/**
	 * 删除行业分类
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 categoryService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
