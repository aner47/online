package com.online.controller.web.a2urbanmanagementbureau.catering;


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
import com.online.entity.online.a2urbanmanagementbureau.Catering;
import com.online.service.a2urbanmanagementbureau.CateringService;
import com.online.util.ExportLog;



@Controller("webCateringController")
@RequestMapping("/web/catering")
public class CateringController extends BaseController{
	
	@Autowired
	private CateringService cateringService;
	
	
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		return "/admin/a2urbanmanagementbureau/catering/list";
	}
	
	
	/**
	 * 增加界面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		return "/admin/a2urbanmanagementbureau/catering/add";
	}

	
	
	/**
	 * 修改界面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("catering", cateringService.find(id));
		return "/admin/a2urbanmanagementbureau/catering/update";
	}
	
	/**
	 * 查看界面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("catering", cateringService.find(id));
		return "/admin/a2urbanmanagementbureau/catering/view";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Catering> query(Pageable pageable,Catering catering) {
		return cateringService.findPage(pageable);
		
	}
	

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Catering catering) {
		try {
			cateringService.saveCatering(catering);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		return Message.success();
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Catering catering) {
		try {
			cateringService.updateCatering(catering);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		return Message.success();
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			cateringService.delete(ids[i]);
		}
		return Message.success();
	}
	
	
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			cateringService.downloanExcel(response);
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
			exportLog = cateringService.importFile(filePath);
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
