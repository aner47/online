package com.online.controller.web.a10marineboard.portmechanical;


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
import com.online.entity.online.a10marineboard.PortMechanical;
import com.online.service.a10marineboard.PortMechanicalService;
import com.online.util.SpringUtils;



@Controller("webPortMechanicalController")
@RequestMapping("/web/portmechanical")
public class PortMechanicalController extends BaseController{
	
	@Autowired
	private PortMechanicalService  portMechanicalService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a10marineboard/portmechanical/list";
	}
	
	/**
	 * 增加港口机械信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		model.put("projectId", SpringUtils.getProjectId());
		
		return "/admin/a10marineboard/portmechanical/add";
	}

	/**
	 * 修改港口机械信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("projectId", SpringUtils.getProjectId());
		model.put("portMechanical", portMechanicalService.find(id));
		return "/admin/a10marineboard/portmechanical/update";
	}
	
	/**
	 * 查看港口机械信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("portMechanical", portMechanicalService.find(id));
		return "/admin/a10marineboard/portmechanical/view";
	}
	
	/**
	 * 查询港口机械信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<PortMechanical> query(Pageable pageable,PortMechanical portMechanical) {
		pageable.addEntity(portMechanical);
		return portMechanicalService.findPage(pageable);
		
	}
	

	/**
	 * 保存港口机械信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(PortMechanical  portMechanical,Integer enterpriseId) {
	 	try {
			portMechanicalService.savePortMechanical(portMechanical,enterpriseId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新港口机械信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(PortMechanical portMechanical,Integer enterpriseId) {
		try {
			portMechanicalService.updatePortMechanical(portMechanical,enterpriseId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除港口机械信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 portMechanicalService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
