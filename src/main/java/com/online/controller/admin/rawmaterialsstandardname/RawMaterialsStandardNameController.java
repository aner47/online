package com.online.controller.admin.rawmaterialsstandardname;


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
import com.online.entity.online.RawMaterialsStandardName;
import com.online.service.RawMaterialsStandardNameService;



@Controller
@RequestMapping("/admin/rawmaterialsstandardname")
public class RawMaterialsStandardNameController extends BaseController{
	
	@Autowired
	private RawMaterialsStandardNameService  rawMaterialsStandardNameService ;
	@RequestMapping("/list")
	public String list(){
		return "/admin/rawmaterialsstandardname/list";
	}
	
	/**
	 * 增加原辅料标准名称配置页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/rawmaterialsstandardname/add";
	}

	/**
	 * 修改原辅料标准名称配置页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("rawMaterialsStandardName", rawMaterialsStandardNameService.find(id));
		return "/admin/rawmaterialsstandardname/update";
	}
	
	/**
	 * 查看原辅料标准名称配置页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("rawMaterialsStandardName", rawMaterialsStandardNameService.find(id));
		return "/admin/rawmaterialsstandardname/view";
	}
	
	/**
	 * 查询原辅料标准名称配置
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<RawMaterialsStandardName> query(Pageable pageable,RawMaterialsStandardName rawMaterialsStandardName) {
	    String standardName = rawMaterialsStandardName.getStandardName();
        String industryCode = rawMaterialsStandardName.getIndustryCode();
        if (StringUtils.isNotEmpty(standardName)) {
            pageable.addFilter("standardName", Operator.like, "%"+standardName.trim()+"%");
        }
        if (StringUtils.isNotEmpty(industryCode)) {
            pageable.addFilter("industryCode", Operator.like,"%"+industryCode.trim()+"%");
        }
		return rawMaterialsStandardNameService.findPage(pageable);
		
	}
	

	/**
	 * 保存原辅料标准名称配置
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(RawMaterialsStandardName  rawMaterialsStandardName) {
		 rawMaterialsStandardNameService.save(rawMaterialsStandardName);
		return Message.success();
	}

	/**
	 * 更新原辅料标准名称配置
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(RawMaterialsStandardName rawMaterialsStandardName) {
		rawMaterialsStandardNameService.update(rawMaterialsStandardName);
		return Message.success();
	}

	/**
	 * 删除原辅料标准名称配置
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 rawMaterialsStandardNameService.delete(ids[i]);
		}
		return Message.success();
	}
		
}
