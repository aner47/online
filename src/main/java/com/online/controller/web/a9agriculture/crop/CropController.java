package com.online.controller.web.a9agriculture.crop;


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
import com.online.entity.online.a9agriculture.Crop;
import com.online.service.a9agriculture.CropService;



@Controller("webCropController")
@RequestMapping("/web/crop")
public class CropController extends BaseController{
	
	@Autowired
	private CropService  cropService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/a9agriculture/crop/list";
	}
	
	/**
	 * 增加农作物产量及秸秆利用页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/a9agriculture/crop/add";
	}

	/**
	 * 修改农作物产量及秸秆利用页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("crop", cropService.find(id));
		return "/admin/a9agriculture/crop/update";
	}
	
	/**
	 * 查看农作物产量及秸秆利用页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("crop", cropService.find(id));
		return "/admin/a9agriculture/crop/view";
	}
	
	/**
	 * 查询农作物产量及秸秆利用
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<Crop> query(Pageable pageable,Crop crop) {
		pageable.addEntity(crop);
		return cropService.findPage(pageable);
		
	}
	

	/**
	 * 保存农作物产量及秸秆利用
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Crop  crop) {
	 	try {
			cropService.saveCrop(crop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return Message.success();
	}

	/**
	 * 更新农作物产量及秸秆利用
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Crop crop) {
		try {
			cropService.updateCrop(crop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 删除农作物产量及秸秆利用
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 cropService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
