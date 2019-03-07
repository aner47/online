package com.online.controller.web.a14caa.airplanetype;


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
import com.online.entity.online.a14caa.AirplaneType;
import com.online.service.a14caa.AirplaneTypeService;



@Controller("webAirplaneTypeController")
@RequestMapping("/web/airplanetype")
public class AirplaneTypeController extends BaseController{
	
	@Autowired
	private AirplaneTypeService  airplaneTypeService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a14caa/airplanetype/list";
	}
	
	/**
	 * 增加飞机机型页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a14caa/airplanetype/add";
	}

	/**
	 * 修改飞机机型页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("airplaneType", airplaneTypeService.find(id));
		return "/admin/a14caa/airplanetype/update";
	}
	
	/**
	 * 查看飞机机型页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("airplaneType", airplaneTypeService.find(id));
		return "/admin/a14caa/airplanetype/view";
	}
	
	/**
	 * 查询飞机机型
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<AirplaneType> query(Pageable pageable,AirplaneType airplaneType) {
		pageable.addEntity(airplaneType);
		return airplaneTypeService.findPage(pageable);
		
	}
	

	/**
	 * 保存飞机机型
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(AirplaneType  airplaneType) {
	 	try {
			airplaneTypeService.saveAirplaneType(airplaneType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新飞机机型
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(AirplaneType airplaneType) {
		try {
			airplaneTypeService.updateAirplaneType(airplaneType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除飞机机型
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 airplaneTypeService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
