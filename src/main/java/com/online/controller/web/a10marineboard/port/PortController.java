package com.online.controller.web.a10marineboard.port;


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
import com.online.entity.online.a10marineboard.Port;
import com.online.service.a10marineboard.PortService;



@Controller("webPortController")
@RequestMapping("/web/port")
public class PortController extends BaseController{
	
	@Autowired
	private PortService  portService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a10marineboard/port/list";
	}
	
	/**
	 * 增加港口信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a10marineboard/port/add";
	}

	/**
	 * 修改港口信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("port", portService.find(id));
		return "/admin/a10marineboard/port/update";
	}
	
	/**
	 * 查看港口信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("port", portService.find(id));
		return "/admin/a10marineboard/port/view";
	}
	
	/**
	 * 查询港口信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Port> query(Pageable pageable,Port port) {
		pageable.addEntity(port);
		return portService.findPage(pageable);
		
	}
	

	/**
	 * 保存港口信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Port  port) {
	 	try {
			portService.savePort(port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新港口信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Port port) {
		try {
			portService.updatePort(port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除港口信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 portService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
