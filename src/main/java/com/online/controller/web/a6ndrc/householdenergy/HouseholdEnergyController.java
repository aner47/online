package com.online.controller.web.a6ndrc.householdenergy;


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
import com.online.entity.online.a6ndrc.HouseholdEnergy;
import com.online.service.a6ndrc.HouseholdEnergyService;



@Controller("webHouseholdEnergyController")
@RequestMapping("/web/householdenergy")
public class HouseholdEnergyController extends BaseController{
	
	@Autowired
	private HouseholdEnergyService  householdEnergyService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a6ndrc/householdenergy/list";
	}
	
	/**
	 * 增加居民能源统计信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a6ndrc/householdenergy/add";
	}

	/**
	 * 修改居民能源统计信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("householdEnergy", householdEnergyService.find(id));
		return "/admin/a6ndrc/householdenergy/update";
	}
	
	/**
	 * 查看居民能源统计信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("householdEnergy", householdEnergyService.find(id));
		return "/admin/a6ndrc/householdenergy/view";
	}
	
	/**
	 * 查询居民能源统计信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<HouseholdEnergy> query(Pageable pageable,HouseholdEnergy householdEnergy) {
		pageable.addEntity(householdEnergy);
		return householdEnergyService.findPage(pageable);
		
	}
	

	/**
	 * 保存居民能源统计信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(HouseholdEnergy  householdEnergy) {
	 	try {
			householdEnergyService.saveHouseholdEnergy(householdEnergy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新居民能源统计信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(HouseholdEnergy householdEnergy) {
		try {
			householdEnergyService.updateHouseholdEnergy(householdEnergy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除居民能源统计信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 householdEnergyService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
