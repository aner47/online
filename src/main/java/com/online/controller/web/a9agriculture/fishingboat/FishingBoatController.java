package com.online.controller.web.a9agriculture.fishingboat;


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
import com.online.entity.online.a9agriculture.FishingBoat;
import com.online.service.a9agriculture.FishingBoatService;



@Controller("webFishingBoatController")
@RequestMapping("/web/fishingboat")
public class FishingBoatController extends BaseController{
	
	@Autowired
	private FishingBoatService  fishingBoatService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a9agriculture/fishingboat/list";
	}
	
	/**
	 * 增加全市渔船信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a9agriculture/fishingboat/add";
	}

	/**
	 * 修改全市渔船信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("fishingBoat", fishingBoatService.find(id));
		return "/admin/a9agriculture/fishingboat/update";
	}
	
	/**
	 * 查看全市渔船信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("fishingBoat", fishingBoatService.find(id));
		return "/admin/a9agriculture/fishingboat/view";
	}
	
	/**
	 * 查询全市渔船信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<FishingBoat> query(Pageable pageable,FishingBoat fishingBoat) {
		pageable.addEntity(fishingBoat);
		return fishingBoatService.findPage(pageable);
		
	}
	

	/**
	 * 保存全市渔船信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(FishingBoat  fishingBoat) {
	 	try {
			fishingBoatService.saveFishingBoat(fishingBoat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新全市渔船信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(FishingBoat fishingBoat) {
		try {
			fishingBoatService.updateFishingBoat(fishingBoat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除全市渔船信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 fishingBoatService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
