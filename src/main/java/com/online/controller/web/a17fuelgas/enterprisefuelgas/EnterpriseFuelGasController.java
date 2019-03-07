package com.online.controller.web.a17fuelgas.enterprisefuelgas;


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
import com.online.entity.online.a17fuelgas.EnterpriseFuelGas;
import com.online.service.a17fuelgas.EnterpriseFuelGasService;
import com.online.util.ExportLog;
import com.online.util.SpringUtils;



@Controller("webEnterpriseFuelGasController")
@RequestMapping("/web/enterprisefuelgas")
public class EnterpriseFuelGasController extends BaseController{
	
	@Autowired
	private EnterpriseFuelGasService  enterpriseFuelGasService ;
	@RequestMapping("/list")
	public String list(ModelMap model){
		model.put("project", SpringUtils.getCurrentProject());
		return "/admin/a17fuelgas/enterprisefuelgas/list";
	}
	
	/**
	 * 增加工业企业燃气消耗信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		model.put("project", SpringUtils.getCurrentProject());
		return "/admin/a17fuelgas/enterprisefuelgas/add";
	}

	/**
	 * 修改工业企业燃气消耗信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("enterpriseFuelGas", enterpriseFuelGasService.find(id));
		model.put("project", SpringUtils.getCurrentProject());
		return "/admin/a17fuelgas/enterprisefuelgas/update";
	}
	
	/**
	 * 查看工业企业燃气消耗信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("enterpriseFuelGas", enterpriseFuelGasService.find(id));
		return "/admin/a17fuelgas/enterprisefuelgas/view";
	}
	
	/**
	 * 查询工业企业燃气消耗信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<EnterpriseFuelGas> query(Pageable pageable,EnterpriseFuelGas enterpriseFuelGas) {
		pageable.addEntity(enterpriseFuelGas);
		return enterpriseFuelGasService.findPage(pageable);
		
	}
	

	/**
	 * 保存工业企业燃气消耗信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(EnterpriseFuelGas  enterpriseFuelGas) {
	 	try {
			enterpriseFuelGasService.saveEnterpriseFuelGas(enterpriseFuelGas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新工业企业燃气消耗信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(EnterpriseFuelGas enterpriseFuelGas) {
		try {
			enterpriseFuelGasService.updateEnterpriseFuelGas(enterpriseFuelGas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除工业企业燃气消耗信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 enterpriseFuelGasService.delete(ids[i]);
		}
		return Message.success();
	}
	
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			enterpriseFuelGasService.downloanExcel(response);
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
			exportLog = enterpriseFuelGasService.importFile(filePath);
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
