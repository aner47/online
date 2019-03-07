package com.online.controller.web.a13powercompany.enterprisepower;


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
import com.online.entity.online.a13powercompany.EnterprisePower;
import com.online.service.a13powercompany.EnterprisePowerService;
import com.online.util.ExportLog;



@Controller("webEnterprisePowerController")
@RequestMapping("/web/enterprisepower")
public class EnterprisePowerController extends BaseController{
	
	@Autowired
	private EnterprisePowerService  enterprisePowerService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a13powercompany/enterprisepower/list";
	}
	
	/**
	 * 增加企业用电信息表页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a13powercompany/enterprisepower/add";
	}

	/**
	 * 修改企业用电信息表页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("enterprisePower", enterprisePowerService.find(id));
		return "/admin/a13powercompany/enterprisepower/update";
	}
	
	/**
	 * 查看企业用电信息表页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("enterprisePower", enterprisePowerService.find(id));
		return "/admin/a13powercompany/enterprisepower/view";
	}
	
	/**
	 * 查询企业用电信息表
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<EnterprisePower> query(Pageable pageable,EnterprisePower enterprisePower) {
		pageable.addEntity(enterprisePower);
		return enterprisePowerService.findPage(pageable);
		
	}
	

	/**
	 * 保存企业用电信息表
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(EnterprisePower  enterprisePower) {
	 	try {
			enterprisePowerService.saveEnterprisePower(enterprisePower);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新企业用电信息表
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(EnterprisePower enterprisePower) {
		try {
			enterprisePowerService.updateEnterprisePower(enterprisePower);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除企业用电信息表
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 enterprisePowerService.delete(ids[i]);
		}
		return Message.success();
	}
	
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			enterprisePowerService.downloanExcel(response);
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
			exportLog = enterprisePowerService.importFile(filePath);
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
