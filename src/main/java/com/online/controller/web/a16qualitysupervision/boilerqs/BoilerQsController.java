package com.online.controller.web.a16qualitysupervision.boilerqs;


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
import com.online.entity.online.a16qualitysupervision.BoilerQs;
import com.online.service.a16qualitysupervision.BoilerQsService;
import com.online.util.ExportLog;



@Controller("webBoilerQsController")
@RequestMapping("/web/boilerqs")
public class BoilerQsController extends BaseController{
	
	@Autowired
	private BoilerQsService  boilerQsService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a16qualitysupervision/boilerqs/list";
	}
	
	/**
	 * 增加锅炉信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a16qualitysupervision/boilerqs/add";
	}

	/**
	 * 修改锅炉信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("boilerQs", boilerQsService.find(id));
		return "/admin/a16qualitysupervision/boilerqs/update";
	}
	
	/**
	 * 查看锅炉信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("boilerQs", boilerQsService.find(id));
		return "/admin/a16qualitysupervision/boilerqs/view";
	}
	
	/**
	 * 查询锅炉信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<BoilerQs> query(Pageable pageable,BoilerQs boilerQs) {
		pageable.addEntity(boilerQs);
		return boilerQsService.findPage(pageable);
		
	}
	

	/**
	 * 保存锅炉信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(BoilerQs  boilerQs) {
	 	try {
			boilerQsService.saveBoilerQs(boilerQs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新锅炉信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(BoilerQs boilerQs) {
		try {
			boilerQsService.updateBoilerQs(boilerQs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除锅炉信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 boilerQsService.delete(ids[i]);
		}
		return Message.success();
	}
	
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			boilerQsService.downloanExcel(response);
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
			exportLog = boilerQsService.importFile(filePath);
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
