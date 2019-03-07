package com.online.controller.web.taskmanager;


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

import com.online.DataAccessException;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Order;
import com.online.Order.Direction;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.TaskManager;
import com.online.excelexport.Export;
import com.online.exception.TaskManagerException;
import com.online.service.TaskManagerService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;



@Controller("webTaskManagerController")
@RequestMapping("/web/taskmanager")
public class TaskManagerController extends BaseController{
	
	@Autowired
	private TaskManagerService  taskManagerService ;
	
	private Export export = new Export();
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/web/taskmanager/list";
	}
	@RequestMapping("/processedlist")
	public String processedlist(){
		return "/web/taskmanager/processedlist";
	}
	
	@RequestMapping("/historylist")
	public String historylist(){
		return "/web/taskmanager/historylist";
	}
	@RequestMapping("/progress")
	public String progress(){
		return "/web/taskmanager/progress";
	}
	@RequestMapping("/viewPage")
	public String viewPage(ModelMap model,Long id){
		model.put("taskManager", taskManagerService.find(id));
		return "/web/taskmanager/viewPage";
	}

	
	@RequestMapping("/reportData")
	public @ResponseBody Message reportData(TaskManager taskManager,Long repeatId,Integer enterpriseId,String ids){
		try {
			String[] idStrs = ids.split(",");
			if(idStrs.length>1){
				for (String id:idStrs) {
					TaskManager taskManagerb = taskManagerService.find(Long.parseLong(id));
					taskManagerb.setEnterpriseStatus(taskManager.getEnterpriseStatus());
					taskManagerService.reportData(taskManagerb, repeatId,enterpriseId,SpringUtils.getUserId());
				}
			}else{
				taskManagerService.reportData(taskManager, repeatId,enterpriseId,SpringUtils.getUserId());
			}
		} catch (TaskManagerException taskManagerException) {
			return Message.error(taskManagerException.getMessage());
		}catch (DataAccessException dataAccessException) {
			return Message.error(dataAccessException.getMessage());
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Message.success("提交成功！");
	}
	
	/**
	 * 查询已分配任务
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<TaskManager> query(Pageable pageable,TaskManager taskManager) {
		
		pageable.addFilter("projectId",Operator.eq,SpringUtils.getProjectId());
		pageable.addFilter("status",Operator.eq,Constants.TASK_STATUS_ASSIGNED);
		pageable.addFilter("executor",Operator.eq,SpringUtils.getPrincipal().getUsername());
		pageable.addOrder(Order.asc("id"));
		String enterpriseName = taskManager.getEnterpriseName();
		if(StringUtils.isNotBlank(enterpriseName)){
			pageable.addFilter("enterpriseName",Operator.like,"%"+enterpriseName+"%");
		}
		
		return taskManagerService.findPage(pageable);
	}
	/**
	 * 查询已处理任务
	 */
	@RequestMapping(value = "/processedquery")
	@ResponseBody
	public Page<TaskManager> processedquery(Pageable pageable) {
		pageable.addFilter("projectId",Operator.eq,SpringUtils.getProjectId());
		pageable.addFilter("status",Operator.eq,Constants.TASK_STATUS_PROCESSED);
		pageable.addFilter("executor",Operator.eq,SpringUtils.getPrincipal().getUsername());
		return taskManagerService.findPage(pageable);
	}

	/**
	 * 查询历史任务
	 */
	@RequestMapping(value = "/historyquery")
	@ResponseBody
	public Page<TaskManager> historyquery(Pageable pageable,TaskManager taskManager) {
		pageable.addOrder("modifyDate", Direction.desc);
		pageable.addFilter("projectId",Operator.eq,SpringUtils.getProjectId());
		pageable.addFilter("status",Operator.ne,Constants.TASK_STATUS_NEW);
		pageable.addFilter("status",Operator.ne,Constants.TASK_STATUS_ASSIGNED);
		pageable.addFilter("executor",Operator.eq,SpringUtils.getPrincipal().getUsername());
		
		String enterpriseStatus = taskManager.getEnterpriseStatus();
		String status = taskManager.getStatus();
		String enterpriseName= taskManager.getEnterpriseName();
		if(StringUtils.isNotBlank(enterpriseName)){
			pageable.addFilter("enterpriseName",Operator.like,"%"+enterpriseName+"%");
		}
		if(StringUtils.isNotBlank(enterpriseStatus)){
			pageable.addFilter("enterpriseStatus",Operator.eq,enterpriseStatus);
		}
		if(StringUtils.isNotBlank(status)){
			pageable.addFilter("status",Operator.eq,status);
		}
		
		return taskManagerService.findPage(pageable);
	}
	/**
	 * 完成任务
	 */
	@RequestMapping(value = "/complete")
	@ResponseBody
	public Message complete(Long taskId) {
		TaskManager taskManager = taskManagerService.find(taskId);
		taskManager.setStatus(Constants.TASK_STATUS_COMPLETE);
		taskManagerService.update(taskManager);
		return Message.success();
	}
		
	/**
	 * 任务进度
	 */
	@RequestMapping(value = "/progressQuery")
	@ResponseBody
	public Message progressQuery() {
		Integer projectId = SpringUtils.getCurrentProject().getId();
		return Message.success(taskManagerService.progressQuery(projectId));
	}
	
	
	/**
	 * 导出名录库数据
	 */
	@RequestMapping(value = "/exportTaskManager", method = RequestMethod.GET)
	public @ResponseBody Message exportEnterpriseDictionary( HttpServletRequest request,
            HttpServletResponse response,TaskManager taskManager) {
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
			
			filters.add(new Filter("projectId",Operator.eq,SpringUtils.getProjectId()));
			filters.add(new Filter("status",Operator.ne,Constants.TASK_STATUS_NEW));
			filters.add(new Filter("status",Operator.ne,Constants.TASK_STATUS_ASSIGNED));
			filters.add(new Filter("executor",Operator.eq,SpringUtils.getPrincipal().getUsername()));
			
			if(taskManager !=  null){
				String enterpriseStatus = taskManager.getEnterpriseStatus();
				String status = taskManager.getStatus();
				String enterpriseName= taskManager.getEnterpriseName();
				if(StringUtils.isNotBlank(enterpriseName)){
					filters.add(new Filter("enterpriseName",Operator.like,"%"+enterpriseName+"%"));
				}
				
				if(StringUtils.isNotBlank(enterpriseStatus)){
					filters.add(new Filter("enterpriseStatus",Operator.eq,enterpriseStatus));
				}
				if(StringUtils.isNotBlank(status)){
					filters.add(new Filter("status",Operator.eq,status));
				}
			}
			
			List<TaskManager> lists = taskManagerService.findList(null, filters, null);
			export.exportTaskManager(os,lists);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return Message.success();
	}
	
	
	
}
