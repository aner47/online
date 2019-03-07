package com.online.controller.admin.rawmaterialsothername;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.RawMaterialsOtherName;
import com.online.entity.online.RawMaterialsStandardName;
import com.online.service.RawMaterialsOtherNameService;
import com.online.service.RawMaterialsStandardNameService;



@Controller
@RequestMapping("/admin/rawmaterialsothername")
public class RawMaterialsOtherNameController extends BaseController{
	
	@Autowired
	private RawMaterialsOtherNameService  rawMaterialsOtherNameService ;
	@Autowired
	private RawMaterialsStandardNameService rawMaterialsStandardNameService;
	@RequestMapping("/list")
	public String list(){
		return "/admin/rawmaterialsothername/list";
	}
	
	/**
	 * 增加原辅料名称配置页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/rawmaterialsothername/add";
	}

	/**
	 * 修改原辅料名称配置页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("rawMaterialsOtherName", rawMaterialsOtherNameService.find(id));
		return "/admin/rawmaterialsothername/update";
	}
	
	/**
	 * 查看原辅料名称配置页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("rawMaterialsOtherName", rawMaterialsOtherNameService.find(id));
		return "/admin/rawmaterialsothername/view";
	}
	
	/**
	 * 查询原辅料名称配置
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<RawMaterialsOtherName> query(Pageable pageable,RawMaterialsOtherName rawMaterialsOtherName) {
	    String otherName = rawMaterialsOtherName.getOtherName();
        String industryCode = rawMaterialsOtherName.getIndustryCode();
        if (StringUtils.isNotEmpty(otherName)) {
            pageable.addFilter("otherName", Operator.like, "%"+otherName.trim()+"%");
        }
        if (StringUtils.isNotEmpty(industryCode)) {
            pageable.addFilter("industryCode", Operator.like,"%"+industryCode.trim()+"%");
        }
		return rawMaterialsOtherNameService.findPage(pageable);
		
	}
	

	/**
	 * 保存原辅料名称配置
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(RawMaterialsOtherName  rawMaterialsOtherName,String pds) {
	    if (StringUtils.isNotEmpty(pds)) {
	        RawMaterialsStandardName standardName = rawMaterialsStandardNameService.find(Integer.parseInt(pds));
	        rawMaterialsOtherName.setRawMaterialsStandardName(standardName);
        }
		 rawMaterialsOtherNameService.save(rawMaterialsOtherName);
		return Message.success();
	}

	/**
	 * 更新原辅料名称配置
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(RawMaterialsOtherName rawMaterialsOtherName,String pds) {
		if (StringUtils.isNotEmpty(pds)) {
	        RawMaterialsStandardName standardName = rawMaterialsStandardNameService.find(Integer.parseInt(pds));
	        rawMaterialsOtherName.setRawMaterialsStandardName(standardName);
        }
		rawMaterialsOtherNameService.update(rawMaterialsOtherName);
		return Message.success();
	}

	/**
	 * 删除原辅料名称配置
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 rawMaterialsOtherNameService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
