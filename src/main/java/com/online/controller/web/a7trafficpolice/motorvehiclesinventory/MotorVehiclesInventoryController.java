package com.online.controller.web.a7trafficpolice.motorvehiclesinventory;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Message;
import com.online.Order;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.a7trafficpolice.MotorVehiclesInventory;
import com.online.service.a7trafficpolice.MotorVehiclesInventoryService;
import com.online.util.JsonUtil;
import com.online.util.SpringUtils;



@Controller("webMotorVehiclesInventoryController")
@RequestMapping("/web/motorvehiclesinventory")
public class MotorVehiclesInventoryController extends BaseController{
	
	@Autowired
	private MotorVehiclesInventoryService  motorVehiclesInventoryService;
	@RequestMapping("/list")
	public String list(ModelMap model){
		List<MotorVehiclesInventory> lists = motorVehiclesInventoryService.findList(null, Arrays.asList(Filter.eq("project.id", SpringUtils.getProjectId())), Arrays.asList(Order.asc("id")));
		model.put("motorVehiclesInventory", JsonUtil.toJSON(lists));
		return "/admin/a7trafficpolice/motorvehiclesinventory/list";
	}
	
	/**
	 * 增加机动车保有量信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a7trafficpolice/motorvehiclesinventory/add";
	}

	/**
	 * 修改机动车保有量信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("motorVehiclesInventory", motorVehiclesInventoryService.find(id));
		return "/admin/a7trafficpolice/motorvehiclesinventory/update";
	}
	
	/**
	 * 查看机动车保有量信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("motorVehiclesInventory", motorVehiclesInventoryService.find(id));
		return "/admin/a7trafficpolice/motorvehiclesinventory/view";
	}
	
	/**
	 * 查询机动车保有量信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<MotorVehiclesInventory> query(Pageable pageable,MotorVehiclesInventory motorVehiclesInventory) {
		pageable.addEntity(motorVehiclesInventory);
		return motorVehiclesInventoryService.findPage(pageable);
		
	}
	
	public static class MotorVehiclesInventoryFrom{
		List<MotorVehiclesInventory> motorVehiclesInventorys;

		public List<MotorVehiclesInventory> getMotorVehiclesInventorys() {
			return motorVehiclesInventorys;
		}

		public void setMotorVehiclesInventorys(List<MotorVehiclesInventory> motorVehiclesInventorys) {
			this.motorVehiclesInventorys = motorVehiclesInventorys;
		}
		
	}

	/**
	 * 保存机动车保有量信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(MotorVehiclesInventoryFrom  motorVehiclesInventoryFrom) {
	 	try {
	 		List<MotorVehiclesInventory> lists = motorVehiclesInventoryFrom.getMotorVehiclesInventorys();
			if(lists != null && lists.size()>0){
				for(MotorVehiclesInventory motorVehiclesInventory:lists){
					if(motorVehiclesInventory.getId() == null){
						motorVehiclesInventoryService.saveMotorVehiclesInventory(motorVehiclesInventory);
					}else{
						motorVehiclesInventoryService.updateMotorVehiclesInventory(motorVehiclesInventory);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新机动车保有量信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(MotorVehiclesInventory motorVehiclesInventory) {
		try {
			motorVehiclesInventoryService.updateMotorVehiclesInventory(motorVehiclesInventory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除机动车保有量信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 motorVehiclesInventoryService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
