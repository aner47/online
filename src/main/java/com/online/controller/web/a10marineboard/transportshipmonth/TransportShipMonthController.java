package com.online.controller.web.a10marineboard.transportshipmonth;


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
import com.online.entity.online.a10marineboard.TransportShipMonth;
import com.online.service.a10marineboard.TransportShipMonthService;
import com.online.util.SpringUtils;



@Controller("webTransportShipMonthController")
@RequestMapping("/web/transportshipmonth")
public class TransportShipMonthController extends BaseController{
	
	@Autowired
	private TransportShipMonthService  transportShipMonthService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a10marineboard/transportshipmonth/list";
	}
	
	/**
	 * 增加客货运输船舶每月活动水平信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		model.put("projectId", SpringUtils.getProjectId());
		return "/admin/a10marineboard/transportshipmonth/add";
	}

	/**
	 * 修改客货运输船舶每月活动水平信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("projectId", SpringUtils.getProjectId());
		model.put("transportShipMonth", transportShipMonthService.find(id));
		return "/admin/a10marineboard/transportshipmonth/update";
	}
	
	/**
	 * 查看客货运输船舶每月活动水平信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("transportShipMonth", transportShipMonthService.find(id));
		return "/admin/a10marineboard/transportshipmonth/view";
	}
	
	/**
	 * 查询客货运输船舶每月活动水平信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<TransportShipMonth> query(Pageable pageable,TransportShipMonth transportShipMonth) {
		pageable.addEntity(transportShipMonth);
		return transportShipMonthService.findPage(pageable);
		
	}
	

	/**
	 * 保存客货运输船舶每月活动水平信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(TransportShipMonth  transportShipMonth,Integer enterpriseId) {
	 	try {
			transportShipMonthService.saveTransportShipMonth(transportShipMonth, enterpriseId);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		 
		return Message.success();
	}

	/**
	 * 更新客货运输船舶每月活动水平信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(TransportShipMonth transportShipMonth,Integer enterpriseId) {
		try {
			transportShipMonthService.updateTransportShipMonth(transportShipMonth, enterpriseId);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
	}

	/**
	 * 删除客货运输船舶每月活动水平信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 transportShipMonthService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
