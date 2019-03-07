package com.online.controller.web.a17fuelgas.enterprisefuelgasmonth;


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
import com.online.entity.online.a17fuelgas.EnterpriseFuelGasMonth;
import com.online.service.a17fuelgas.EnterpriseFuelGasMonthService;
import com.online.util.ExportLog;



@Controller("webEnterpriseFuelGasMonthController")
@RequestMapping("/web/enterprisefuelgasmonth")
public class EnterpriseFuelGasMonthController extends BaseController{
	
	@Autowired
	private EnterpriseFuelGasMonthService  enterpriseFuelGasMonthService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a17fuelgas/enterprisefuelgasmonth/list";
	}
	
	/**
	 * 增加企业燃气消耗分月统计页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a17fuelgas/enterprisefuelgasmonth/add";
	}

	/**
	 * 修改企业燃气消耗分月统计页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("enterpriseFuelGasMonth", enterpriseFuelGasMonthService.find(id));
		return "/admin/a17fuelgas/enterprisefuelgasmonth/update";
	}
	
	/**
	 * 查看企业燃气消耗分月统计页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("enterpriseFuelGasMonth", enterpriseFuelGasMonthService.find(id));
		return "/admin/a17fuelgas/enterprisefuelgasmonth/view";
	}
	
	/**
	 * 查询企业燃气消耗分月统计
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<EnterpriseFuelGasMonth> query(Pageable pageable,EnterpriseFuelGasMonth enterpriseFuelGasMonth) {
		pageable.addEntity(enterpriseFuelGasMonth);
		return enterpriseFuelGasMonthService.findPage(pageable);
		
	}
	

	/**
	 * 保存企业燃气消耗分月统计
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(EnterpriseFuelGasMonth  enterpriseFuelGasMonth) {
	 	try {
			enterpriseFuelGasMonthService.saveEnterpriseFuelGasMonth(enterpriseFuelGasMonth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新企业燃气消耗分月统计
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(EnterpriseFuelGasMonth enterpriseFuelGasMonth) {
		try {
			enterpriseFuelGasMonthService.updateEnterpriseFuelGasMonth(enterpriseFuelGasMonth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除企业燃气消耗分月统计
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 enterpriseFuelGasMonthService.delete(ids[i]);
		}
		return Message.success();
	}
	
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			enterpriseFuelGasMonthService.downloanExcel(response);
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
			exportLog = enterpriseFuelGasMonthService.importFile(filePath);
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
