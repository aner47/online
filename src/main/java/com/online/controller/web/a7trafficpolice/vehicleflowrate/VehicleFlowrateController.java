package com.online.controller.web.a7trafficpolice.vehicleflowrate;


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
import com.online.entity.online.a7trafficpolice.VehicleFlowrate;
import com.online.service.a7trafficpolice.VehicleFlowrateService;



@Controller("webVehicleFlowrateController")
@RequestMapping("/web/vehicleflowrate")
public class VehicleFlowrateController extends BaseController{
	
	@Autowired
	private VehicleFlowrateService  vehicleFlowrateService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a7trafficpolice/vehicleflowrate/list";
	}
	
	/**
	 * 增加车流量信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a7trafficpolice/vehicleflowrate/add";
	}

	/**
	 * 修改车流量信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("vehicleFlowrate", vehicleFlowrateService.find(id));
		return "/admin/a7trafficpolice/vehicleflowrate/update";
	}
	
	/**
	 * 查看车流量信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("vehicleFlowrate", vehicleFlowrateService.find(id));
		return "/admin/a7trafficpolice/vehicleflowrate/view";
	}
	
	/**
	 * 查询车流量信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<VehicleFlowrate> query(Pageable pageable,VehicleFlowrate vehicleFlowrate) {
		pageable.addEntity(vehicleFlowrate);
		return vehicleFlowrateService.findPage(pageable);
		
	}
	

	/**
	 * 保存车流量信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(VehicleFlowrate  vehicleFlowrate) {
	 	try {
			vehicleFlowrateService.saveVehicleFlowrate(vehicleFlowrate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新车流量信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(VehicleFlowrate vehicleFlowrate) {
		try {
			vehicleFlowrateService.updateVehicleFlowrate(vehicleFlowrate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除车流量信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 vehicleFlowrateService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
