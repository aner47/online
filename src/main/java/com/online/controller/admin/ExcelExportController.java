package com.online.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.controller.ScheduleBean;
import com.online.controller.ScheduleSingletonData;
import com.online.controller.base.BaseController;
import com.online.entity.online.Enterprise;
import com.online.entity.online.ProjectModuleInfo;
import com.online.excelexport.ExcelExprotConstants;
import com.online.excelexport.Export;
import com.online.service.ProjectModuleInfoService;
import com.online.service.ProjectTemplatesService;


/**
 * excel导出页面
 * @author DEV2
 *
 */
@Controller
@RequestMapping("/admin/excelexport")
public class ExcelExportController extends BaseController {
	@Autowired
	private Export export;
	@Autowired
	private ProjectTemplatesService projectTemplatesService;
	
	@Autowired
	private ProjectModuleInfoService projectModuleInfoService;
	
	@RequestMapping("list")
	public String exportPage(){
		return "";
	}
	
	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @param projectId
	 * @throws IOException 
	 */
	@RequestMapping(value = "/exportFileValid")
	public @ResponseBody Message exportFileValid(HttpServletRequest request, HttpServletResponse response, Integer projectTemplatesId){
		List<Filter>  filters = new ArrayList<>();
		filters.add(new Filter("projectTemplates.id",Operator.eq,projectTemplatesId));
		List<ProjectModuleInfo> projectModuleInfos = projectModuleInfoService.findList(null, filters, null);
		if(projectModuleInfos!=null&&projectModuleInfos.size()>0){
			Collections.sort(projectModuleInfos,(o1,o2)->o1.getOrder()-o2.getOrder());
			if(!ExcelExprotConstants.MODULE_TYPE_ENTERPRISE.equals(projectModuleInfos.get(0).getModuleType())){
				return	Message.error("导出必须含有【企业模块】并且模块排序位最小");
			}
		}else{
			return Message.error("导出模板没有选择任何导出模块！");
		}
		return Message.success();
		
		
    }
	
	
	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @param projectId
	 * @throws IOException 
	 */
	@RequestMapping(value = "/download")
	public void exportFile(HttpServletRequest request, HttpServletResponse response, Integer projectTemplatesId,String scheduleKey) throws IOException {
        String contentType = "application/octet-stream";
        response.setContentType(contentType);
        // 设置response的头信息
        response.setHeader("Content-disposition", "attachment;filename=\"exports.xlsx\"");
        export.export2ExcelByPorjectId(projectTemplatesService.find(projectTemplatesId), response.getOutputStream(),scheduleKey);
        
		/*List<Enterprise> lists = export.getEnterpriseDataList(null, 4,"NORMAL");
		System.out.println(lists.size());*/
    }
	
	
	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @param projectId
	 * @throws IOException 
	 */
	@RequestMapping(value = "/downloadSchedule")
	public @ResponseBody Message downloadSchedule(String scheduleKey){
		System.out.println("scheduleKey==========="+scheduleKey);
		ScheduleBean query = null;
		try {
			 query = ScheduleSingletonData.query(scheduleKey);
			 System.out.println(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success(query);
    }
	
}
