package com.online.controller.web.a9agriculture.fertilizerpesticide;


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
import com.online.entity.online.a9agriculture.FertilizerPesticide;
import com.online.service.a9agriculture.FertilizerPesticideService;



@Controller("webFertilizerPesticideController")
@RequestMapping("/web/fertilizerpesticide")
public class FertilizerPesticideController extends BaseController{
	
	@Autowired
	private FertilizerPesticideService  fertilizerPesticideService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a9agriculture/fertilizerpesticide/list";
	}
	
	/**
	 * 增加化肥与农药施用信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a9agriculture/fertilizerpesticide/add";
	}

	/**
	 * 修改化肥与农药施用信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("fertilizerPesticide", fertilizerPesticideService.find(id));
		return "/admin/a9agriculture/fertilizerpesticide/update";
	}
	
	/**
	 * 查看化肥与农药施用信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("fertilizerPesticide", fertilizerPesticideService.find(id));
		return "/admin/a9agriculture/fertilizerpesticide/view";
	}
	
	/**
	 * 查询化肥与农药施用信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<FertilizerPesticide> query(Pageable pageable,FertilizerPesticide fertilizerPesticide) {
		pageable.addEntity(fertilizerPesticide);
		return fertilizerPesticideService.findPage(pageable);
		
	}
	

	/**
	 * 保存化肥与农药施用信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(FertilizerPesticide  fertilizerPesticide) {
	 	try {
			fertilizerPesticideService.saveFertilizerPesticide(fertilizerPesticide);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新化肥与农药施用信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(FertilizerPesticide fertilizerPesticide) {
		try {
			fertilizerPesticideService.updateFertilizerPesticide(fertilizerPesticide);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除化肥与农药施用信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 fertilizerPesticideService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
