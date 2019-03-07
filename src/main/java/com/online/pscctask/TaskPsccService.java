package com.online.pscctask;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.online.Kiln;
import com.online.entity.online.Product;
import com.online.entity.online.PsccStatusTaskManage;
import com.online.entity.online.RawMaterials;
import com.online.entity.online.Section;
import com.online.entity.online.TaskList;
import com.online.exportpdf.DataBean;
import com.online.service.ProductService;
import com.online.service.PsccStatusTaskService;
import com.online.service.RawMaterialsService;
import com.online.service.TaskListService;
import com.online.util.Constants;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年9月28日 上午11:20:55 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
@Service
public class TaskPsccService {
	
	
	@Autowired
	private TaskListService taskListService;
	@Autowired
    private ProductTask productTask;
    @Autowired
    private RawMaterialsTask rawMaterialsTask;
    @Autowired
    private RawMaterialsService rawMaterialsService;
    @Autowired
    private ProductService productService;
    
    @Autowired
    private PsccStatusTaskService psccStatusTaskService;
	
    ITaskListInsert itaskListInsert;
    
    public static MyPsccTaskThread myPsccTaskThread = null;
    
    public static Boolean isscan = false;
    
    
	
	public static Boolean isIsscan() {
		return isscan;
	}

	public static void setIsscan(Boolean isscan) {
		TaskPsccService.isscan = isscan;
	}

