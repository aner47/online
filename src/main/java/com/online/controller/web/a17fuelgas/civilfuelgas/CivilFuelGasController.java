package com.online.controller.web.a17fuelgas.civilfuelgas;


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
import com.online.entity.online.a17fuelgas.CivilFuelGas;
import com.online.service.a17fuelgas.CivilFuelGasService;
import com.online.util.SpringUtils;



@Controller("webCivilFuelGasController")
@RequestMapping("/web/civilfuelgas")
public class CivilFuelGasController extends BaseController{
	
	@Autowired
	private CivilFuelGasService  civilFuelGasService ;
	@RequestMapping("/list")
	public String list(ModelMap model){
		model.put("project", SpringUtils.getCurrentProject());
		return "/admin/a17fuelgas/civilfuelgas/list";
	}
	
	/**
	 * 增加民用燃气消耗信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		model.put("project", SpringUtils.getCurrentProject());
		return "/admin/a17fuelgas/civilfuelgas/add";
	}

	/**
	 * 修改民用燃气消耗信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("civilFuelGas", civilFuelGasService.find(id));
		model.put("project", SpringUtils.getCurrentProject());
		return "/admin/a17fuelgas/civilfuelgas/update";
	}
	
	/**
	 * 查看民用燃气消耗信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("civilFuelGas", civilFuelGasService.find(id));
		return "/admin/a17fuelgas/civilfuelgas/view";
	}
	
	/**
	 * 查询民用燃气消耗信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CivilFuelGas> query(Pageable pageable,CivilFuelGas civilFuelGas) {
		pageable.addEntity(civilFuelGas);
		return civilFuelGasService.findPage(pageable);
		
	}
	

	/**
	 * 保存民用燃气消耗信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(CivilFuelGas  civilFuelGas) {
	 	try {
			civilFuelGasService.saveCivilFuelGas(civilFuelGas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新民用燃气消耗信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(CivilFuelGas civilFuelGas) {
		try {
			civilFuelGasService.updateCivilFuelGas(civilFuelGas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除民用燃气消耗信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 civilFuelGasService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
