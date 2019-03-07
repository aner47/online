package com.online.controller.web.a12ceit.ceitenterprise;


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
import com.online.entity.online.a12ceit.CeitEnterprise;
import com.online.service.a12ceit.CeitEnterpriseService;
import com.online.util.ExportLog;



@Controller("webCeitEnterpriseController")
@RequestMapping("/web/ceitenterprise")
public class CeitEnterpriseController extends BaseController{
	
	@Autowired
	private CeitEnterpriseService  ceitEnterpriseService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a12ceit/ceitenterprise/list";
	}
	
	/**
	 * 增加工业企业信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a12ceit/ceitenterprise/add";
	}

	/**
	 * 修改工业企业信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("ceitEnterprise", ceitEnterpriseService.find(id));
		return "/admin/a12ceit/ceitenterprise/update";
	}
	
	/**
	 * 查看工业企业信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("ceitEnterprise", ceitEnterpriseService.find(id));
		return "/admin/a12ceit/ceitenterprise/view";
	}
	
	/**
	 * 查询工业企业信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CeitEnterprise> query(Pageable pageable,CeitEnterprise ceitEnterprise) {
		pageable.addEntity(ceitEnterprise);
		return ceitEnterpriseService.findPage(pageable);
		
	}
	

	/**
	 * 保存工业企业信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(CeitEnterprise  ceitEnterprise) {
	 	try {
			ceitEnterpriseService.saveCeitEnterprise(ceitEnterprise);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新工业企业信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(CeitEnterprise ceitEnterprise) {
		try {
			ceitEnterpriseService.updateCeitEnterprise(ceitEnterprise);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除工业企业信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 ceitEnterpriseService.delete(ids[i]);
		}
		return Message.success();
	}
	
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			ceitEnterpriseService.downloanExcel(response);
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
			exportLog = ceitEnterpriseService.importFile(filePath);
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
