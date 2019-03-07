package com.online.controller.web.a9agriculture.fishingboatmonth;


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
import com.online.entity.online.a9agriculture.FishingBoatMonth;
import com.online.service.a9agriculture.FishingBoatMonthService;



@Controller("webFishingBoatMonthController")
@RequestMapping("/web/fishingboatmonth")
public class FishingBoatMonthController extends BaseController{
	
	@Autowired
	private FishingBoatMonthService  fishingBoatMonthService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a9agriculture/fishingboatmonth/list";
	}
	
	/**
	 * 增加渔船每月活动水平信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a9agriculture/fishingboatmonth/add";
	}

	/**
	 * 修改渔船每月活动水平信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("fishingBoatMonth", fishingBoatMonthService.find(id));
		return "/admin/a9agriculture/fishingboatmonth/update";
	}
	
	/**
	 * 查看渔船每月活动水平信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("fishingBoatMonth", fishingBoatMonthService.find(id));
		return "/admin/a9agriculture/fishingboatmonth/view";
	}
	
	/**
	 * 查询渔船每月活动水平信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<FishingBoatMonth> query(Pageable pageable,FishingBoatMonth fishingBoatMonth) {
		pageable.addEntity(fishingBoatMonth);
		return fishingBoatMonthService.findPage(pageable);
		
	}
	

	/**
	 * 保存渔船每月活动水平信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(FishingBoatMonth  fishingBoatMonth) {
	 	try {
			fishingBoatMonthService.saveFishingBoatMonth(fishingBoatMonth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新渔船每月活动水平信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(FishingBoatMonth fishingBoatMonth) {
		try {
			fishingBoatMonthService.updateFishingBoatMonth(fishingBoatMonth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除渔船每月活动水平信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 fishingBoatMonthService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
