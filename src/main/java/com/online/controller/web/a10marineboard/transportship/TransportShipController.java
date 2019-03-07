package com.online.controller.web.a10marineboard.transportship;


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
import com.online.entity.online.a10marineboard.TransportShip;
import com.online.service.a10marineboard.TransportShipService;
import com.online.util.SpringUtils;



@Controller("webTransportShipController")
@RequestMapping("/web/transportship")
public class TransportShipController extends BaseController{
	
	@Autowired
	private TransportShipService  transportShipService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a10marineboard/transportship/list";
	}
	
	/**
	 * 增加客货运输船舶活动信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		model.put("projectId", SpringUtils.getProjectId());
		return "/admin/a10marineboard/transportship/add";
	}

	/**
	 * 修改客货运输船舶活动信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("projectId", SpringUtils.getProjectId());
		model.put("transportShip", transportShipService.find(id));
		return "/admin/a10marineboard/transportship/update";
	}
	
	/**
	 * 查看客货运输船舶活动信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("transportShip", transportShipService.find(id));
		return "/admin/a10marineboard/transportship/view";
	}
	
	/**
	 * 查询客货运输船舶活动信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<TransportShip> query(Pageable pageable,TransportShip transportShip) {
		pageable.addEntity(transportShip);
		return transportShipService.findPage(pageable);
		
	}
	

	/**
	 * 保存客货运输船舶活动信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(TransportShip  transportShip,Integer enterpriseId) {
	 	try {
			transportShipService.saveTransportShip(transportShip, enterpriseId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新客货运输船舶活动信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(TransportShip transportShip,Integer enterpriseId) {
		try {
			transportShipService.updateTransportShip(transportShip, enterpriseId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除客货运输船舶活动信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 transportShipService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
