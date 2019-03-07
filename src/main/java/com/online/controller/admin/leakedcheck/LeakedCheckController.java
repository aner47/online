package com.online.controller.admin.leakedcheck;


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
import com.online.entity.online.LeakedCheck;
import com.online.service.LeakedCheckService;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller
@RequestMapping("/admin/leakedcheck")
public class LeakedCheckController extends BaseController{
	
	@Autowired
	private LeakedCheckService  leakedCheckService ;
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/leakedcheck/list";
	}
	
	/**
	 * 增加设备泄露检测页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/leakedcheck/add";
	}

	/**
	 * 修改设备泄露检测页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("leakedCheck", leakedCheckService.find(id));
		return "/admin/leakedcheck/update";
	}
	
	/**
	 * 查看设备泄露检测页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("leakedCheck", leakedCheckService.find(id));
		return "/admin/leakedcheck/view";
	}
	
	/**
	 * 查询设备泄露检测
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<LeakedCheck> query(Pageable pageable,LeakedCheck leakedCheck) {
		pageable.addEntity(leakedCheck);
		return leakedCheckService.findPage(pageable);
		
	}
	

	/**
	 * 保存设备泄露检测
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(LeakedCheck  leakedCheck) {
		 leakedCheckService.save(leakedCheck);
		return Message.success();
	}

	/**
	 * 更新设备泄露检测
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(LeakedCheck leakedCheck) {
		leakedCheckService.update(leakedCheck);
		return Message.success();
	}

	/**
	 * 删除设备泄露检测
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 leakedCheckService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
