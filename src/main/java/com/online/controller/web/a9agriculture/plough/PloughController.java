package com.online.controller.web.a9agriculture.plough;


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
import com.online.entity.online.a9agriculture.Plough;
import com.online.service.a9agriculture.PloughService;



@Controller("webPloughController")
@RequestMapping("/web/plough")
public class PloughController extends BaseController{
	
	@Autowired
	private PloughService  ploughService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a9agriculture/plough/list";
	}
	
	/**
	 * 增加耕地信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a9agriculture/plough/add";
	}

	/**
	 * 修改耕地信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("plough", ploughService.find(id));
		return "/admin/a9agriculture/plough/update";
	}
	
	/**
	 * 查看耕地信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("plough", ploughService.find(id));
		return "/admin/a9agriculture/plough/view";
	}
	
	/**
	 * 查询耕地信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Plough> query(Pageable pageable,Plough plough) {
		pageable.addEntity(plough);
		return ploughService.findPage(pageable);
		
	}
	

	/**
	 * 保存耕地信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Plough  plough) {
	 	try {
			ploughService.savePlough(plough);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新耕地信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Plough plough) {
		try {
			ploughService.updatePlough(plough);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除耕地信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 ploughService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
