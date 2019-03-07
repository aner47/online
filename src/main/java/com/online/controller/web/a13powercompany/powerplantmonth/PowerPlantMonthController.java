package com.online.controller.web.a13powercompany.powerplantmonth;


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
import com.online.entity.online.a13powercompany.PowerPlantMonth;
import com.online.service.a13powercompany.PowerPlantMonthService;
import com.online.util.ExportLog;



@Controller("webPowerPlantMonthController")
@RequestMapping("/web/powerplantmonth")
public class PowerPlantMonthController extends BaseController{
	
	@Autowired
	private PowerPlantMonthService  powerPlantMonthService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a13powercompany/powerplantmonth/list";
	}
	
	/**
	 * 增加电厂发电量分月统计信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a13powercompany/powerplantmonth/add";
	}

	/**
	 * 修改电厂发电量分月统计信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("powerPlantMonth", powerPlantMonthService.find(id));
		return "/admin/a13powercompany/powerplantmonth/update";
	}
	
	/**
	 * 查看电厂发电量分月统计信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("powerPlantMonth", powerPlantMonthService.find(id));
		return "/admin/a13powercompany/powerplantmonth/view";
	}
	
	/**
	 * 查询电厂发电量分月统计信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<PowerPlantMonth> query(Pageable pageable,PowerPlantMonth powerPlantMonth) {
		pageable.addEntity(powerPlantMonth);
		return powerPlantMonthService.findPage(pageable);
		
	}
	

	/**
	 * 保存电厂发电量分月统计信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(PowerPlantMonth  powerPlantMonth) {
	 	try {
			powerPlantMonthService.savePowerPlantMonth(powerPlantMonth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新电厂发电量分月统计信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(PowerPlantMonth powerPlantMonth) {
		try {
			powerPlantMonthService.updatePowerPlantMonth(powerPlantMonth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除电厂发电量分月统计信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 powerPlantMonthService.delete(ids[i]);
		}
		return Message.success();
	}
	
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			powerPlantMonthService.downloanExcel(response);
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
			exportLog = powerPlantMonthService.importFile(filePath);
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