	/**
	 * 是否存在并创建任务
	 * @author 郑有权
	 * @date 创建时间：2017年10月25日 上午11:24:29 
	 * @param dataBean
	 */
	public boolean isExistCreate(DataBean dataBean){
		boolean isCreate = false;
		
		if(dataBean instanceof Kiln){
			Kiln kiln = (Kiln)dataBean;
			if(kiln.getProduct() != null && StringUtils.isNotBlank(kiln.getProduct().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_PRODUCT,
						kiln.getProduct().getName(),kiln.getProduct().getUnit(),kiln.getProduct().getId(),
						kiln.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),kiln.getEnterpriseEmissionsManagement().getEnterprise().getId(),kiln.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
			if(kiln.getRawMaterials1() != null && StringUtils.isNotBlank(kiln.getRawMaterials1().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
						kiln.getRawMaterials1().getName(), kiln.getRawMaterials1().getUnit(),kiln.getRawMaterials1().getId(),
						kiln.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),kiln.getEnterpriseEmissionsManagement().getEnterprise().getId(),kiln.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
			
			if(kiln.getRawMaterials2() != null && StringUtils.isNotBlank(kiln.getRawMaterials2().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
					kiln.getRawMaterials2().getName(), kiln.getRawMaterials2().getUnit(),kiln.getRawMaterials2().getId(),
					kiln.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),kiln.getEnterpriseEmissionsManagement().getEnterprise().getId(),kiln.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
			if(kiln.getRawMaterials3() != null && StringUtils.isNotBlank(kiln.getRawMaterials3().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
					kiln.getRawMaterials3().getName(), kiln.getRawMaterials3().getUnit(),kiln.getRawMaterials3().getId(),
					kiln.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),kiln.getEnterpriseEmissionsManagement().getEnterprise().getId(),kiln.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
			if(kiln.getRawMaterials4() != null && StringUtils.isNotBlank(kiln.getRawMaterials4().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
					kiln.getRawMaterials4().getName(), kiln.getRawMaterials4().getUnit(),kiln.getRawMaterials4().getId(),
					kiln.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),kiln.getEnterpriseEmissionsManagement().getEnterprise().getId(),kiln.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
			if(kiln.getRawMaterials5() != null && StringUtils.isNotBlank(kiln.getRawMaterials5().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
					kiln.getRawMaterials5().getName(), kiln.getRawMaterials5().getUnit(),kiln.getRawMaterials5().getId(),
					kiln.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),kiln.getEnterpriseEmissionsManagement().getEnterprise().getId(),kiln.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
		}else if(dataBean instanceof Section){
			Section section = (Section)dataBean;
			if(section.getProduct() != null && StringUtils.isNotBlank(section.getProduct().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_PRODUCT,
					section.getProduct().getName(),section.getProduct().getUnit(),section.getProduct().getId(),
					section.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),section.getEnterpriseEmissionsManagement().getEnterprise().getId(),section.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
			if(section.getRawMaterials1() != null && StringUtils.isNotBlank(section.getRawMaterials1().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
					section.getRawMaterials1().getName(), section.getRawMaterials1().getUnit(),section.getRawMaterials1().getId(),
					section.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),section.getEnterpriseEmissionsManagement().getEnterprise().getId(),section.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
			if(section.getRawMaterials2() != null && StringUtils.isNotBlank(section.getRawMaterials2().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
					section.getRawMaterials2().getName(), section.getRawMaterials2().getUnit(),section.getRawMaterials2().getId(),
					section.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),section.getEnterpriseEmissionsManagement().getEnterprise().getId(),section.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
			if(section.getRawMaterials3() != null && StringUtils.isNotBlank(section.getRawMaterials3().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
					section.getRawMaterials3().getName(), section.getRawMaterials3().getUnit(),section.getRawMaterials3().getId(),
					section.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),section.getEnterpriseEmissionsManagement().getEnterprise().getId(),section.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
			if(section.getRawMaterials4() != null && StringUtils.isNotBlank(section.getRawMaterials4().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
					section.getRawMaterials4().getName(), section.getRawMaterials4().getUnit(),section.getRawMaterials4().getId(),
					section.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),section.getEnterpriseEmissionsManagement().getEnterprise().getId(),section.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
			if(section.getRawMaterials5() != null && StringUtils.isNotBlank(section.getRawMaterials5().getName())){
				boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
					section.getRawMaterials5().getName(), section.getRawMaterials5().getUnit(),section.getRawMaterials5().getId(),
					section.getEnterpriseEmissionsManagement().getEnterprise().getIndustryCategoryCodeMiddle(),section.getEnterpriseEmissionsManagement().getEnterprise().getId(),section.getProject().getId());
				if(isTrue){
					isCreate = true;
				}
			}
		}else if(dataBean instanceof RawMaterials){
			RawMaterials rawMaterials = (RawMaterials)dataBean;
			boolean isTrue = isExistCreate(Constants.TASK_TYPE_RAWMATERIALS,
					rawMaterials.getName(), rawMaterials.getUnit(),rawMaterials.getId(),null,rawMaterials.getEnterprise(),rawMaterials.getProject());
			if(isTrue){
				isCreate = true;
			}
		}else if(dataBean instanceof Product){
			Product product = (Product)dataBean;
			boolean isTrue = isExistCreate(Constants.TASK_TYPE_PRODUCT,
					product.getName(), product.getUnit(),product.getId(),null,product.getEnterprise(),product.getProject());
			if(isTrue){
				isCreate = true;
			}
		}
		
		return isCreate;
		
		
	}
	
	/**
	 * 生成配置
	 * @author 郑有权
	 * @date 创建时间：2017年9月28日 上午11:54:05 
	 * @param taskList
	 */
	public void insertConfig(TaskList taskList) {
		// TODO Auto-generated method stub
		//郑有权
		if(taskList != null){
			itaskListInsert = getTaskListInsertService(taskList);
			if(itaskListInsert != null){
				
				itaskListInsert.insertConfig(taskList); 
			}
		}
	}
	
	
	
	private ITaskListInsert getTaskListInsertService(TaskList taskList){
		
		switch (taskList.getTaskType()) {
		case Constants.TASK_TYPE_PRODUCT:
			return productTask;
		case Constants.TASK_TYPE_RAWMATERIALS:
			return rawMaterialsTask;
		default:
			return null;
		}
		
		
	}
	
	

	/**
	 * 
	 * @author 郑有权
	 * @date 创建时间：2017年9月28日 下午3:23:53 
	 * @param type				类型
	 * @param name				名称
	 * @param unit				单位	
	 * @param dataSourceId		数据来源
	 * @param industryCode		行业代码
	 * @param enterpriseId		企业id
	 * @param projectId			项目id
	 * @return
	 */
	private boolean isExistCreate(String type,String name,String unit,Integer dataSourceId
			,String industryCode,Integer enterpriseId,Integer projectId) {
		TaskList taskList = new TaskList();
		taskList.setTaskType(type);
		taskList.setDataName(name);
		taskList.setDataUnit(unit);
		taskList.setDataSourceId(dataSourceId);
		taskList.setIndustryCode(industryCode);
		taskList.setEnterprise(enterpriseId);
		taskList.setProject(projectId);
		
		//郑有权
		if(StringUtils.isNotBlank(taskList.getTaskType()) && StringUtils.isNotBlank(taskList.getDataName())){
			List<Filter> taskfilters = new ArrayList<>();
			//通过任务类型，数据名称，数据单位，行业代码判断任务列表中是否存在
			taskfilters.add(new Filter("taskType", Operator.eq, taskList.getTaskType()));
			taskfilters.add(new Filter("dataName", Operator.eq, taskList.getDataName()));
			taskfilters.add(new Filter("dataUnit", Operator.eq, taskList.getDataUnit()));
			taskfilters.add(new Filter("industryCode", Operator.eq, taskList.getIndustryCode()));
			
			List<TaskList> taskLists= taskListService.findList(null, taskfilters, null);
			
			//如果任务列表中不存在，从配置中查找
			if(taskLists != null &&  taskLists.size() ==0){
				//如果配置中不存在，生成任务
				if(getTaskListInsertService(taskList) !=null && !getTaskListInsertService(taskList).isExist(taskList)){
					TaskList taskListback = taskListService.saveTaskList(taskList);
					createPsccStatusTask(taskListback,projectId,enterpriseId);
					return true;
				}
			}else{
				//如果列表中存在，则创建pscc状态管理任务
				createPsccStatusTask(taskLists.get(0),projectId,enterpriseId);
				return true;
			}
			
		}
		
		return false;
	}
	
	//创建pscc状态管理任务。
	public void createPsccStatusTask(TaskList taskList,Integer projectId,Integer enterpriseId){
		PsccStatusTaskManage psccStatusTaskManage = new PsccStatusTaskManage();
		psccStatusTaskManage.setPsccTaskId(taskList.getId());
		psccStatusTaskManage.setProjectId(projectId);
		psccStatusTaskManage.setEnterpriseId(enterpriseId);
		psccStatusTaskManage.setPsccTaskStatus(Constants.ENTERPRISE_PSCC_STATUS_UNFINISH);	
		
		psccStatusTaskService.savePsccStatusTaskManage(psccStatusTaskManage); 
	}
	
	
	
	
	class MyPsccTaskThread extends Thread{
		
		private String type;
		
		
		
		public String getType() {
			return type;
		}



		public void setType(String type) {
			this.type = type;
		}



		@Override
		public void run() {
			isscan = true;
			if(Constants.SCAN_rawMaterials.equals(type)){
				List<RawMaterials> rawMaterialsList = rawMaterialsService.findAll();
				for(RawMaterials rawMaterials:rawMaterialsList){
					//isExistCreate(rawMaterials);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else if(Constants.SCAN_product.equals(type)){
				List<Product> productList = productService.findAll();
				for(Product product:productList){
					//isExistCreate(product);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			isscan = false;
			
		}
	}
	
	public void startScanPsccTask(String type){
		if(myPsccTaskThread == null){
			myPsccTaskThread = new MyPsccTaskThread();
			myPsccTaskThread.setType(type);
			myPsccTaskThread.start();
		}else{
			if(!myPsccTaskThread.isAlive()){
				myPsccTaskThread = null;
				startScanPsccTask(type);
			}
		}
	}
	
	
}
