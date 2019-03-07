package com.online.controller.admin.tasklist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.FilterGroup.JoinChar;
import com.online.FilterGroup;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.SystemRole;
import com.online.entity.SystemUser;
import com.online.entity.online.PsccStatusTaskManage;
import com.online.entity.online.TaskHistory;
import com.online.entity.online.TaskList;
import com.online.entity.online.TaskListBase.TaskStatus;
import com.online.pscctask.TaskPsccService;
import com.online.service.ProjectEnterpriseUserService;
import com.online.service.PsccStatusTaskService;
import com.online.service.TaskHistoryService;
import com.online.service.TaskListService;
import com.online.util.Constants;
import com.online.util.SpringUtils;

@Controller
@RequestMapping("/admin/tasklist")
public class TaskListController extends BaseController {

    @Autowired
    private TaskListService taskListService;
    
    @Autowired
    private TaskHistoryService taskHistoryService;
    
    @Autowired
    private TaskPsccService taskPsccService;
    
    
    

    /**
     * 待分配任务
     */
    @RequestMapping("/list0")
    public String list0(ModelMap model) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
    	model.put("currentUser", currentSystemUser.getRoles().iterator().next().getName());
        return "/admin/tasklist/list0";
    }

    /**
     * 待分配任务
     */
    @RequestMapping(value = "/query0")
    @ResponseBody
    public Page<TaskList> query0(Pageable pageable, TaskList taskList) {
        SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();

        Set<SystemRole> roles = currentSystemUser.getRoles();
        for (Iterator<SystemRole> iterator = roles.iterator(); iterator.hasNext();) {
            SystemRole systemRole = iterator.next();
            // 分配员
            if (Constants.TASK_DISTRIBUTE.equals(systemRole.getName())) {
                pageable.addFilter(new Filter("taskAccount", Operator.eq, null));
            }
        }

        pageable.addFilter(new Filter("status", Operator.eq, TaskStatus.assigned));
        pageable.addEntity(taskList);
        return taskListService.findPage(pageable);

    }
    
    /**
     * 已分配任务
     */
    @RequestMapping("/list0_already")
    public String list0_already(ModelMap model) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
    	model.put("currentUser", currentSystemUser.getRoles().iterator().next().getName());
    	return "/admin/tasklist/list0_already";
    }
    
    /**
     * 已分配任务
     */
    @RequestMapping(value = "/query0_already")
    @ResponseBody
    public Page<TaskList> query0_already(Pageable pageable, TaskList taskList) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
    	
    	
    	pageable.addFilter(new Filter("taskAccount", Operator.eq, currentSystemUser.getId().toString()));
    	pageable.addEntity(taskList);
    	return taskListService.findPage(pageable);
    	
    }
    
    
    /**
     * 分配任务页面
     * 
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "distributionTask")
    public String distributionTask(ModelMap model, @RequestParam("id") Integer ids) {
        model.put("taskList", taskListService.find(ids));
        return "/admin/tasklist/distributionTask";
    }

    /**
     * 分配到处理人
     * 
     * @param taskList
     * @return
     */
    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    @ResponseBody
    public Message distribution(TaskList taskList, Integer... ids) {
    	
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
        if (ids != null) {
            for (Integer taskId : ids) {
            	TaskList taskListback=  taskListService.designate(taskId, currentSystemUser.getId().toString(), taskList.getDisposeAccount());
            	taskHistoryService.insertEntery(taskListback, "分配",currentSystemUser.getId().toString(),taskList.getDisposeAccount());
            }
        }
        return Message.success();
    }
    

    /**
     * 待处理任务
     */
    @RequestMapping("/list1")
    public String list1(ModelMap model) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
    	model.put("currentUser", currentSystemUser);
    	model.put("currentUserRoles", currentSystemUser.getRoles());
        return "/admin/tasklist/list1";
    }

    /**
     * 待处理任务
     */
    @RequestMapping(value = "/query1")
    @ResponseBody
    public Page<TaskList> query1(Pageable pageable, TaskList taskList) {
        SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();

        pageable.addFilter(new Filter("disposeAccount", Operator.eq, currentSystemUser.getId().toString()));
    	pageable.addFilter(new Filter("status", Operator.eq, TaskStatus.active));
    	pageable.addEntity(taskList);
    	return taskListService.findPage(pageable);
//        Set<SystemRole> roles = currentSystemUser.getRoles();
//        
//        
//        List<Filter> filters = new ArrayList<>();
//        for (Iterator<SystemRole> iterator = roles.iterator(); iterator.hasNext();) {
//            SystemRole systemRole = iterator.next();
//            // 分配员
//            if (Constants.TASK_DISTRIBUTE.equals(systemRole.getName())) {
//            	filters.add(new Filter("taskAccount", Operator.eq, currentSystemUser.getId().toString()));
//            } else if (Constants.TASK_DISPOSE.equals(systemRole.getName())) {
//            	filters.add(new Filter("disposeAccount", Operator.eq, currentSystemUser.getId().toString()));
//            } 
//        }
//        
//        FilterGroup filterGroup = new FilterGroup();
//        filterGroup.setJoinChar(JoinChar.and);
//        filterGroup.addFilter(new Filter("status", Operator.eq, TaskStatus.active));
//        
//        FilterGroup filterGroup1 = new FilterGroup(filters);
//        filterGroup1.setJoinChar(JoinChar.or);
//        
//        filterGroup.addFilterGroup(filterGroup1);
//        //pageable.addEntity(taskList);
//        
//        List<TaskList> lists = taskListService.findComplexFilter(0, null, filterGroup, null);
//        return new Page<>(taskListService.findComplexFilter((pageable.getPageNumber()-1)*10, 10, filterGroup, null), lists.size(), pageable);
//        
        

    }
    /**
     * 已处理任务
     */
    @RequestMapping("/list1_already")
    public String list1_already(ModelMap model) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
    	model.put("currentUser", currentSystemUser);
    	model.put("currentUserRoles", currentSystemUser.getRoles());
    	return "/admin/tasklist/list1_already";
    }
    
    /**
     * 已处理任务
     */
    @RequestMapping(value = "/query1_already")
    @ResponseBody
    public Page<TaskList> query1_already(Pageable pageable, TaskList taskList) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
    	
    	pageable.addFilter(new Filter("disposeAccount", Operator.eq, currentSystemUser.getId().toString()));
    	pageable.addFilter(new Filter("status", Operator.ne, TaskStatus.active));
    	pageable.addEntity(taskList);
    	return taskListService.findPage(pageable);
    	
    	
    	
    }
    
    
    /**
     * 待处理修改页面
     */
    @RequestMapping(value = "updatePage")
    public String updatePage(ModelMap model, Integer id) {
        TaskList task = taskListService.find(id);
        model.put("taskList", task);
        return "/admin/tasklist/update";
    }
    
    
    /**
     * 修改待处理任务
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Message update(TaskList taskList) {
    	taskList.setIsCompletion(Constants.TASK_LIST_Completion);
    	TaskList task =  taskListService.updateEntery(taskList);
//	    taskHistoryService.insertEntery(task, "编辑");
        return Message.success();
    }
    
    
    /**
     * 选择审核员页面
     * 
     */
    @RequestMapping(value = "auditorPage")
    public String AuditorPage(ModelMap model, Integer id) {
        model.put("taskList", taskListService.find(id));
        return "/admin/tasklist/auditorPage";
    }
    
    
    /**
     * 分配复核员
     * 
     * @param taskList
     * @return
     */
    @RequestMapping(value = "/auditor", method = RequestMethod.POST)
    @ResponseBody
    public Message auditor(TaskList taskList, Integer... ids) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
        if (ids != null) {
        	for (Integer taskId : ids) {
        		if(!taskListService.isCompletion(taskId)){
        			return Message.error("数据不完整", null);
        		}
        	}
            for (Integer taskId : ids) {
            	
            	TaskList  taskListback = taskListService.designateCheck(taskId, currentSystemUser.getId().toString(), taskList.getCheckAccount());
            	taskHistoryService.insertEntery(taskListback, "指定到审核",currentSystemUser.getId().toString(),taskList.getCheckAccount());
            }
        }
        return Message.success();
    }
    
    
    
    
    
    
    
    
    
    
    

    /**
     * 待审核任务
     */
    @RequestMapping("/list2")
    public String list2(ModelMap model) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
    	model.put("currentUser", currentSystemUser);
    	model.put("currentUserRoles", currentSystemUser.getRoles());
        return "/admin/tasklist/list2";
    }

    /**
     * 待审核任务
     */
    @RequestMapping(value = "/query2")
    @ResponseBody
    public Page<TaskList> query2(Pageable pageable, TaskList taskList) {
        SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();

        pageable.addFilter(new Filter("checkAccount", Operator.eq, currentSystemUser.getId().toString()));
    	pageable.addFilter(new Filter("status", Operator.eq, TaskStatus.pendingreview));
    	pageable.addEntity(taskList);
    	return taskListService.findPage(pageable);
    	
//        Set<SystemRole> roles = currentSystemUser.getRoles();
//
//        List<Filter> filters = new ArrayList<>();
//        for (Iterator<SystemRole> iterator = roles.iterator(); iterator.hasNext();) {
//            SystemRole systemRole = iterator.next();
//            // 分配员
//            if (Constants.TASK_DISTRIBUTE.equals(systemRole.getName())) {
//               // pageable.addFilter(new Filter("taskAccount", Operator.eq, currentSystemUser.getId().toString()));
//            	filters.add(new Filter("taskAccount", Operator.eq, currentSystemUser.getId().toString()));
//            } else if (Constants.TASK_DISPOSE.equals(systemRole.getName())) {
////                pageable.addFilter(new Filter("disposeAccount", Operator.eq, currentSystemUser.getId().toString()));
//            	filters.add(new Filter("disposeAccount", Operator.eq, currentSystemUser.getId().toString()));
//            } else  if (Constants.TASK_CHECK.equals(systemRole.getName())) {
////                pageable.addFilter(new Filter("checkAccount", Operator.eq, currentSystemUser.getId().toString()));
//            	filters.add(new Filter("checkAccount", Operator.eq, currentSystemUser.getId().toString()));
//            }
//        }
//        //filters.add(new Filter("status", Operator.eq, TaskStatus.pendingreview));
//        
//        FilterGroup filterGroup = new FilterGroup();
//        filterGroup.setJoinChar(JoinChar.and);
//        filterGroup.addFilter(new Filter("status", Operator.eq, TaskStatus.pendingreview));
//        
//        FilterGroup filterGroup1 = new FilterGroup(filters);
//        filterGroup1.setJoinChar(JoinChar.or);
//        
//        
//        filterGroup.addFilterGroup(filterGroup1);
////      
//        
//        List<TaskList> lists = taskListService.findComplexFilter(0, null, filterGroup, null);
//        return new Page<>(taskListService.findComplexFilter((pageable.getPageNumber()-1)*10, 10, filterGroup, null), lists.size(), pageable);

    }
    /**
     * 已审核任务
     */
    @RequestMapping("/list2_already")
    public String list2_already(ModelMap model) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
    	model.put("currentUser", currentSystemUser);
    	model.put("currentUserRoles", currentSystemUser.getRoles());
    	return "/admin/tasklist/list2_already";
    }
    
    /**
     * 已审核任务
     */
    @RequestMapping(value = "/query2_already")
    @ResponseBody
    public Page<TaskList> query2_already(Pageable pageable, TaskList taskList) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
    	pageable.addFilter(new Filter("checkAccount", Operator.eq, currentSystemUser.getId().toString()));
    	pageable.addFilter(new Filter("status", Operator.ne, TaskStatus.pendingreview));
    	pageable.addEntity(taskList);
    	return taskListService.findPage(pageable);
    	
    }
    
    
    /**
     * 选择复核员
     * 
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "chooseReviewTask")
    public String chooseReviewPage(ModelMap model, Integer id) {
        model.put("taskList", taskListService.find(id));
        return "/admin/tasklist/chooseReviewTask";
    }

    /**
     * 审核
     * 
     * @param model
     * @param status
     * @param ids
     * @return
     */
    @RequestMapping(value = "auditOpinion")
    @ResponseBody
    @Transactional
    public Message auditOpinion(String status, String description, Integer... ids) {
    	
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
        // 如果状态是通过，则完成数据修改
        if (Objects.equals(status, Constants.TASK_PASS)) {
            for (Integer id : ids) {
            	TaskList  taskListback = taskListService.checkPass(id, currentSystemUser.getId().toString(),description);
            	taskHistoryService.insertEntery(taskListback, "审核通过",currentSystemUser.getId().toString(),"");
            	taskPsccService.insertConfig(taskListback);
            	
            }
        } else {
            for (Integer id : ids) {
                TaskList  taskListback = taskListService.checkNoPass(id, currentSystemUser.getId().toString(),description);
            	taskHistoryService.insertEntery(taskListback, "审核不通过",currentSystemUser.getId().toString(),taskListback.getDisposeAccount());
            }
        }
        return Message.success();
    }
    
    
    
    
    
    
    
    

    /**
     * 已结束任务
     */
    @RequestMapping("/list3")
    public String list3(ModelMap model) {
    	SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
    	model.put("currentUser", currentSystemUser.getRoles().iterator().next().getName());
        return "/admin/tasklist/list3";
    }

    /**
     * 已结束任务
     */
    @RequestMapping(value = "/query3")
    @ResponseBody
    public Page<TaskList> query3(Pageable pageable, TaskList taskList) {
        SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
        Set<SystemRole> roles = currentSystemUser.getRoles();
       /* for (Iterator<SystemRole> iterator = roles.iterator(); iterator.hasNext();) {
            SystemRole systemRole = iterator.next();
            // 分配员
            if (Constants.TASK_DISTRIBUTE.equals(systemRole.getName())) {
                pageable.addFilter(new Filter("taskAccount", Operator.eq, currentSystemUser.getId().toString()));
            } else if (Constants.TASK_DISPOSE.equals(systemRole.getName())) {
                pageable.addFilter(new Filter("disposeAccount", Operator.eq, currentSystemUser.getId().toString()));
            } else if (Constants.TASK_CHECK.equals(systemRole.getName())) {
                pageable.addFilter(new Filter("checkAccount", Operator.eq, currentSystemUser.getId().toString()));
            }
        }
        pageable.addFilter(new Filter("status", Operator.eq, TaskStatus.Audited));
        pageable.addEntity(taskList);
        return taskListService.findPage(pageable);*/
        
        List<Filter> filters = new ArrayList<>();
        for (Iterator<SystemRole> iterator = roles.iterator(); iterator.hasNext();) {
            SystemRole systemRole = iterator.next();
            // 分配员
            if (Constants.TASK_DISTRIBUTE.equals(systemRole.getName())) {
            	filters.add(new Filter("taskAccount", Operator.eq, currentSystemUser.getId().toString()));
            } else if (Constants.TASK_DISPOSE.equals(systemRole.getName())) {
            	filters.add(new Filter("disposeAccount", Operator.eq, currentSystemUser.getId().toString()));
            } else  if (Constants.TASK_CHECK.equals(systemRole.getName())) {
            	filters.add(new Filter("checkAccount", Operator.eq, currentSystemUser.getId().toString()));
            }
        }
        
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.setJoinChar(JoinChar.and);
        filterGroup.addFilter(new Filter("status", Operator.eq, TaskStatus.Audited));
        
        FilterGroup filterGroup1 = new FilterGroup(filters);
        filterGroup1.setJoinChar(JoinChar.or);
        
        filterGroup.addFilterGroup(filterGroup1);
        
        List<TaskList> lists = taskListService.findComplexFilter(0, null, filterGroup, null);
        return new Page<>(taskListService.findComplexFilter((pageable.getPageNumber()-1)*10, 10, filterGroup, null), lists.size(), pageable);

    }
    
    
    
    
    
    
    
    
    
    

    /**
     * 增加每日任务清单页面
     */
    @RequestMapping(value = "addPage")
    public String addPage() {
        return "/admin/tasklist/add";
    }
    

    

    /**
     * 查询每日任务审核页面
     */
    @RequestMapping(value = "/queryAudit")
    @ResponseBody
    public Page<TaskList> queryAudit(Pageable pageable, TaskList taskList) {
        SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
        if (currentSystemUser != null) {
            Integer id = currentSystemUser.getId();
        }
        pageable.addEntity(taskList);
        return taskListService.findPage(pageable);

    }

    /**
     * 查询每日任务处理
     */
    @RequestMapping(value = "/queryTaskProcess")
    @ResponseBody
    public Page<TaskList> queryTaskProcess(Pageable pageable, TaskList taskList) {
        SystemUser currentSystemUser = SpringUtils.getCurrentSystemUser();
        if (currentSystemUser != null) {
            Integer id = currentSystemUser.getId();
        }
        pageable.addEntity(taskList);
        return taskListService.findPage(pageable);

    }

    /**
     * 保存每日任务清单
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Message save(TaskList taskList) {
        taskListService.save(taskList);
        return Message.success();
    }

    

    /**
     * 删除每日任务清单
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Message delete(Integer... ids) {
        for (int i = 0; i < ids.length; i++) {
            taskListService.delete(ids[i]);
        }
        return Message.success();
    }

    

    

    /**
     * 任务处理
     * 
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "taskAccept")
    public String taskAccept(ModelMap model, Integer id) {
        model.put("taskList", taskListService.find(id));
        return "/admin/tasklist/taskAccept";
    }
    

    
    
    
    
    
    
    
    
    
    

    
    /**
     * 查看历史
     */
    @RequestMapping(value = "taskhistory")
    public String taskhistory(ModelMap model,String id) {
    	model.put("id", id);
    	return "/admin/tasklist/taskhistory";
    }
    
    
    /**
     * 查看历史
     *
     */
    @RequestMapping(value = "taskhistoryQuery")
    @ResponseBody
    public Page<TaskHistory> taskhistoryQuery(Pageable pageable,TaskHistory taskHistory ,String id) {
    	pageable.addFilter(new Filter("oldId", Operator.eq, id));
//    	pageable.setPageNumber(100);
    	pageable.addEntity(taskHistory);
    	return taskHistoryService.findPage(pageable);
    }
    
    
    
    
    
}
