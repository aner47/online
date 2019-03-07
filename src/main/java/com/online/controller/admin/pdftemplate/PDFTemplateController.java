package com.online.controller.admin.pdftemplate;


import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.DataAccessException;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.PDFTemplateFile;
import com.online.service.PDFTemplateService;



@Controller
@RequestMapping("/admin/pdftemplate")
public class PDFTemplateController extends BaseController{
	
	static List<String> imgSuffix = Arrays.asList("PDF");
	
	@Autowired
	private PDFTemplateService pdfTemplateService;
	
	
	@RequestMapping("/list")
	public String list(){
		return "/admin/pdftemplate/list";
	}
	
	@RequestMapping("/auditlist")
	public String auditlist(){
		return "/admin/audit/list";
	}
	
	/**
	 * 增加pdf模块信息页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/pdftemplate/add";
	}
	/**
	 * 增加pdf模块信息页面
	 */
	@RequestMapping(value = "addauditPage")
	public String addauditPage() {
		return "/admin/audit/add";
	}

	/**
	 * 修改pdf模块信息页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("pdfTemplateFile", pdfTemplateService.find(id));
		return "/admin/pdftemplate/update";
	}
	/**
	 * 修改pdf模块信息页面
	 */
	@RequestMapping(value = "updateauditPage")
	public String updateauditPage(ModelMap model,Integer id) {
		model.put("pdfTemplateFile", pdfTemplateService.find(id));
		return "/admin/audit/update";
	}
	
	/**
	 * 查看pdf模块信息页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("pdfTemplateFile", pdfTemplateService.find(id));
		return "/admin/pdftemplate/view";
	}
	
	/**
	 * 查询pdf模块信息
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<PDFTemplateFile> query(Pageable pageable,PDFTemplateFile pdfTemplateFile) {
	    
	    String projectModelName = pdfTemplateFile.getProjectModelName();
		String enterpriseType = pdfTemplateFile.getEnterpriseType();
		String projectTemplateName = pdfTemplateFile.getProjectTemplateName();
		Integer projectId = pdfTemplateFile.getProjectId();
		Integer projectTypeId = pdfTemplateFile.getProjectTypeId();
		String  pdftemplateName = pdfTemplateFile.getPdftemplateName();
		
		if (projectId != null) {
            pageable.addFilter("projectId", Operator.eq, projectId);
        }
		if (projectTypeId != null) {
			pageable.addFilter("projectTypeId", Operator.eq, projectTypeId);
		}
		if (StringUtils.isNotEmpty(projectModelName)) {
			pageable.addFilter("projectModelName", Operator.eq, projectModelName);
		}
		if (StringUtils.isNotEmpty(enterpriseType)) {
            pageable.addFilter("enterpriseType", Operator.eq, enterpriseType);
        }
		if (StringUtils.isNotEmpty(projectTemplateName)) {
            pageable.addFilter("projectTemplateName", Operator.eq, projectTemplateName);
        }
		if (StringUtils.isNotEmpty(pdftemplateName)) {
			pageable.addFilter("pdftemplateName", Operator.eq, pdftemplateName);
		}else{
			pageable.addFilter(Filter.isNull("pdftemplateName"));
		}
		
		return pdfTemplateService.findPage(pageable);
		
	}
	

	/**
	 * 保存pdf模块信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(PDFTemplateFile  pdfTemplateFile) {
		
		try {
			pdfTemplateService.savePDFTemplateFile(pdfTemplateFile);
		} catch (DataAccessException e) {
			return Message.error(e.getMessage(), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Message.success();
	}

	/**
	 * 更新pdf模块信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(PDFTemplateFile pdfTemplateFile) {
		try {
			pdfTemplateService.updatePDFTemplateFile(pdfTemplateFile);
		} catch (DataAccessException e) {
			return Message.error(e.getMessage(), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Message.success();
	}

	/**
	 * 删除pdf模块信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			pdfTemplateService.delete(ids[i]);
			 
		}
		return Message.success();
	}
	
	/**
	 * 上传文件
	 */
	@RequestMapping(value = "/uploadfile")
	@ResponseBody
	@Transactional
	public Message uploadfile(PDFTemplateFile pdfTemplateFile,HttpServletRequest request,String url,String filename,String realPath) {
		String suffix = filename.substring(filename.lastIndexOf(".")+1);
		if(imgSuffix.contains(suffix.toUpperCase())){
			PDFTemplateFile pdfTemplateFileback = pdfTemplateService.find(pdfTemplateFile.getId());
			pdfTemplateFileback.setUrl(realPath);
			pdfTemplateFileback.setFilename(filename);
			pdfTemplateService.update(pdfTemplateFileback);
		}else{
			return Message.error("上传文件格式错误！");
		}
		return Message.success();
    }
	
}
