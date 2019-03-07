package com.online.controller.admin.area;

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
import com.online.entity.Area;
import com.online.service.AreaService;

/** 
 * @author 作者名 
 * @time   2017年5月5日 上午9:59:26 
 */
@Controller("areaController")
@RequestMapping("/admin/area")
public class AreaController extends BaseController{
	
	@Autowired
	private AreaService areaService;

	@RequestMapping("/list")
	public String list(){
		return "/admin/area/list";
	}
	
	/**
	 * 增加区域页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/area/add";
	}

	/**
	 * 修改区域页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("area", areaService.find(id));
		return "/admin/area/update";
	}
	
	/**
	 * 查看区域页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("area", areaService.find(id));
		return "/admin/area/view";
	}
	
	/**
	 * 查询区域
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Area> query(Pageable pageable,String name,String level,String parent,String code,String source) {
		if(StringUtils.isNotEmpty(name)){
			pageable.addFilter("name", Operator.like, "%"+name+"%");
		}
		if(StringUtils.isNotEmpty(parent)){
			pageable.addFilter("parent", Operator.like, "%"+parent+"%");
		}
		if(StringUtils.isNotEmpty(code)){
			pageable.addFilter("code", Operator.like, "%"+code+"%");
		}
		if(StringUtils.isNotEmpty(level)){
			pageable.addFilter("level", Operator.eq, Integer.parseInt(level));
		}
		if(StringUtils.isNotEmpty(source)){
			pageable.addFilter("source", Operator.eq, Integer.parseInt(source));
		}
		
		return areaService.findPage(pageable);
	}
	
	/**
	 * 保存区域
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Area area,Integer source) {
		area.setSource(source);
		 areaService.save(area);
		return Message.success();
	}

	/**
	 * 更新区域
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Area area) {
		areaService.update(area);
		return Message.success();
	}

	/**
	 * 删除区域
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 areaService.delete(ids[i]);
		}
		return Message.success();
	}
}