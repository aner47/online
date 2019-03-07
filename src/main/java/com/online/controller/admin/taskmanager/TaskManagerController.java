package com.online.controller.admin.taskmanager;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
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
import com.online.Order;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.Condition;
import com.online.entity.online.TaskManager;
import com.online.excelexport.Export;
import com.online.service.TaskManagerService;
import com.online.util.Constants;



@Controller
@RequestMapping("/admin/taskmanager")
public class TaskManagerController extends BaseController{
	
	@Autowired
	private TaskManagerService  taskManagerService ;
	
	private Export export = new Export();
	
	@RequestMapping("/list")
	public String list(){
		return "/admin/taskmanager/list";
	}
	
	@RequestMapping("/statistics")
	public String statistics(){
		return "/admin/taskmanager/statistics";
	}
	
	
	/**
	 * 填报统计
	 */
	@RequestMapping(value = "/statisticsQuery")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> statisticsQuery(Condition condition,Integer projectId,Date start,Date end) {
		
		
		
		Map<String, List<Map<String, Object>>> returnMap = new HashMap<>();
		
		Condition areacondition = new Condition();
		
		returnMap.put("county_name", taskManagerService.findStatistics(projectId,1, start, end));
		returnMap.put("alreadyCreateEnterprise", taskManagerService.findStatistics(projectId,2, start, end));
		returnMap.put("unCreateEnterprise", taskManagerService.findStatistics(projectId,3, start, end));
		
		
		return returnMap;
	}
	
	
	
	
	/**
	 * 增加任务管理页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/taskmanager/add";
	}
	/**
	 * 分配任务页面
	 */
	@RequestMapping(value = "distributionPage")
	public String distributionPage(ModelMap model,String ids,String projectId) {
		model.put("ids", ids);
		model.put("projectId", projectId);
		return "/admin/taskmanager/distributionPage";
	}

	/**
	 * 修改任务管理页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Long id) {
		model.put("taskManager", taskManagerService.find(id));
		return "/admin/taskmanager/update";
	}
	/**
	 * 修改任务管理页面
	 */
	@RequestMapping(value = "updateNamePage")
	public String updateNamePage(ModelMap model,Long id) {
		model.put("taskManager", taskManagerService.find(id));
		return "/admin/taskmanager/updateName";
	}
	
	/**
	 * 查看任务管理页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Long id) {
		model.put("taskManager", taskManagerService.find(id));
		return "/admin/taskmanager/view";
	}
	
	/**
	 * 查询任务管理
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<TaskManager> query(Pageable pageable,Integer projectId,String status,String executor,
			String countyName,String inputIndustry,TaskManager taskManager) {
		pageable.addOrder(Order.asc("id"));
		
		pageable.addFilter("projectId",Operator.eq,projectId);
		pageable.addFilter("status",Operator.eq,status);
		pageable.addFilter("executor",Operator.eq,executor);
		
		String enterpriseName = taskManager.getEnterpriseName();
		
		if(StringUtils.isNotBlank(enterpriseName)){
			pageable.addFilter("enterpriseName",Operator.like,"%"+enterpriseName.trim()+"%");
		}
		if(StringUtils.isNotBlank(countyName)){
			pageable.addFilter("countyName",Operator.like,"%"+countyName.trim()+"%");
		}
		if(StringUtils.isNotBlank(countyName)){
			pageable.addFilter("countyName",Operator.like,"%"+countyName.trim()+"%");
		}
		if(StringUtils.isNotBlank(inputIndustry)){
			pageable.addFilter("inputIndustry",Operator.like,"%"+inputIndustry.trim()+"%");
		}
		
		
		return taskManagerService.findPage(pageable);
	}
	

	/**
	 * 保存任务管理
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(TaskManager  taskManager) {
		 taskManagerService.save(taskManager);
		return Message.success();
	}

	/**
	 * 更新任务管理
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(TaskManager taskManager) {
		if(Constants.TASK_STATUS_NEW.equals(taskManager.getStatus())){
			taskManagerService.update(taskManager);
			return Message.success();
		}else{
			return Message.error("已分配不能修改", null);
		}
		
		
	}
	/**
	 * 更新企业名称
	 */
	@RequestMapping(value = "/updateName", method = RequestMethod.POST)
	@ResponseBody
	public Message updateName(TaskManager taskManager) {
		
		
		taskManagerService.updateName(taskManager); 
		
		
		return Message.success();
		
	}

