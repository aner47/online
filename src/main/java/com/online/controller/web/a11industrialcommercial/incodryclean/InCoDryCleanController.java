package com.online.controller.web.a11industrialcommercial.incodryclean;


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
import com.online.entity.online.a11industrialcommercial.InCoDryClean;
import com.online.service.a11industrialcommercial.InCoDryCleanService;
import com.online.util.ExportLog;



@Controller("webInCoDryCleanController")
@RequestMapping("/web/incodryclean")
public class InCoDryCleanController extends BaseController{
	
	@Autowired
	private InCoDryCleanService  inCoDryCleanService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a11industrialcommercial/incodryclean/list";
	}
	
	/**
	 * 增加干洗企业工商登记信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a11industrialcommercial/incodryclean/add";
	}

	/**
	 * 修改干洗企业工商登记信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("inCoDryClean", inCoDryCleanService.find(id));
		return "/admin/a11industrialcommercial/incodryclean/update";
	}
	
	/**
	 * 查看干洗企业工商登记信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("inCoDryClean", inCoDryCleanService.find(id));
		return "/admin/a11industrialcommercial/incodryclean/view";
	}
	
	/**
	 * 查询干洗企业工商登记信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<InCoDryClean> query(Pageable pageable,InCoDryClean inCoDryClean) {
		pageable.addEntity(inCoDryClean);
		return inCoDryCleanService.findPage(pageable);
		
	}
	

	/**
	 * 保存干洗企业工商登记信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(InCoDryClean  inCoDryClean) {
	 	try {
			inCoDryCleanService.saveInCoDryClean(inCoDryClean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新干洗企业工商登记信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(InCoDryClean inCoDryClean) {
		try {
			inCoDryCleanService.updateInCoDryClean(inCoDryClean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除干洗企业工商登记信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 inCoDryCleanService.delete(ids[i]);
		}
		return Message.success();
	}
		
	
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			inCoDryCleanService.downloanExcel(response);
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
			exportLog = inCoDryCleanService.importFile(filePath);
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
