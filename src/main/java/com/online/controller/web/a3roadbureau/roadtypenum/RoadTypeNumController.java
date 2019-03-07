package com.online.controller.web.a3roadbureau.roadtypenum;


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
import com.online.entity.online.a3roadbureau.RoadTypeNum;
import com.online.service.a3roadbureau.RoadTypeNumService;



@Controller("webRoadTypeNumController")
@RequestMapping("/web/roadtypenum")
public class RoadTypeNumController extends BaseController{
	
	@Autowired
	private RoadTypeNumService roadTypeNumService;
	
	
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		return "/admin/a3roadbureau/roadtypenum/list";
	}
	
	
	/**
	 * 增加界面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		return "/admin/a3roadbureau/roadtypenum/add";
	}

	
	
	/**
	 * 修改界面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("roadTypeNum", roadTypeNumService.find(id));
		return "/admin/a3roadbureau/roadtypenum/update";
	}
	
	/**
	 * 查看界面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("roadTypeNum", roadTypeNumService.find(id));
		return "/admin/a3roadbureau/roadtypenum/view";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<RoadTypeNum> query(Pageable pageable,RoadTypeNum roadTypeNum) {
		return roadTypeNumService.findPage(pageable);
		
	}
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(RoadTypeNum roadTypeNum) {
		try {
			roadTypeNumService.saveRoadTypeNum(roadTypeNum);
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
	public Message update(RoadTypeNum roadTypeNum) {
		try {
			roadTypeNumService.updateRoadTypeNum(roadTypeNum);
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
			roadTypeNumService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
