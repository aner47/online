package com.online.controller.web.a11industrialcommercial.incobreakdownservice;


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
import com.online.entity.online.a11industrialcommercial.InCoBreakdownService;
import com.online.service.a11industrialcommercial.InCoBreakdownServiceService;
import com.online.util.ExportLog;



@Controller("webInCoBreakdownServiceController")
@RequestMapping("/web/incobreakdownservice")
public class InCoBreakdownServiceController extends BaseController{
	
	@Autowired
	private InCoBreakdownServiceService  inCoBreakdownServiceService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a11industrialcommercial/incobreakdownservice/list";
	}
	
	/**
	 * 增加汽修企业工商登记信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a11industrialcommercial/incobreakdownservice/add";
	}

	/**
	 * 修改汽修企业工商登记信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("inCoBreakdownService", inCoBreakdownServiceService.find(id));
		return "/admin/a11industrialcommercial/incobreakdownservice/update";
	}
	
	/**
	 * 查看汽修企业工商登记信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("inCoBreakdownService", inCoBreakdownServiceService.find(id));
		return "/admin/a11industrialcommercial/incobreakdownservice/view";
	}
	
	/**
	 * 查询汽修企业工商登记信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<InCoBreakdownService> query(Pageable pageable,InCoBreakdownService inCoBreakdownService) {
		pageable.addEntity(inCoBreakdownService);
		return inCoBreakdownServiceService.findPage(pageable);
		
	}
	

	/**
	 * 保存汽修企业工商登记信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(InCoBreakdownService  inCoBreakdownService) {
	 	try {
			inCoBreakdownServiceService.saveInCoBreakdownService(inCoBreakdownService);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新汽修企业工商登记信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(InCoBreakdownService inCoBreakdownService) {
		try {
			inCoBreakdownServiceService.updateInCoBreakdownService(inCoBreakdownService);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除汽修企业工商登记信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 inCoBreakdownServiceService.delete(ids[i]);
		}
		return Message.success();
	}
	
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			inCoBreakdownServiceService.downloanExcel(response);
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
			exportLog = inCoBreakdownServiceService.importFile(filePath);
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
