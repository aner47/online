package com.online.controller.admin.rawmeterialsunitconfig;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.Filter.Operator;
import com.online.controller.base.BaseController;
import com.online.entity.online.RawMaterialsOtherName;
import com.online.entity.online.RawMeterialsUnitConfig;
import com.online.service.RawMaterialsOtherNameService;
import com.online.service.RawMeterialsUnitConfigService;



@Controller
@RequestMapping("/admin/rawmeterialsunitconfig")
public class RawMeterialsUnitConfigController extends BaseController{
	
	@Autowired
	private RawMeterialsUnitConfigService  rawMeterialsUnitConfigService ;
	@Autowired
	private RawMaterialsOtherNameService rawMaterialsOtherNameService;
	@RequestMapping("/list")
	public String list(){
		return "/admin/rawmeterialsunitconfig/list";
	}
	
	/**
	 * 增加原辅料单位配置页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/rawmeterialsunitconfig/add";
	}

	/**
	 * 修改原辅料单位配置页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("rawMeterialsUnitConfig", rawMeterialsUnitConfigService.find(id));
		return "/admin/rawmeterialsunitconfig/update";
	}
	
	/**
	 * 查看原辅料单位配置页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("rawMeterialsUnitConfig", rawMeterialsUnitConfigService.find(id));
		return "/admin/rawmeterialsunitconfig/view";
	}
	
	/**
	 * 查询原辅料单位配置
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<RawMeterialsUnitConfig> query(Pageable pageable,RawMeterialsUnitConfig rawMeterialsUnitConfig,String otherName) {
        if (StringUtils.isNotEmpty(otherName)) {
            pageable.addFilter("rawMaterialsOtherName.otherName", Operator.like, "%" + otherName.trim() + "%");
        }
		return rawMeterialsUnitConfigService.findPage(pageable);
		
	}
	

	/**
	 * 保存原辅料单位配置
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(RawMeterialsUnitConfig  rawMeterialsUnitConfig,String po) {
	    if (StringUtils.isNotEmpty(po)) {
	        RawMaterialsOtherName otherName = rawMaterialsOtherNameService.find(Integer.parseInt(po));
	        rawMeterialsUnitConfig.setRawMaterialsOtherName(otherName);
        }
		 rawMeterialsUnitConfigService.save(rawMeterialsUnitConfig);
		return Message.success();
	}

	/**
	 * 更新原辅料单位配置
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(RawMeterialsUnitConfig rawMeterialsUnitConfig,String po) {
		if (StringUtils.isNotEmpty(po)) {
	        RawMaterialsOtherName otherName = rawMaterialsOtherNameService.find(Integer.parseInt(po));
	        rawMeterialsUnitConfig.setRawMaterialsOtherName(otherName);
        }
		rawMeterialsUnitConfigService.update(rawMeterialsUnitConfig);
		return Message.success();
	}

	/**
	 * 删除原辅料单位配置
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 rawMeterialsUnitConfigService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
