package com.online.controller.web.a17fuelgas.civilfuelgasmonth;


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
import com.online.entity.online.a17fuelgas.CivilFuelGasMonth;
import com.online.service.a17fuelgas.CivilFuelGasMonthService;



@Controller("webCivilFuelGasMonthController")
@RequestMapping("/web/civilfuelgasmonth")
public class CivilFuelGasMonthController extends BaseController{
	
	@Autowired
	private CivilFuelGasMonthService  civilFuelGasMonthService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a17fuelgas/civilfuelgasmonth/list";
	}
	
	/**
	 * 增加民用燃气消耗分月统计页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a17fuelgas/civilfuelgasmonth/add";
	}

	/**
	 * 修改民用燃气消耗分月统计页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("civilFuelGasMonth", civilFuelGasMonthService.find(id));
		return "/admin/a17fuelgas/civilfuelgasmonth/update";
	}
	
	/**
	 * 查看民用燃气消耗分月统计页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("civilFuelGasMonth", civilFuelGasMonthService.find(id));
		return "/admin/a17fuelgas/civilfuelgasmonth/view";
	}
	
	/**
	 * 查询民用燃气消耗分月统计
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CivilFuelGasMonth> query(Pageable pageable,CivilFuelGasMonth civilFuelGasMonth) {
		pageable.addEntity(civilFuelGasMonth);
		return civilFuelGasMonthService.findPage(pageable);
		
	}
	

	/**
	 * 保存民用燃气消耗分月统计
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(CivilFuelGasMonth  civilFuelGasMonth) {
	 	try {
			civilFuelGasMonthService.saveCivilFuelGasMonth(civilFuelGasMonth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新民用燃气消耗分月统计
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(CivilFuelGasMonth civilFuelGasMonth) {
		try {
			civilFuelGasMonthService.updateCivilFuelGasMonth(civilFuelGasMonth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除民用燃气消耗分月统计
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 civilFuelGasMonthService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
