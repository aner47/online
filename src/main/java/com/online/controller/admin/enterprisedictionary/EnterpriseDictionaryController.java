package com.online.controller.admin.enterprisedictionary;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Order;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.EnterpriseDictionary;
import com.online.excelexport.Export;
import com.online.service.EnterpriseDictionaryService;
import com.online.util.Constants;


@Controller
@RequestMapping("/admin/enterprisedictionary")
public class EnterpriseDictionaryController extends BaseController {
	@Autowired
	private EnterpriseDictionaryService enterpriseDictionaryService;
	
	private Export export = new Export();
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String queryOPage(){
		return "/admin/enterprisedictionary/list";
	}
	
	@RequestMapping(value = "/listrep",method=RequestMethod.GET)
	public String listrep(ModelMap model,Integer repId){
		model.put("repId",repId);
		return "/admin/enterprisedictionary/listRep";
	}
	
	/**
	 * 增加页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(HttpServletRequest request, Pageable pageable) {
		return "/admin/enterprisedictionary/add";
	}

	/**
	 * 修改页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,int id) {
		model.put("enterpriseDictionary", enterpriseDictionaryService.find(id));
		return "/admin/enterprisedictionary/update";
	}
	/**
	 * 审核页面
	 */
	@RequestMapping(value = "checkPage")
	public String checkPage(ModelMap model,int id) {
		model.put("enterpriseDictionary", enterpriseDictionaryService.find(id));
		return "/admin/enterprisedictionary/check";
	}
	/**
	 * 批量审核页面
	 */
	@RequestMapping(value = "batchCheckPage")
	public String batchCheckPage(ModelMap model,String ids) {
		model.put("ids", ids);
		return "/admin/enterprisedictionary/batchCheck";
	}
	
	/**
	 * 批量审核
	 */
	@RequestMapping(value = "/batchCheck", method = RequestMethod.POST)
	public @ResponseBody Message batchCheck(String opinion ,String status,int... ids) {
		for (int i = 0; i < ids.length; i++) {
			EnterpriseDictionary enterpriseDictionary = enterpriseDictionaryService.find(ids[i]);
			enterpriseDictionary.setOpinion(opinion);
			enterpriseDictionary.setStatus(status);
			enterpriseDictionaryService.update(enterpriseDictionary);
		}

		return Message.success();
	}
	
	/**
	 * 加入标准库页面
	 */
	@RequestMapping(value = "insertPage")
	public String insertPage(ModelMap model,String ids) {
		model.put("ids", ids);
		return "/admin/enterprisedictionary/insert";
	}
	
