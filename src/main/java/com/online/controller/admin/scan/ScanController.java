package com.online.controller.admin.scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Message;
import com.online.controller.base.BaseController;
import com.online.pscctask.TaskPsccService;

/** 
 * @author 作者名 
 * @time   2017年5月5日 上午9:59:26 
 */
@Controller("scanController")
@RequestMapping("/admin/scan")
public class ScanController extends BaseController{
	
	@Autowired
	private TaskPsccService taskPsccService;

	@RequestMapping("/list")
	public String list(){
		return "/admin/scan/list";
	}
	@RequestMapping("/scan")
	@ResponseBody
	public Message scan(String type){
		if(taskPsccService.isscan){
			return Message.error("正在扫描", null);
		}else{
			taskPsccService.startScanPsccTask(type);
			return Message.success("开始扫描");
		}
		
		
	}
	
	@RequestMapping("/isScaning")
	@ResponseBody
	public String isScaning(String type){
		
		return taskPsccService.isscan.toString();
		
		
	}
	
}