	/**
	 * 删除任务管理
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Long... ids) {
		for (int i = 0; i < ids.length; i++) {
			 taskManagerService.deleteTaskManager(ids[i]);
		}
		return Message.success();
	}
	
	
	/**
	 * 导入任务
	 */
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public @ResponseBody Message export(String filePath,Integer projectId) {
		taskManagerService.exportTask(filePath, projectId);
		return Message.success();
	}
	
	/**
	 * 分配任务管理
	 */
	@RequestMapping(value = "/distribution", method = RequestMethod.POST)
	@ResponseBody
	public Message distribution(String ids,String user,String enterpriseType,Integer projectTypeId) {
		try {
			taskManagerService.distribution(ids,user,enterpriseType,projectTypeId);
		} catch (DataAccessException  e) {
			return Message.error(e.getMessage(), null);
		}catch(ConstraintViolationException  e){
			return Message.error("分配错误，企业用户已存在", null);
		}catch(Exception  e){
			e.printStackTrace();
			return Message.error("分配错误", null);
		}
		return Message.success();
	}
	/**
	 * 创建用户并分配任务
	 */
	@RequestMapping(value = "/createEnterpriseUser", method = RequestMethod.POST)
	@ResponseBody
	public Message createEnterpriseUser(String ids,String enterpriseType,Integer projectTypeId) {
		try {
			taskManagerService.createEnterpriseUser(ids,enterpriseType,projectTypeId);
		}catch (DataAccessException  e) {
			return Message.error(e.getMessage(), null);
		}catch(ConstraintViolationException |PersistenceException e){
			return Message.error("分配错误，企业用户已存在", null);
		}catch(Exception  e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Message.error("分配错误", null);
		}
		return Message.success();
	}
	
	/**
	 * 导出任务
	 */
	@RequestMapping(value = "/exportTaskManager", method = RequestMethod.GET)
	public @ResponseBody Message exportTaskManager( HttpServletRequest request,
            HttpServletResponse response,Integer projectId,String status,String executor,
			String countyName,String inputIndustry,TaskManager taskManager) {
		//web浏览通过MIME类型判断文件是excel类型
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        String systemtime = df.format(System.currentTimeMillis());   
        
        // 对文件名进行处理。防止文件名乱码
        String filename;
		try {
			filename = new String(("任务").getBytes("GB2312"),"iso8859-1") +"_"+systemtime+".xls";
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
			
				if(projectId != null){
					filters.add(new Filter("projectId",Operator.eq,projectId));
				}
				if(StringUtils.isNotBlank(status)){
					filters.add(new Filter("status",Operator.eq,status));
				}
				if(StringUtils.isNotBlank(executor)){
					filters.add(new Filter("executor",Operator.eq,executor));
				}
				
				
				
				if(taskManager !=  null){
				String enterpriseName = taskManager.getEnterpriseName();
				
				if(StringUtils.isNotBlank(enterpriseName)){
					filters.add(new Filter("enterpriseName",Operator.like,"%"+enterpriseName.trim()+"%"));
				}
				if(StringUtils.isNotBlank(countyName)){
					filters.add(new Filter("countyName",Operator.like,"%"+countyName.trim()+"%"));
				}
				if(StringUtils.isNotBlank(countyName)){
					filters.add(new Filter("countyName",Operator.like,"%"+countyName.trim()+"%"));
				}
				if(StringUtils.isNotBlank(inputIndustry)){
					filters.add(new Filter("inputIndustry",Operator.like,"%"+inputIndustry.trim()+"%"));
				}
			}
			
			List<TaskManager> lists = taskManagerService.findList(null, filters, null);
			export.exportTaskManager(os, lists);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return Message.success();
	}
	

		
}
