package com.online.controller.web.a9agriculture.livestockbreeding;


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
import com.online.entity.online.a9agriculture.LivestockBreeding;
import com.online.service.a9agriculture.LivestockBreedingService;
import com.online.util.ExportLog;



@Controller("webLivestockBreedingController")
@RequestMapping("/web/livestockbreeding")
public class LivestockBreedingController extends BaseController{
	
	@Autowired
	private LivestockBreedingService  livestockBreedingService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a9agriculture/livestockbreeding/list";
	}
	
	/**
	 * 增加畜禽养殖信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a9agriculture/livestockbreeding/add";
	}

	/**
	 * 修改畜禽养殖信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("livestockBreeding", livestockBreedingService.find(id));
		return "/admin/a9agriculture/livestockbreeding/update";
	}
	
	/**
	 * 查看畜禽养殖信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("livestockBreeding", livestockBreedingService.find(id));
		return "/admin/a9agriculture/livestockbreeding/view";
	}
	
	/**
	 * 查询畜禽养殖信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<LivestockBreeding> query(Pageable pageable,LivestockBreeding livestockBreeding) {
		pageable.addEntity(livestockBreeding);
		return livestockBreedingService.findPage(pageable);
		
	}
	

	/**
	 * 保存畜禽养殖信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(LivestockBreeding  livestockBreeding) {
	 	try {
			livestockBreedingService.saveLivestockBreeding(livestockBreeding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新畜禽养殖信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(LivestockBreeding livestockBreeding) {
		try {
			livestockBreedingService.updateLivestockBreeding(livestockBreeding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除畜禽养殖信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 livestockBreedingService.delete(ids[i]);
		}
		return Message.success();
	}
		
	/**
	 * 下载
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel(HttpServletResponse response) {
		
		try {
			livestockBreedingService.downloanExcel(response);
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
			exportLog = livestockBreedingService.importFile(filePath);
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
