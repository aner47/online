package com.online.controller.web.a7trafficpolice.trafficcarweight;


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
import com.online.controller.web.a3roadbureau.businventory.BusInventoryController.ConstructionMachineryFrom;
import com.online.entity.online.a3roadbureau.BusInventory;
import com.online.entity.online.a3roadbureau.CarWeight;
import com.online.entity.online.a7trafficpolice.TrafficCarWeight;
import com.online.service.a7trafficpolice.TrafficCarWeightService;
import com.online.util.SpringUtils;



@Controller("webTrafficCarWeightController")
@RequestMapping("/web/trafficcarweight")
public class TrafficCarWeightController extends BaseController{
	
	@Autowired
	private TrafficCarWeightService  trafficCarWeightService ;
	@RequestMapping("/list")
	public String list(ModelMap model){
		model.put("trafficCarWeight", trafficCarWeightService.findList(null, Arrays.asList(Filter.eq("project.id", SpringUtils.getProjectId())), Arrays.asList(Order.asc("id"))));
		return "/admin/a7trafficpolice/trafficcarweight/list";
	}
	
	/**
	 * 增加车重信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a7trafficpolice/trafficcarweight/add";
	}

	/**
	 * 修改车重信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("trafficCarWeight", trafficCarWeightService.find(id));
		return "/admin/a7trafficpolice/trafficcarweight/update";
	}
	
	/**
	 * 查看车重信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("trafficCarWeight", trafficCarWeightService.find(id));
		return "/admin/a7trafficpolice/trafficcarweight/view";
	}
	
	/**
	 * 查询车重信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<TrafficCarWeight> query(Pageable pageable,TrafficCarWeight trafficCarWeight) {
		pageable.addEntity(trafficCarWeight);
		return trafficCarWeightService.findPage(pageable);
		
	}
	
	
	public static class ConstructionMachineryFrom{
		List<TrafficCarWeight> trafficCarWeights;

		public List<TrafficCarWeight> getTrafficCarWeights() {
			return trafficCarWeights;
		}

		public void setTrafficCarWeights(List<TrafficCarWeight> trafficCarWeights) {
			this.trafficCarWeights = trafficCarWeights;
		}
		
		
	}
	

	/**
	 * 保存车重信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ConstructionMachineryFrom constructionMachineryFrom) {
	 	try {
	 		List<TrafficCarWeight> trafficCarWeightLists =  constructionMachineryFrom.getTrafficCarWeights();
			if(trafficCarWeightLists != null && trafficCarWeightLists.size()>0){
				for(TrafficCarWeight trafficCarWeight:trafficCarWeightLists){
					if(trafficCarWeight.getId() == null){
						trafficCarWeightService.saveTrafficCarWeight(trafficCarWeight);
					}else{
						trafficCarWeightService.updateTrafficCarWeight(trafficCarWeight);
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新车重信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(TrafficCarWeight trafficCarWeight) {
		try {
			trafficCarWeightService.updateTrafficCarWeight(trafficCarWeight);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除车重信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 trafficCarWeightService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
