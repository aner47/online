package com.online.controller.web.leakedcheck;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.LeakedCheck;
import com.online.service.EquipmentLeakedService;
import com.online.service.LeakedCheckService;



@Controller("webLeakedCheckController")
@RequestMapping("/web/leakedcheck")
public class LeakedCheckController extends BaseController{
	
	@Autowired
	private LeakedCheckService  leakedCheckService ;
	@Autowired
	private EquipmentLeakedService  equipmentLeakedService ;
	
	
	@RequestMapping("/list")
	public String list(ModelMap model,String equipment_leaked){
		model.put("equipment_leaked", equipment_leaked);
		return "/admin/leakedcheck/list";
	}
	
	/**
	 * 增加设备泄露检测页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model,String equipment_leaked) {
		model.put("equipment_leaked", equipment_leaked);
		return "/admin/leakedcheck/add";
	}

	/**
	 * 修改设备泄露检测页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("leakedCheck", leakedCheckService.find(id));
		return "/admin/leakedcheck/update";
	}
	
	/**
	 * 查看设备泄露检测页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id,String equipment_leaked) {
		model.put("equipment_leaked", equipment_leaked);
		model.put("leakedCheck", leakedCheckService.find(id));
		return "/admin/leakedcheck/view";
	}
	
	/**
	 * 查询设备泄露检测
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<LeakedCheck> query(Pageable pageable,LeakedCheck leakedCheck,String equipment_leaked) {
		pageable.addFilter(new Filter("equipmentLeaked.id", Operator.eq, Integer.parseInt(equipment_leaked)));
		pageable.addEntity(leakedCheck);
		return leakedCheckService.findPage(pageable);
		
	}
	

	/**
	 * 保存设备泄露检测
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(LeakedCheck  leakedCheck,String equipment_leaked) {
		leakedCheck.setEquipmentLeaked(equipmentLeakedService.find(Integer.parseInt(equipment_leaked)));
		 leakedCheckService.save(leakedCheck);
		return Message.success();
	}

	/**
	 * 更新设备泄露检测
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(LeakedCheck leakedCheck) {
		leakedCheckService.update(leakedCheck);
		return Message.success();
	}

	/**
	 * 删除设备泄露检测
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 leakedCheckService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
