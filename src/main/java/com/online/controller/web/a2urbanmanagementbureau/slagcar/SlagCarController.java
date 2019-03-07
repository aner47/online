package com.online.controller.web.a2urbanmanagementbureau.slagcar;


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
import com.online.entity.online.a2urbanmanagementbureau.SlagCar;
import com.online.service.a2urbanmanagementbureau.SlagCarService;



@Controller("webSlagCarController")
@RequestMapping("/web/slagcar")
public class SlagCarController extends BaseController{
	
	@Autowired
	private SlagCarService slagCarService;
	
	
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		return "/admin/a2urbanmanagementbureau/slagcar/list";
	}
	
	
	/**
	 * 增加界面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		return "/admin/a2urbanmanagementbureau/slagcar/add";
	}

	
	
	/**
	 * 修改界面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("slagCar", slagCarService.find(id));
		return "/admin/a2urbanmanagementbureau/slagcar/update";
	}
	
	/**
	 * 查看界面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("slagCar", slagCarService.find(id));
		return "/admin/a2urbanmanagementbureau/slagcar/view";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<SlagCar> query(Pageable pageable,SlagCar slagCar) {
		return slagCarService.findPage(pageable);
		
	}
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(SlagCar slagCar) {
		try {
			slagCarService.saveSlagCar(slagCar);
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
	public Message update(SlagCar slagCar) {
		try {
			slagCarService.updateSlagCar(slagCar);
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
			slagCarService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
