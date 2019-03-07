package com.online.controller.web.a9agriculture.agriculturalmachinery;


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
import com.online.entity.online.a9agriculture.AgriculturalMachinery;
import com.online.service.a9agriculture.AgriculturalMachineryService;



@Controller("webAgriculturalMachineryController")
@RequestMapping("/web/agriculturalmachinery")
public class AgriculturalMachineryController extends BaseController{
	
	@Autowired
	private AgriculturalMachineryService  agriculturalMachineryService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a9agriculture/agriculturalmachinery/list";
	}
	
	/**
	 * 增加农业机械信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a9agriculture/agriculturalmachinery/add";
	}

	/**
	 * 修改农业机械信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("agriculturalMachinery", agriculturalMachineryService.find(id));
		return "/admin/a9agriculture/agriculturalmachinery/update";
	}
	
	/**
	 * 查看农业机械信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("agriculturalMachinery", agriculturalMachineryService.find(id));
		return "/admin/a9agriculture/agriculturalmachinery/view";
	}
	
	/**
	 * 查询农业机械信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<AgriculturalMachinery> query(Pageable pageable,AgriculturalMachinery agriculturalMachinery) {
		pageable.addEntity(agriculturalMachinery);
		return agriculturalMachineryService.findPage(pageable);
		
	}
	

	/**
	 * 保存农业机械信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(AgriculturalMachinery  agriculturalMachinery) {
	 	try {
			agriculturalMachineryService.saveAgriculturalMachinery(agriculturalMachinery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新农业机械信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(AgriculturalMachinery agriculturalMachinery) {
		try {
			agriculturalMachineryService.updateAgriculturalMachinery(agriculturalMachinery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除农业机械信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 agriculturalMachineryService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
