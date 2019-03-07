package com.online.service.impl;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.DataAccessException;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.TaskManagerDao;
import com.online.entity.Area;
import com.online.entity.DictionaryData;
import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.SystemRole;
import com.online.entity.SystemUser;
import com.online.entity.SystemUser.UserType;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Enterprise.EnterpriseType;
import com.online.entity.online.Project;
import com.online.entity.online.TaskManager;
import com.online.entity.online.embeddable.Address;
import com.online.excelexport.ExcelWriteUtils;
import com.online.service.AreaService;
import com.online.service.EnterpriseService;
import com.online.service.ProjectEnterpriseUserService;
import com.online.service.ProjectService;
import com.online.service.SelectService;
import com.online.service.SystemRoleService;
import com.online.service.SystemUserService;
import com.online.service.TaskManagerService;
import com.online.util.Constants;
import com.online.util.PinyinUtils;
import com.online.util.SpringUtils;
/**
 * 
 * @author 左志平
 * 
 * 任务管理服务实现
 *
 */
@Service("taskManagerServiceImpl")
public class TaskManagerServiceImpl extends BaseServiceImpl<TaskManager, Long> 
		implements SelectService, TaskManagerService {

	@Autowired
	@Lazy
	private EnterpriseService enterpriseService;   
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TaskManagerDao taskManagerDao;
	
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemRoleService systemRoleService;
	
	@Autowired
	@Lazy
	private ProjectEnterpriseUserService  projectEnterpriseUserService ;
	
	
	@Override
	@Transactional
	public void exportTask(String filePath, Integer projectId) {
		Sheet sheet = ExcelWriteUtils.getSheet(filePath, 0);
		for (int i = 1 ;i<sheet.getPhysicalNumberOfRows();i++) {
			Row row = sheet.getRow(i);
			if(row == null || ExcelWriteUtils.isBlankRow(row))continue;
			row.getPhysicalNumberOfCells();
			TaskManager taskManager = new TaskManager();
			taskManager.setExecutor(ExcelWriteUtils.getStringValue(row, 0));
			taskManager.setEnterpriseName(ExcelWriteUtils.getStringValue(row, 1));
			taskManager.setContactPerson(ExcelWriteUtils.getStringValue(row, 2));
			taskManager.setContactPhone(ExcelWriteUtils.getStringValue(row, 3));
			taskManager.setCountyName(ExcelWriteUtils.getStringValue(row, 4));
			taskManager.setDetailAddress(ExcelWriteUtils.getStringValue(row, 5));
			taskManager.setBaseInfo(ExcelWriteUtils.getStringValue(row, 6));
			taskManager.setEnterpriseType(EnterpriseType.valueOf(ExcelWriteUtils.getStringValue(row, 7)));
			taskManager.setProjectId(projectId);
			taskManager.setStatus(Constants.TASK_STATUS_ASSIGNED);
			save(taskManager);
		}
		
	}

	@Override
	@Transactional
	public void reportData(TaskManager taskManager,Long repeatId,Integer enterpriseId,Integer userId) throws Exception {
		TaskManager taskManagerEntity = find(taskManager.getId());//查找实例对象
		Project project = null;
		if(taskManagerEntity !=null){
			project = projectService.find(taskManagerEntity.getProjectId());
		}
		
		taskManagerEntity.setReport(taskManager.getReport());//更新上报状态
		taskManagerEntity.setReportEnterpriseName(taskManager.getReportEnterpriseName());//更新上报企业名称
		taskManagerEntity.setRemarks(taskManager.getRemarks());//更新备注信息
		taskManagerEntity.setPhotoPath(taskManager.getPhotoPath());//更新照片地址
		if(taskManager.getAddress()!=null){
			taskManagerEntity.setAddress(taskManager.getAddress().clear());//更新详细地址、经纬度
		}else{
			taskManagerEntity.setAddress(null);
		}
		taskManagerEntity.setRelocationAddress(taskManager.getRelocationAddress());//更新搬迁地址
		taskManagerEntity.setRepeatId(repeatId);
		Date relocationDate = taskManager.getRelocationDate();
		taskManagerEntity.setRelocationDate(relocationDate);//更新搬迁日期
		taskManagerEntity.setInquirer(taskManager.getInquirer());//更新调研人
		taskManagerEntity.setInquirerPhone(taskManager.getInquirerPhone());//更新调研人联系电话
		String enterpriseStatus = taskManager.getEnterpriseStatus();
		Enterprise enterprise = taskManagerEntity.getEnterprise();
		if(enterpriseStatus.equals("D_09")){//已上报
			//已上报，不需要填报，修改任务状态为完成
			taskManagerEntity.setStatus(Constants.TASK_STATUS_COMPLETE);
		}else if(enterpriseStatus.startsWith("T") || (enterpriseStatus.equals("D_05"))){//需要填报
			//创建企业
			cereateEnterprise(taskManagerEntity, enterprise, userId, project);
		}
		update(taskManagerEntity);
		
	}
	
	//任务生成企业
	public void cereateEnterprise(TaskManager taskManagerEntity,Enterprise enterprise,Integer userId,Project project) throws Exception{
		taskManagerEntity.setStatus(Constants.TASK_STATUS_PROCESSED);  //已处理
		if(enterprise == null){
			Enterprise enterpriseback = null;
			//是否已录入企业
			if(taskManagerEntity.getAlreadyEnterpriseId() != null){
				enterprise =  enterpriseService.find(taskManagerEntity.getAlreadyEnterpriseId());
			}
			if(enterprise == null){
				enterprise = new Enterprise();
				enterprise.setName(taskManagerEntity.getEnterpriseName());
				enterprise.setAccount(userId);
				enterprise.setContactNumber(taskManagerEntity.getContactPhone());
				enterprise.setContacts(taskManagerEntity.getContactPerson());
				enterprise.setCorporation(taskManagerEntity.getCorporation());
				enterprise.setEnterpriseType(taskManagerEntity.getEnterpriseType());
				enterprise.setAddress(getAddress(taskManagerEntity.getCountyName(), project));
				Set<Project> projects = new HashSet<>();
				projects.add(project);
				enterprise.setProjects(projects);
			}
			//新建企业信息
			enterpriseback = enterpriseService.saveEnterprise(enterprise,taskManagerEntity.getProjectTypeId(),userId,taskManagerEntity.getProjectId());
			taskManagerEntity.setEnterprise(enterpriseback);
		}else{
			/*if(enterprise.getStatus()!=null && enterprise.getStatus() == Constants.ENTERPRISE_STATUS_ALREADYSUBMIT){
				throw new TaskManagerException("企业已经完成调查，不允许修改！");
			}*/
		}
	}
	@Autowired
	AreaService areaService;
	private Address getAddress(String countyName ,Project project){
		Area county = areaService.findList(null,Arrays.asList(Filter.eq("parent", project.getCity()),Filter.eq("name", countyName)),null).stream().findFirst().orElse(null);
		Area city = areaService.findAreaByAreaCode(project.getCity());
		Area provinces = areaService.findAreaByAreaCode(city != null?city.getParent():project.getProvinces());
		Address address = new Address();
		address.setProvinces(provinces);
		address.setCity(city);
		address.setCounty(county);
		return address;
	}

	@Override
	@Transactional
	public void completeTask(Integer enterpriseId) {
		taskManagerDao.completeByEnterprise(enterpriseId);
	}
	@Override
	@Transactional
	public void processedByEnterprise(Integer enterpriseId) {
		taskManagerDao.processedByEnterprise(enterpriseId);
	}

	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<DictionaryData> list = new ArrayList<>();
		List<Filter> filters = new ArrayList<>();
		List<Map<String, Object>> findList = taskManagerDao.findCompleteTaskManager(SpringUtils.getProjectId());
		for (Map<String, Object> taskManager : findList) {
			
			DictionaryData dictionaryData = new DictionaryData();
			dictionaryData.setCode(String.valueOf(taskManager.get("id")));
			dictionaryData.setCodeValue(String.valueOf(taskManager.get("name")));
			list.add(dictionaryData);
		}
		return list;
	}

	@Override
	@Transactional
	public void distribution(String ids, String user,String enterpriseType,Integer projectTypeId) throws  Exception {
		//郑有权
		if(StringUtils.isNotBlank(ids)){
			String[] strIds = ids.split(",");
			for(String id:strIds){
				TaskManager taskManager = find(Long.parseLong(id));
				if(taskManager != null){
					//如果是新任务，或者已分配任务，可以修改分配人。已处理和已完成不能修改分配人
					if(Constants.TASK_STATUS_NEW.equals(taskManager.getStatus())){
						taskManager.setExecutor(user);
						taskManagerDao.distribution(Long.parseLong(id),user,enterpriseType,projectTypeId);
					}else{
						throw new DataAccessException("已处理任务不能分配");
					}
				}
				
			}
		}
	}

	@Transactional
	@Override
	public void createEnterpriseUser(String ids,String enterpriseType,Integer projectTypeId) throws Exception {
		//郑有权
		if(StringUtils.isNotBlank(ids)){
			String[] strIds = ids.split(",");
			for(String id:strIds){
				TaskManager taskManager = find(Long.parseLong(id));
				//如果任务状态为新建
				if(Constants.TASK_STATUS_NEW.equals(taskManager.getStatus())){
					//创建企业用户
					SystemUser user = createUser(taskManager);
					//任务分配调查人
					taskManager.setExecutor(user.getUsername());
					taskManager.setProjectTypeId(projectTypeId);
					taskManager.setEnterpriseType(getEnterpriseType(enterpriseType));
					taskManager.setStatus(Constants.TASK_STATUS_ASSIGNED); //已分配
					taskManager.setEnterpriseStatus("T_01");
					TaskManager taskManagerBack = update(taskManager);
					
					//创建企业信息
					reportData(taskManagerBack,null,null,user.getId());
				
				}else{
					throw new DataAccessException("已处理任务不能分配");
				}
				
				
				
				
				
				
				
			}
		}
	}
	
	public SystemUser createUser(TaskManager taskManager){
		SystemUser entity = new SystemUser();
		entity.setUsername(taskManager.getEnterpriseName());
		String password = PinyinUtils.getThreeStr(taskManager.getEnterpriseName())+((int)((Math.random()*9+1)*10000));
		entity.setPasswordVisible(password);
		entity.setPassword(DigestUtils.md5Hex(password));
		entity.setUserType(UserType.enterprise);
		entity.setProject(projectService.find(taskManager.getProjectId()));
		Set<SystemRole> roles = new HashSet<>();
		
		List<SystemRole> systemRoles = systemRoleService.findByFilter(new Filter("name", Operator.eq, "企业用户"));
		if(systemRoles != null && systemRoles.size()>0){
			roles.add(systemRoles.get(0));
			entity.setRoles(roles);
		}
		
		return systemUserService.save(entity);
	}
	
	
	
	
	public EnterpriseType getEnterpriseType(String str){
		switch (str) {
		case "NORMAL":
			return EnterpriseType.NORMAL;
		case "SIMPLE":
			return EnterpriseType.SIMPLE;
		case "SINGLE_BOILER":
			return EnterpriseType.SINGLE_BOILER;
		case "CONSTRUCTION_SITE":
			return EnterpriseType.CONSTRUCTION_SITE;
		case "PETROL_STATION":
			return EnterpriseType.PETROL_STATION;
		case "DRY_CLEAR":
			return EnterpriseType.DRY_CLEAR;
		case "BREAKDOWN_SERVICE":
			return EnterpriseType.BREAKDOWN_SERVICE;
		case "BEASTS_BIRDS":
			return EnterpriseType.BEASTS_BIRDS;
		case "CATERING":
			return EnterpriseType.CATERING;
		case "GENERAL":
			return EnterpriseType.GENERAL;

		default:
			return EnterpriseType.NORMAL;
		}
		
	}

	@Override
	public List<Map<String, Object>> progressQuery(Integer projectId) {
//		Map<String, Map<String, Object>> returnMap = new HashMap<String, Map<String,Object>>();
//		//郑有权
//		List<Map<String, Object>> maps= taskManagerDao.progressQuery(projectId);
//		for(Map<String, Object> map:maps){
//			returnMap.put((String)map.get("executor"), map);
//		}
		
		
		return taskManagerDao.progressQuery(projectId);
	}

	@Override
	public TaskManager findByEnterpriseId(Integer enterpriseId) {
		//郑有权
		List<TaskManager> lists = findByFilter(new Filter("enterprise.id", Operator.eq, enterpriseId));
		if(lists != null &&lists.size() >0){
			return lists.get(0);
		}
		return null;
	}

	@Override
	public void deleteTaskManager(Long taskManagerId) {
		// TODO Auto-generated method stub
		TaskManager taskManager = find(taskManagerId);
		Enterprise enterprise = taskManager.getEnterprise();
		//郑有权
		if(enterprise != null){
			try {
				//enterpriseService.deleteByEnterpriseId(enterprise.getId());
				taskManager.setEnterprise(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		delete(taskManager);
		
	}

	@Override
	public List<Map<String, Object>> findStatistics(Integer projectId,Integer type,Date start,Date end) {
		// TODO Auto-generated method stub
		//郑有权
		return taskManagerDao.findStatistics(projectId,type,start,end);
	}

	
	@Override
	public void updateName(TaskManager taskManager) {
		// TODO Auto-generated method stub
		//郑有权
		TaskManager taskManagerback =  find(taskManager.getId());
		taskManagerback.setEnterpriseName(taskManager.getEnterpriseName());
		
		Enterprise enterprise = taskManagerback.getEnterprise();
		if(enterprise != null){
			enterprise.setName(taskManager.getEnterpriseName());
			ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(taskManagerback.getProjectId(), enterprise.getId());
			if(projectEnterpriseUser != null ){
				SystemUser systemUser = projectEnterpriseUser.getUser();
				systemUser.setUsername(taskManager.getEnterpriseName());
			}
			
		}
		
		
		
	}

	
	
	
	

}
