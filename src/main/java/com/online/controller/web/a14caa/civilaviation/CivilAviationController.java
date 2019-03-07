package com.online.controller.web.a14caa.civilaviation;


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
import com.online.entity.online.a14caa.CivilAviation;
import com.online.service.a14caa.CivilAviationService;



@Controller("webCivilAviationController")
@RequestMapping("/web/civilaviation")
public class CivilAviationController extends BaseController{
	
	@Autowired
	private CivilAviationService  civilAviationService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a14caa/civilaviation/list";
	}
	
	@RequestMapping("/addlist")
	public String addlist(Integer airId,ModelMap model){
		model.put("civilAviation", civilAviationService.find(airId));
		return "/admin/a14caa/civilaviation/addlist";
	}
	
	/**
	 * 增加民航飞机信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a14caa/civilaviation/add";
	}

	/**
	 * 修改民航飞机信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("civilAviation", civilAviationService.find(id));
		return "/admin/a14caa/civilaviation/update";
	}
	
	/**
	 * 查看民航飞机信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("civilAviation", civilAviationService.find(id));
		return "/admin/a14caa/civilaviation/view";
	}
	
	/**
	 * 查询民航飞机信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CivilAviation> query(Pageable pageable,CivilAviation civilAviation) {
		pageable.addEntity(civilAviation);
		return civilAviationService.findPage(pageable);
		
	}
	

	/**
	 * 保存民航飞机信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(CivilAviation  civilAviation) {
	 	try {
			civilAviationService.saveCivilAviation(civilAviation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新民航飞机信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(CivilAviation civilAviation) {
		try {
			civilAviationService.updateCivilAviation(civilAviation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}
	/**
	 * 更新民航飞机信息
	 */
	@RequestMapping(value = "/updateAirplanetype", method = RequestMethod.POST)
	@ResponseBody
	public Message updateAirplanetype(CivilAviation civilAviation) {
		try {
			civilAviationService.updateAirplanetype(civilAviation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除民航飞机信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 civilAviationService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
