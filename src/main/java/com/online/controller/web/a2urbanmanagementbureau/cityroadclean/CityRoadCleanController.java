package com.online.controller.web.a2urbanmanagementbureau.cityroadclean;


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
import com.online.entity.online.a2urbanmanagementbureau.CityRoadClean;
import com.online.service.a2urbanmanagementbureau.CityRoadCleanService;



@Controller("webCityRoadCleanController")
@RequestMapping("/web/cityroadclean")
public class CityRoadCleanController extends BaseController{
	
	@Autowired
	private CityRoadCleanService cityRoadCleanService;
	
	
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		return "/admin/a2urbanmanagementbureau/cityroadclean/list";
	}
	
	
	/**
	 * 增加界面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		return "/admin/a2urbanmanagementbureau/cityroadclean/add";
	}

	
	
	/**
	 * 修改界面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("cityRoadClean", cityRoadCleanService.find(id));
		return "/admin/a2urbanmanagementbureau/cityroadclean/update";
	}
	
	/**
	 * 查看界面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("cityRoadClean", cityRoadCleanService.find(id));
		return "/admin/a2urbanmanagementbureau/cityroadclean/view";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CityRoadClean> query(Pageable pageable,CityRoadClean cityRoadClean) {
		return cityRoadCleanService.findPage(pageable);
		
	}
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(CityRoadClean cityRoadClean) {
		try {
			cityRoadCleanService.saveCityRoadClean(cityRoadClean);
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
	public Message update(CityRoadClean cityRoadClean) {
		try {
			cityRoadCleanService.updateCityRoadClean(cityRoadClean);
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
			cityRoadCleanService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
