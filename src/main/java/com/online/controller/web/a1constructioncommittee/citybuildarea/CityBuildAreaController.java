package com.online.controller.web.a1constructioncommittee.citybuildarea;


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
import com.online.entity.online.a1constructioncommittee.CityBuildArea;
import com.online.service.a1constructioncommittee.CityBuildAreaService;



@Controller("webCityBuildAreaController")
@RequestMapping("/web/citybuildarea")
public class CityBuildAreaController extends BaseController{
	
	@Autowired
	private CityBuildAreaService cityBuildAreaService;
	
	
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		return "/admin/a1constructioncommittee/citybuildarea/list";
	}
	
	
	/**
	 * 增加界面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		return "/admin/a1constructioncommittee/citybuildarea/add";
	}

	
	
	/**
	 * 修改界面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("cityBuildArea", cityBuildAreaService.find(id));
		return "/admin/a1constructioncommittee/citybuildarea/update";
	}
	
	/**
	 * 查看界面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("cityBuildArea", cityBuildAreaService.find(id));
		return "/admin/a1constructioncommittee/citybuildarea/view";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CityBuildArea> query(Pageable pageable,CityBuildArea cityBuildArea) {
		return cityBuildAreaService.findPage(pageable);
		
	}
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(CityBuildArea cityBuildArea) {
		try {
			cityBuildAreaService.saveCityBuildArea(cityBuildArea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success();
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(CityBuildArea cityBuildArea) {
		try {
			cityBuildAreaService.updateCityBuildArea(cityBuildArea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success();
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			cityBuildAreaService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
