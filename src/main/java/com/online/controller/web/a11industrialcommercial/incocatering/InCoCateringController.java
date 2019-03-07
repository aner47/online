package com.online.controller.web.a11industrialcommercial.incocatering;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.online.entity.online.a11industrialcommercial.InCoCatering;
import com.online.service.a11industrialcommercial.InCoCateringService;
import com.online.util.ExportLog;



@Controller("webInCoCateringController")
@RequestMapping("/web/incocatering")
public class InCoCateringController extends BaseController{
	
	@Autowired
	private InCoCateringService  inCoCateringService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a11industrialcommercial/incocatering/list";
	}
	
	/**
	 * 增加餐饮企业工商登记信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a11industrialcommercial/incocatering/add";
	}

	/**
	 * 修改餐饮企业工商登记信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("inCoCatering", inCoCateringService.find(id));
		return "/admin/a11industrialcommercial/incocatering/update";
	}
	
	/**
	 * 查看餐饮企业工商登记信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("inCoCatering", inCoCateringService.find(id));
		return "/admin/a11industrialcommercial/incocatering/view";
	}
	
	/**
	 * 查询餐饮企业工商登记信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<InCoCatering> query(Pageable pageable,InCoCatering inCoCatering) {
		pageable.addEntity(inCoCatering);
		return inCoCateringService.findPage(pageable);
		
	}
	

	/**
	 * 保存餐饮企业工商登记信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(InCoCatering  inCoCatering) {
	 	try {
			inCoCateringService.saveInCoCatering(inCoCatering);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新餐饮企业工商登记信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(InCoCatering inCoCatering) {
		try {
			inCoCateringService.updateInCoCatering(inCoCatering);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除餐饮企业工商登记信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 inCoCateringService.delete(ids[i]);
		}
		return Message.success();
	}
		
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			inCoCateringService.downloanExcel(response);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("下载模板错误");
		}
        
		return Message.success();
		
	}
	
    /**
     * 导入配置
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public @ResponseBody Message importFile(String filePath) {
    	ExportLog exportLog;
		try {
			exportLog = inCoCateringService.importFile(filePath);
			return Message.success(exportLog);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
    }
    
    /**
     * 日志展现
     */
    @RequestMapping(value = "viewLog")
    public String viewPage(ModelMap model, String content) {
    	model.put("content", content);
    	return "/admin/viewLog";
    }
}