	/**
	 * 查询
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/query")
	public @ResponseBody Page<EnterpriseDictionary> query(Pageable pageable,
			EnterpriseDictionary enterpriseDictionary,Integer repId,String status){
		pageable.addOrder(Order.asc("id"));
		
	    String name = enterpriseDictionary.getEnterpriseName();
	    Integer projectId = enterpriseDictionary.getProjectId();
	    String countyName = enterpriseDictionary.getCountyName();
	    String sign = enterpriseDictionary.getSign();
        if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("enterpriseName", Operator.like, "%"+name.trim()+"%");
        }
        if (projectId != null ) {
        	pageable.addFilter("projectId", Operator.eq, projectId);
        }
        if (StringUtils.isNotEmpty(countyName)) {
        	pageable.addFilter("countyName", Operator.like, "%"+countyName+"%");
        }
        if (StringUtils.isNotEmpty(status)) {
        	pageable.addFilter("status", Operator.eq, status);
        }
        if (repId != null ) {
        	pageable.addFilter("enterpriseDictionaryRep.id", Operator.eq, repId);
        }
        if (StringUtils.isNotEmpty(sign)) {
        	pageable.addFilter("sign", Operator.like, "%"+sign+"%");
        }
		Page<EnterpriseDictionary> findPage = enterpriseDictionaryService.findPage(pageable);
		return findPage;
	}


	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(EnterpriseDictionary enterpriseDictionary) {
		enterpriseDictionary.setStatus(Constants.ENTERPRISE_DICTIONARY_STATUS_NEW);
		enterpriseDictionaryService.saveEntity(enterpriseDictionary);
		return Message.success();
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(EnterpriseDictionary enterpriseDictionary) {
		enterpriseDictionaryService.updateEntity(enterpriseDictionary);
		return Message.success();
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(int... ids) {
		for (int i = 0; i < ids.length; i++) {
			enterpriseDictionaryService.deleteEntity(ids[i]);
		}

		return Message.success();
	}
	
	/**
	 * 审核
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public Message check(EnterpriseDictionary enterpriseDictionary,String status,String opinion) {
		enterpriseDictionary.setStatus(status);
		enterpriseDictionary.setOpinion(opinion);
		enterpriseDictionaryService.update(enterpriseDictionary,"source");
		return Message.success();
	}
	
	/**
	 * 导入任务
	 */
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public @ResponseBody Message export(String filePath,Integer projectId) {
		try {
			enterpriseDictionaryService.exportTask(filePath,projectId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return Message.error(e.getMessage(), null);
		}
		return Message.success();
	}
	
	/**
	 * 下载名录模板
	 */
	@RequestMapping(value = "/downloanExcel", method = RequestMethod.GET)
	public @ResponseBody Message downloanExcel( HttpServletRequest request,
            HttpServletResponse response) {
		//web浏览通过MIME类型判断文件是excel类型
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        String systemtime = df.format(System.currentTimeMillis());   
        
        // 对文件名进行处理。防止文件名乱码
        String filename;
		try {
			filename = new String(("名录库模板").getBytes("GB2312"),"iso8859-1") +"_"+systemtime+".xls";
			response.setHeader("Content-disposition", "attachment;filename=" + filename);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // Content-disposition属性设置成以附件方式进行下载
        
        ServletOutputStream os;
		
        try {
			os = response.getOutputStream();
			export.exportTemplate(os,export.enterpriseDictionaryHeader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return Message.success();
	}
	
	
	/**
	 * 生成任务
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	public Message insert(String ids) {
		try {
			enterpriseDictionaryService.createTaskManager(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return Message.error(e.getMessage(), null);
		}
		return Message.success();
	}
	/**
	 * 插入标准库
	 
	@RequestMapping(value = "/insert")
	@ResponseBody
	public Message insert(String ids,String industryCategoryCodeMain,
			String industryCategoryCodeMiddle, String industryCategoryNameMain
			, String industryCategoryNameMiddle) {
		try {
			enterpriseDictionaryService.saveEnterpriseDictionaryStandard(ids,industryCategoryCodeMain,
					industryCategoryCodeMiddle, industryCategoryNameMain
					, industryCategoryNameMiddle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return Message.error("加入标准库失败", null);
		}
		return Message.success();
	}*/
	/**
	 * 筛选重复
	 */
	@RequestMapping(value = "/filtrate")
	@ResponseBody
	public Message filtrate(EnterpriseDictionary enterpriseDictionary) {
		
		if(enterpriseDictionaryService.filtrate(enterpriseDictionary)){
			return Message.error("计算中", null);
			
		}else{
			return Message.success();
		}
		
	}
	
	/**
	 * 导出名录库数据
	 */
	@RequestMapping(value = "/exportEnterpriseDictionary", method = RequestMethod.GET)
	public @ResponseBody Message exportEnterpriseDictionary( HttpServletRequest request,
            HttpServletResponse response,EnterpriseDictionary enterpriseDictionary) {
		//web浏览通过MIME类型判断文件是excel类型
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        String systemtime = df.format(System.currentTimeMillis());   
        
        // 对文件名进行处理。防止文件名乱码
        String filename;
		try {
			filename = new String(("名录库").getBytes("GB2312"),"iso8859-1") +"_"+systemtime+".xls";
			response.setHeader("Content-disposition", "attachment;filename=" + filename);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // Content-disposition属性设置成以附件方式进行下载
        
        ServletOutputStream os;
		
        try {
			os = response.getOutputStream();
			List<Filter> filters =  new ArrayList<>();	
			if(enterpriseDictionary !=  null){
				String enterpriseName = enterpriseDictionary.getEnterpriseName();
			    Integer projectId = enterpriseDictionary.getProjectId();
			    String countyName = enterpriseDictionary.getCountyName();
			    String sign = enterpriseDictionary.getSign();
			    String status = enterpriseDictionary.getStatus();
			    
				if(projectId != null){
					filters.add(new Filter("projectId", Operator.eq, projectId));
				}
				if(StringUtils.isNotBlank(enterpriseName)){
					filters.add(new Filter("enterpriseName", Operator.eq, enterpriseName));
				}
		        if (StringUtils.isNotEmpty(countyName)) {
		        	filters.add(new Filter("countyName", Operator.like, "%"+countyName+"%"));
		        }
		        if (StringUtils.isNotEmpty(status)) {
		        	filters.add(new Filter("status", Operator.eq, status));
		        }
		        if (StringUtils.isNotEmpty(sign)) {
		        	filters.add(new Filter("sign", Operator.like, "%"+sign+"%"));
		        }
			}
			
			List<EnterpriseDictionary> lists = enterpriseDictionaryService.findList(null, filters, null);
			export.exportTemplate(os,lists);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return Message.success();
	}
	
	
}
