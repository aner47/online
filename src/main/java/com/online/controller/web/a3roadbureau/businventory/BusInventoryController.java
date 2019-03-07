package com.online.controller.web.a3roadbureau.businventory;


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
import com.online.entity.online.a3roadbureau.BusInventory;
import com.online.entity.online.a3roadbureau.CarWeight;
import com.online.service.a3roadbureau.BusInventoryService;
import com.online.service.a3roadbureau.CarWeightService;
import com.online.util.SpringUtils;



@Controller("webBusInventoryController")
@RequestMapping("/web/businventory")
public class BusInventoryController extends BaseController{
	
	@Autowired
	private BusInventoryService busInventoryService;
	@Autowired
	private CarWeightService carWeightService;
	
	
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		model.put("constructionMachinery", busInventoryService.findList(null, Arrays.asList(Filter.eq("project.id", SpringUtils.getProjectId())), Arrays.asList(Order.asc("id"))));
		model.put("carWeight", carWeightService.findList(null, Arrays.asList(Filter.eq("project.id", SpringUtils.getProjectId())), Arrays.asList(Order.asc("id"))));
		return "/admin/a3roadbureau/businventory/list";
	}
	
	
	/**
	 * 增加界面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		return "/admin/a3roadbureau/businventory/add";
	}

	
	
	/**
	 * 修改界面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("busInventory", busInventoryService.find(id));
		return "/admin/a3roadbureau/businventory/update";
	}
	
	/**
	 * 查看界面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("busInventory", busInventoryService.find(id));
		return "/admin/a3roadbureau/businventory/view";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<BusInventory> query(Pageable pageable,BusInventory busInventory) {
		return busInventoryService.findPage(pageable);
		
	}
	
	
	public static class ConstructionMachineryFrom{
		List<BusInventory> constructionMachinerys;
		List<CarWeight> carWeights;

		public List<BusInventory> getConstructionMachinerys() {
			return constructionMachinerys;
		}

		public void setConstructionMachinerys(List<BusInventory> constructionMachinerys) {
			this.constructionMachinerys = constructionMachinerys;
		}

		public List<CarWeight> getCarWeights() {
			return carWeights;
		}

		public void setCarWeights(List<CarWeight> carWeights) {
			this.carWeights = carWeights;
		}
		
		
		
		
	}
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ConstructionMachineryFrom constructionMachineryFrom) {
		try {
			
			List<BusInventory> lists = constructionMachineryFrom.getConstructionMachinerys();
			if(lists != null && lists.size()>0){
				for(BusInventory busInventory:lists){
					if(busInventory.getId() == null){
						busInventoryService.saveBusInventory(busInventory);
					}else{
						busInventoryService.updateBusInventory(busInventory);
					}
				}
			}
			
			List<CarWeight> carWeightLists =  constructionMachineryFrom.getCarWeights();
			if(carWeightLists != null && carWeightLists.size()>0){
				for(CarWeight carWeight:carWeightLists){
					if(carWeight.getId() == null){
						carWeightService.saveCarWeight(carWeight);
					}else{
						carWeightService.updateCarWeight(carWeight);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("保存失败");
		}
		return Message.success();
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(BusInventory busInventory) {
		try {
			busInventoryService.updateBusInventory(busInventory);
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
			busInventoryService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
