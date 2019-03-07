package com.online.controller.web.a5businessbureau.petrolgasstation;


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
import com.online.entity.online.a5businessbureau.PetrolGasStation;
import com.online.service.a5businessbureau.PetrolGasStationService;
import com.online.util.ExportLog;



@Controller("webPetrolGasStationController")
@RequestMapping("/web/petrolgasstation")
public class PetrolGasStationController extends BaseController{
	
	@Autowired
	private PetrolGasStationService  petrolGasStationService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a5businessbureau/petrolgasstation/list";
	}
	
	/**
	 * 增加加油站/加气站信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a5businessbureau/petrolgasstation/add";
	}

	/**
	 * 修改加油站/加气站信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("petrolGasStation", petrolGasStationService.find(id));
		return "/admin/a5businessbureau/petrolgasstation/update";
	}
	
	/**
	 * 查看加油站/加气站信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("petrolGasStation", petrolGasStationService.find(id));
		return "/admin/a5businessbureau/petrolgasstation/view";
	}
	
	/**
	 * 查询加油站/加气站信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<PetrolGasStation> query(Pageable pageable,PetrolGasStation petrolGasStation) {
		pageable.addEntity(petrolGasStation);
		return petrolGasStationService.findPage(pageable);
		
	}
	

	/**
	 * 保存加油站/加气站信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(PetrolGasStation  petrolGasStation) {
	 	try {
			petrolGasStationService.savePetrolGasStation(petrolGasStation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新加油站/加气站信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(PetrolGasStation petrolGasStation) {
		try {
			petrolGasStationService.updatePetrolGasStation(petrolGasStation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除加油站/加气站信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 petrolGasStationService.delete(ids[i]);
		}
		return Message.success();
	}
	
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			petrolGasStationService.downloanExcel(response);
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
			exportLog = petrolGasStationService.importFile(filePath);
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
