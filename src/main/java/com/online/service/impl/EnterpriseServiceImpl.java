package com.online.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.CustomFilter;
import com.online.DataAccessException;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Page;
import com.online.dao.EnterpriseDao;
import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.SystemUser;
import com.online.entity.online.BaseInformation;
import com.online.entity.online.BoilerInformation;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Kiln;
import com.online.entity.online.MonthlyInformation;
import com.online.entity.online.PowerPlant;
import com.online.entity.online.Project;
import com.online.entity.online.RawMaterials;
import com.online.entity.online.Section;
import com.online.entity.online.SimpleBaseInformation;
import com.online.entity.online.Enterprise.TaskType;
import com.online.entity.online.breakdownservice.BreakdownServiceBase;
import com.online.entity.online.catering.CateringBase;
import com.online.entity.online.constructionsite.ConstructionSiteBase;
import com.online.entity.online.dryclear.DryClearBase;
import com.online.entity.online.petrol.PetrolStationBase;
import com.online.service.BaseInformationService;
import com.online.service.BeastsBirdsService;
import com.online.service.BoilerInformationService;
import com.online.service.BreakdownServiceBaseService;
import com.online.service.CateringBaseService;
import com.online.service.ConstructionSiteBaseService;
import com.online.service.DryClearBaseService;
import com.online.service.EnterpriseService;
import com.online.service.GasStoveService;
import com.online.service.KilnService;
import com.online.service.MonthlyInformationService;
import com.online.service.NonRoadService;
import com.online.service.PetrolStationBaseService;
import com.online.service.PowerPlantService;
import com.online.service.ProjectEnterpriseUserService;
import com.online.service.ProjectService;
import com.online.service.RawMaterialsService;
import com.online.service.SectionService;
import com.online.service.SimpleBaseInformationService;
import com.online.service.SingleBoilerFuelService;
import com.online.service.SingleBoilerInformationService;
import com.online.service.SteelService;
import com.online.service.SystemUserService;
import com.online.service.TaskManagerService;
import com.online.service.TransportCarService;
import com.online.util.Constants;
import com.online.util.SpringUtils;

/**
 * 
 * @author 左志平
 * 
 *         企业服务实现
 *
 */
@Service("enterpriseServiceImpl")
public class EnterpriseServiceImpl extends BaseServiceImpl<Enterprise, Integer> implements EnterpriseService {

	@Autowired
	public EnterpriseDao enterpriseDao;

	@Autowired
	public BaseInformationService baseInformationService;

	@Autowired
	public BoilerInformationService boilerInformationService;

	@Autowired
	public ConstructionSiteBaseService constructionSiteBaseService;
	@Autowired
	public SingleBoilerInformationService singleBoilerInformationService;
	@Autowired
	public SingleBoilerFuelService singleBoilerFuelService;
	@Autowired
	public PetrolStationBaseService petrolStationBaseService;
	@Autowired
	public DryClearBaseService dryClearBaseService;
	@Autowired
	public BreakdownServiceBaseService breakdownServiceBaseService;
	@Autowired
	public BeastsBirdsService beastsBirdsService;
	@Autowired
	public CateringBaseService cateringBaseService;
	@Autowired
	public TransportCarService transportCarService;
	@Autowired
	public NonRoadService nonRoadService;

	@Autowired
	public SimpleBaseInformationService simpleBaseInformationService;

	@Autowired
	public SteelService steelService;
	@Autowired
	public GasStoveService gasStoveService;
	
	@Autowired
	public SectionService sectionService;
	@Autowired
	public RawMaterialsService rawMaterialsService;
	
	@Autowired
	public KilnService kilnService;
	@Autowired
	public ProjectService projectService;
	@Autowired
	public PowerPlantService powerPlantService;
	@Autowired
	public MonthlyInformationService monthlyInformationService;
	@Autowired
	public SystemUserService systemUserService;

	@Autowired
	protected TaskManagerService taskManagerService;

	@Autowired
	@Lazy
	public ProjectEnterpriseUserService projectEnterpriseUserService;

	@Override
	public Enterprise findByEnterpriseName(String enterpriseName) {
		return enterpriseDao.findByEnterpriseName(enterpriseName);
	}

	@Override
	public Enterprise findByAccount(Integer userId) {
		return enterpriseDao.findByAccount(userId);
	}

	@Override
	public List<Enterprise> findALLByAccount(List<Integer> userIds) throws Exception {
		return enterpriseDao.findALLByAccount(userIds);
	}

	@Override
	public void deleteByEnterpriseId(Integer id) throws Exception {
		
		List<ProjectEnterpriseUser> lists = projectEnterpriseUserService.findByFilter(Filter.eq("enterprise.id", id));
		//如果企业信息有多条绑定关系。则只删除绑定关系。不删除企业信息，如果只剩一条绑定关系，则把企业所有关联数据删除。
		if(lists != null && lists.size() > 1){
			//删除项目企业用户绑定关系
			projectEnterpriseUserService.deleteByProjectEnterpriseUser(SpringUtils.getCurrentProject()
					,find(id),SpringUtils.getCurrentSystemUser());
		}else{
			//删除项目企业用户绑定关系
			projectEnterpriseUserService.deleteByProjectEnterpriseUser(SpringUtils.getCurrentProject()
					,find(id),SpringUtils.getCurrentSystemUser());
			// 删除基本信息
			baseInformationService.deleteBaseInformationByEnterpriseId(id);
			// 删除施工工地基本信息
			constructionSiteBaseService.deleteConstructionSiteBaseByEnterpriseId(id);
			// 删除单独锅炉
			singleBoilerInformationService.deleteSingleBoilerInformationByEnterpriseId(id);
			// 删除单独锅炉
			singleBoilerFuelService.deleteSingleBoilerFuelByEnterpriseId(id);
			// 删除加油站
			petrolStationBaseService.deletePetrolStationBaseByEnterpriseId(id);
			// 删除干洗
			dryClearBaseService.deleteDryClearBaseByEnterpriseId(id);
			// 删除汽修
			breakdownServiceBaseService.deleteBreakdownServiceBaseByEnterpriseId(id);
			// 删除畜禽养殖
			beastsBirdsService.deleteBeastsBirdsByEnterpriseId(id);
			// 删除餐饮
			cateringBaseService.deleteCateringBaseByEnterpriseId(id);
			// 删除普表
			transportCarService.deleteTransportCarByEnterpriseId(id);
			nonRoadService.deleteNonRoadByEnterpriseId(id);
			// 删除钢铁
			steelService.deleteSteelByEnterpriseId(id);
			// 删除煤气炉
			gasStoveService.deleteGasStoveByEnterpriseId(id);
			
			enterpriseDao.deleteByEnterpriseId(id);
		}
		
	}

	@Override
	public void exportEnterprise(OutputStream out, List<SystemUser> systemUser, List<Enterprise> enterprises)
			throws Exception {
		// 所有用户id对应name
		Map<Integer, String> userMap = new HashMap<>();
		for (int i = 0; i < systemUser.size(); i++) {
			userMap.put(systemUser.get(i).getId(), systemUser.get(i).getUsername());
		}

		// 所有企业id
		List<Integer> enterpriseIds = new ArrayList<>();
		for (int i = 0; i < enterprises.size(); i++) {
			enterpriseIds.add(enterprises.get(i).getId());
		}

		// 查询所有企业下的基本信息
		List<Filter> baseInformationfilters = new ArrayList<>();
		baseInformationfilters.add(new Filter("enterprise", Operator.in, enterpriseIds));
		List<BaseInformation> baseInformationList = baseInformationService.findList(null, baseInformationfilters, null);
		Map<Integer, BaseInformation> baseInformationMap = new HashMap<>();
		if (baseInformationList != null && baseInformationList.size() > 0) {
			for (int i = 0; i < baseInformationList.size(); i++) {
				baseInformationMap.put(baseInformationList.get(i).getEnterprise().getId(), baseInformationList.get(i));
			}
		}

		// 查询所有企业下的锅炉信息
		List<Filter> boilerInformationfilters = new ArrayList<>();
		boilerInformationfilters.add(new Filter("enterprise", Operator.in, enterpriseIds));
		List<BaseInformation> boilerInformationList = baseInformationService.findList(null, boilerInformationfilters,
				null);

		// 郑有权
		String[] headers = { "ID", "行政区划", "名称", "行业类别（大类）", "行业编号（大类）", "行业类别（中类）", "行业编号（中类）", "组织编码", "联系人", "联系号码",
				"用户", "省", "市", "街道", "门牌号", "经度", "纬度 ", "生产总值", "年用电量", "年生产天数", "日生产小时数", "年正常生产时间" };
		Map<Integer, List<String>> map = new HashMap<>();

		// List<Enterprise> enterprises = enterpriseDao.findAll();

		for (int i = 0; i < enterprises.size(); i++) {
			List<String> list = new ArrayList<String>();
			list.add(enterprises.get(i).getId().toString());
			list.add(enterprises.get(i).getAddress().getCounty().getName().toString());
			list.add(enterprises.get(i).getName().toString());
			list.add(enterprises.get(i).getIndustryCategoryNameMain().toString());
			list.add(enterprises.get(i).getIndustryCategoryCodeMain().toString());
			list.add(enterprises.get(i).getIndustryCategoryNameMiddle().toString());
			list.add(enterprises.get(i).getIndustryCategoryCodeMiddle().toString());
			list.add(enterprises.get(i).getCode().toString());
			list.add(enterprises.get(i).getContacts().toString());
			list.add(enterprises.get(i).getContactNumber().toString());
			list.add(userMap.get(enterprises.get(i).getAccount()));
			list.add(enterprises.get(i).getAddress().getProvinces().getName().toString());
			list.add(enterprises.get(i).getAddress().getCity().getName().toString());
			list.add(enterprises.get(i).getAddress().getStreet().getName().toString());
			list.add(enterprises.get(i).getAddress().getHouseNumber().toString());
			list.add(enterprises.get(i).getAddress().getLongitude().toString());
			list.add(enterprises.get(i).getAddress().getLatitude().toString());
			// 基本信息
			BaseInformation baseInformation = baseInformationMap.get(enterprises.get(i).getId());
			if (baseInformation != null) {
				list.add(baseInformation.getGrossProduct() != null ? baseInformation.getGrossProduct().toString() : "");
				list.add(baseInformation.getEnergyUsed() != null ? baseInformation.getEnergyUsed().toString() : "");
				list.add(baseInformation.getWorkDay() != null ? baseInformation.getWorkDay().toString() : "");
				list.add(baseInformation.getDayHours() != null ? baseInformation.getDayHours().toString() : "");
				list.add(baseInformation.getNomalProductionHour() != null
						? baseInformation.getNomalProductionHour().toString() : "");
			}

			map.put(i + 1, list);

		}

		// ExportExcel.exportExcel("企业表", headers, map, out);

	}

	@Override
	public List<Map<String, Object>> findALLEnterpriseByAccount(List<Integer> userIds, String polluteSource)
			throws Exception {
		return enterpriseDao.findALLEnterpriseByAccount(userIds, polluteSource);
	}

	@Override
	public boolean isexist(Enterprise enterprise) throws Exception {
		// 如果企业名称+组织机构代码+区县 都相等
		Filter filter = Filter.eq("name", enterprise.getName());
		Filter filter1 = Filter.eq("code", enterprise.getCode());
		Filter filter2 = Filter.eq("address.city.code",  enterprise.getAddress().getCity().getCode());
		Filter filter3 = Filter.eq("address.county.code", enterprise.getAddress().getCounty().getCode());
		Filter filter4 = Filter.eq("enterpriseType",  enterprise.getEnterpriseType());

		List<Filter> filters = new ArrayList<Filter>(Arrays.asList(filter,filter1,filter2,filter3,filter4));
		if (enterprise.getId() != null && StringUtils.isNotBlank(enterprise.getId().toString())) {
			Filter filter5 = new Filter("id", Operator.ne, enterprise.getId());
			filters.add(filter5);
		}
		List<Enterprise> lists = findList(null, filters, null);

		// 企业名称相等区县信息相等， 经纬度距离小于2km
		List<Filter> filters1 = new ArrayList<Filter>(Arrays.asList(filter,filter2,filter3,filter4));
		if (enterprise.getId() != null && StringUtils.isNotBlank(enterprise.getId().toString())) {
			Filter filter5 = new Filter("id", Operator.ne, enterprise.getId());
			filters1.add(filter5);
		}
		List<Enterprise> lists1 = findList(null, filters1, null);

		if (lists != null && lists.size() > 0) {
			return true;
		} else if (isexistsEnterprise(enterprise, lists1)) {
			return true;
		}

		else {
			return false;
		}

	}

	public boolean isexistsEnterprise(Enterprise newEnterprise, List<Enterprise> lists1) {
		for (int i = 0; i < lists1.size(); i++) {
			double lat1 = newEnterprise.getAddress().getLatitude();
			double lng1 = newEnterprise.getAddress().getLongitude();
			double lat2 = lists1.get(i).getAddress().getLatitude();
			double lng2 = lists1.get(i).getAddress().getLongitude();

			if (GetDistance(lat1, lng1, lat2, lng2) < 2000) {
				return true;
			}
		}

		return false;
	}

	private static double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 计算两个经纬度之间的距离
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		// s = Math.round(s * 1000);
		s = Math.round(s);
		System.out.println("经纬度距离" + s);
		return s;
	}

	@Override
	public List<Enterprise> findALLByProjectid(Integer projectId) throws Exception {
		// TODO Auto-generated method stub
		// 郑有权
		return enterpriseDao.findALLByProjectid(projectId);
	}

	@Override
	public Page<Enterprise> findCustomPage(CustomFilter customFilter) throws Exception {
		// 郑有权
		return enterpriseDao.findCustomPage(customFilter);
	}

	@Override
	@Transactional
	public Enterprise update(Enterprise entity) {
		// TaskManagerUntils.completeTask(entity.getId());
		return super.update(entity);
	}

	@Override
	@Transactional
	public Enterprise update(Enterprise entity, String... ignoreProperties) {
		// TaskManagerUntils.completeTask(entity.getId());
		return super.update(entity, ignoreProperties);
	}

	@Override
	@Transactional
	public Enterprise updateDestined(Enterprise entity, String... destinedProperties) {
		// TaskManagerUntils.completeTask(entity.getId());
		return super.updateDestined(entity, destinedProperties);
	}

	@Override
	@Transactional
	public Enterprise save(Enterprise entity) {
		entity.setTaskType(TaskType.UNTREATED);
		return super.save(entity);
	}
	
	@Transactional
	@Override
	public void updatestatus(Integer projectId, Integer enterpriseId, Integer status, String opinion) {
		//如果是审核通过，修改任务状态为通过
		if (status == Constants.ENTERPRISE_STATUS_APPROVE) {
			taskManagerService.completeTask(enterpriseId);
		//直接退回	，修改任务状态为已处理
		}else if(status == Constants.ENTERPRISE_STATUS_NOTSUBMIT){
			taskManagerService.processedByEnterprise(enterpriseId);
		}
		 
		projectEnterpriseUserService.updateStatus(status, projectId, enterpriseId, false,opinion);
	}

	@Override
	public boolean isSave(Integer enterpriseId, Integer projectId,String enterpriseType) {
		// 郑有权
		//如果是简表直接判断工段是否填写
		if("SIMPLE".equals(enterpriseType)){
			List<Filter> filters = new ArrayList<>();
			filters.add(new Filter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, enterpriseId));
			filters.add(new Filter("project.id", Operator.eq, projectId));
			List<Section> sections = sectionService.findList(null, filters, null);
			if(sections != null && sections.size()>0){
				return true;
			}
		//如果是详表工段和炉窑有一个填写则为true	
		}else{
			List<Filter> filters = new ArrayList<>();
			filters.add(new Filter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, enterpriseId));
			filters.add(new Filter("project.id", Operator.eq, projectId));
			List<Section> sections = sectionService.findList(null, filters, null);
			List<Kiln> kilns = kilnService.findList(null, filters, null);
			
			if((sections != null && sections.size()>0) || (kilns != null && kilns.size()>0)){
				return true;
			}
			
		}
		return false;
	}
	
	@Override
	public boolean isBaseSave(Integer enterpriseId, Integer projectId,String enterpriseType) {
		//郑有权
		if("SIMPLE".equals(enterpriseType)){
			SimpleBaseInformation simpleBaseInformation = simpleBaseInformationService
					.findListByProjectAndEnterprise(projectId, enterpriseId);
						if (simpleBaseInformation != null && Constants.BASEINFO_ISSAVE_TRUE.equals(simpleBaseInformation.getIsSave())) {
							return true;
						}
		}else{
			BaseInformation baseInformation = baseInformationService.findListByProjectAndEnterprise(projectId, enterpriseId);
			if (baseInformation != null && Constants.BASEINFO_ISSAVE_TRUE.equals(baseInformation.getIsSave())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 原辅料是否保存
	 * @author 郑有权
	 * @date 创建时间：2018年7月2日 下午3:48:22 
	 * @param enterpriseId
	 * @param projectId
	 * @param enterpriseType
	 * @return
	 */
	public boolean isRawSave(Integer enterpriseId, Integer projectId,String enterpriseType) {
		//郑有权
		if("SIMPLE".equals(enterpriseType)){
			return true;
		}else{
			//如果工段存在判断原辅料
			if(isSave(enterpriseId, projectId, "SIMPLE")){
				List<Filter> filters = new ArrayList<>();
				filters.add(new Filter("enterprise", Operator.eq, enterpriseId));
				filters.add(new Filter("project", Operator.eq, projectId));
				filters.add(Filter.isNotNull("sectionId"));
				List<RawMaterials> rawMaterials = rawMaterialsService.findList(null, filters, null);
				
				if((rawMaterials != null && rawMaterials.size()>0)){
					return true;
				}
				return false;
			}else{
				return true;
			}
			
			
			
		}
		
	}
	
	

	@Override
	public Enterprise saveEnterprise(Enterprise enterprise,Integer projectType,Integer userId,Integer projectId) throws Exception {
		Enterprise enterpriseback = null;
		if (enterprise.getId() == null) {
			enterpriseback = findSaveByEnterpriseName(enterprise.getName(),enterprise);

			// 详表
			if ("NORMAL".equals(enterprise.getEnterpriseType().toString())) {
				BaseInformation baseInformation = new BaseInformation();
				baseInformation.setProject(projectService.find(projectId));
				baseInformation.setEnterprise(enterpriseback);
				baseInformationService.save(baseInformation);
				// 简表
			} else if ("SIMPLE".equals(enterprise.getEnterpriseType().toString())) {
				SimpleBaseInformation simpleBaseInformation = new SimpleBaseInformation();
				simpleBaseInformation.setProject(projectService.find(projectId));
				simpleBaseInformation.setEnterprise(enterpriseback);
				simpleBaseInformationService.save(simpleBaseInformation);
				// 单一锅炉
			} else if ("SINGLE_BOILER".equals(enterprise.getEnterpriseType().toString())) {

				// 施工工地
			} else if ("CONSTRUCTION_SITE".equals(enterprise.getEnterpriseType().toString())) {

				ConstructionSiteBase constructionSiteBase = new ConstructionSiteBase();
				constructionSiteBase.setProject(projectService.find(projectId));
				constructionSiteBase.setEnterprise(enterpriseback);
				constructionSiteBaseService.save(constructionSiteBase);
				// 加油站
			} else if ("PETROL_STATION".equals(enterprise.getEnterpriseType().toString())) {

				PetrolStationBase petrolStationBase = new PetrolStationBase();
				petrolStationBase.setProject(projectService.find(projectId));
				petrolStationBase.setEnterprise(enterpriseback);
				petrolStationBaseService.save(petrolStationBase);
				// 干洗
			} else if ("DRY_CLEAR".equals(enterprise.getEnterpriseType().toString())) {

				DryClearBase dryClearBase = new DryClearBase();
				dryClearBase.setProject(projectService.find(projectId));
				dryClearBase.setEnterprise(enterpriseback);
				dryClearBaseService.save(dryClearBase);
				// 汽修
			} else if ("BREAKDOWN_SERVICE".equals(enterprise.getEnterpriseType().toString())) {

				BreakdownServiceBase breakdownServiceBase = new BreakdownServiceBase();
				breakdownServiceBase.setProject(projectService.find(projectId));
				breakdownServiceBase.setEnterprise(enterpriseback);
				breakdownServiceBaseService.save(breakdownServiceBase);
				// 畜禽养殖
			} else if ("BEASTS_BIRDS".equals(enterprise.getEnterpriseType().toString())) {
				// 餐饮
			} else if ("CATERING".equals(enterprise.getEnterpriseType().toString())) {

				CateringBase cateringBase = new CateringBase();
				cateringBase.setProject(projectService.find(projectId));
				cateringBase.setEnterprise(enterpriseback);
				cateringBaseService.save(cateringBase);
			}
		} else {
			enterpriseback = enterprise;
		}

		if(projectType == null){
			throw new DataAccessException("未选择项目类型，请联系管理员");
		}
		SystemUser systemUser = systemUserService.find(userId);
		Project project = projectService.find(projectId);
		// 保存项目企业用户配置。
		projectEnterpriseUserService.saveProjectEnterpriseUser(project, enterpriseback,
				systemUser, Constants.STR_ENTERPRISE_STATUS_NOTSUBMIT,projectType);

		return enterpriseback;
	}

	@Override
	public Enterprise updateEnterprise(Enterprise enterprise) throws Exception {
		// 郑有权
		return update(enterprise, "account");
	}

	@Override
	public void submitEnterprise(Integer projectId, Integer enterpriseId, Integer status)
			throws Exception {
		
		ProjectEnterpriseUser  projectEnterpriseUser  = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(projectId, enterpriseId);
		//如果是审核通过不能提交
		if(projectEnterpriseUser.getTaskStatus().equals(Constants.STR_ENTERPRISE_STATUS_APPROVE)){
			throw new DataAccessException("已通过企业不能提交"); 
		}
		Enterprise enterprise = find(enterpriseId);
		
		Project project = projectService.find(projectId);
		String enterpriseType = enterprise.getEnterpriseType().toString();
		//简表和详表判断是否有工段信息。
		if("GENERAL".equals(enterpriseType) || "SIMPLE".equals(enterpriseType) || "NORMAL".equals(enterpriseType)){
			if(!isBaseSave(enterpriseId, project.getId(),enterpriseType)){
				throw new DataAccessException("基本信息未保存"); 
			}else if(!isSave(enterpriseId, project.getId(),enterpriseType)){
				if("SIMPLE".equals(enterpriseType)){
					throw new DataAccessException("工段产品信息未填写"); 
				}else{
					throw new DataAccessException("工段产品或炉窑信息未填写"); 
				}
			}else if(!isRawSave(enterpriseId, projectId, enterpriseType)){
				throw new DataAccessException("原辅料信息未填写"); 
			}
			
			if("NORMAL".equals(enterpriseType)){
				if(!isPowerMonth(enterpriseId, project.getId(),enterpriseType)){
					throw new DataAccessException("发电机组分月信息未填写");
				}else if(!isBoilerMonth(enterpriseId, project.getId(),enterpriseType)){
					throw new DataAccessException("锅炉分月信息未填写");
				}else if(!isKilnMonth(enterpriseId, project.getId(),enterpriseType)){
					throw new DataAccessException("炉窑分月信息未填写");
				}else if(!isSectionMonth(enterpriseId, project.getId(),enterpriseType)){
					throw new DataAccessException("工段产品分月信息未填写");
				}else if(!isRawMonth(enterpriseId, project.getId(),enterpriseType)){
					throw new DataAccessException("原辅料分月信息未填写");
				}
			}
			
		}
		//update(enterprise);
		updatestatus(projectId, enterpriseId, status,projectEnterpriseUser.getOpinion());
		//projectEnterpriseUserService.updateStatus(Constants.ENTERPRISE_STATUS_ALREADYSUBMIT, projectId, enterpriseId,false,opinion);
	}
	
	

	//发电机组分月是否填写
	public boolean isPowerMonth(Integer enterpriseId,Integer projectId,String enterpriseType){
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("enterprise.id", Operator.eq, enterpriseId));
		filters.add(new Filter("project.id", Operator.eq, projectId));
		List<PowerPlant> powerPlants = powerPlantService.findList(null, filters, null);
		if(powerPlants != null && powerPlants.size()==0){
			return true;
		}else{
			boolean flag = true;
			List<Integer> powerPlantIds = new ArrayList<>();
			for(PowerPlant powerPlant:powerPlants){
				powerPlantIds.add(powerPlant.getId());
			}
			List<Filter> monthfilters = new ArrayList<>();
			monthfilters.add(new Filter("enterprise.id", Operator.eq, enterpriseId));
			monthfilters.add(new Filter("project.id", Operator.eq, projectId));
			monthfilters.add(new Filter("powerPlantId", Operator.in, powerPlantIds));
			
			List<MonthlyInformation> monthlyInformations = monthlyInformationService.findList(null, monthfilters, null);
			if(monthlyInformations != null && monthlyInformations.size() == 0){
				flag = false;
			}
			
			return flag;
		}
	}
	
	//锅炉分月是否填写
	public boolean isBoilerMonth(Integer enterpriseId,Integer projectId,String enterpriseType){
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("emissionsManagement.enterprise.id", Operator.eq, enterpriseId));
		filters.add(new Filter("project.id", Operator.eq, projectId));
		List<BoilerInformation> boilerInformations = boilerInformationService.findList(null, filters, null);
		if(boilerInformations != null && boilerInformations.size()==0){
			return true;
		}else{
			boolean flag = true;
			List<Integer> boilerInformationIds = new ArrayList<>();
			for(BoilerInformation boilerInformation:boilerInformations){
				boilerInformationIds.add(boilerInformation.getId());
				
			}
			
			List<Filter> monthfilters = new ArrayList<>();
			monthfilters.add(new Filter("enterprise.id", Operator.eq, enterpriseId));
			monthfilters.add(new Filter("project.id", Operator.eq, projectId));
			monthfilters.add(new Filter("boilerId", Operator.in, boilerInformationIds));
			
			List<MonthlyInformation> monthlyInformations = monthlyInformationService.findList(null, monthfilters, null);
			if(monthlyInformations != null && monthlyInformations.size() == 0){
				flag = false;
			}
			return flag;
		}
	}
	//炉窑分月是否填写
	public boolean isKilnMonth(Integer enterpriseId,Integer projectId,String enterpriseType){
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, enterpriseId));
		filters.add(new Filter("project.id", Operator.eq, projectId));
		List<Kiln> kilns = kilnService.findList(null, filters, null);
		if(kilns != null && kilns.size()==0){
			return true;
		}else{
			boolean flag = true;
			List<Integer> kilnIds = new ArrayList<>();
			for(Kiln kiln:kilns){
				kilnIds.add(kiln.getId());
				
			}
			
			List<Filter> monthfilters = new ArrayList<>();
			monthfilters.add(new Filter("enterprise.id", Operator.eq, enterpriseId));
			monthfilters.add(new Filter("project.id", Operator.eq, projectId));
			monthfilters.add(new Filter("kilnId", Operator.in, kilnIds));
			
			List<MonthlyInformation> monthlyInformations = monthlyInformationService.findList(null, monthfilters, null);
			if(monthlyInformations != null && monthlyInformations.size() == 0){
				flag = false;
			}
			
			return flag;
		}
	}
	//产品分月是否填写
	public boolean isSectionMonth(Integer enterpriseId,Integer projectId,String enterpriseType){
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, enterpriseId));
		filters.add(new Filter("project.id", Operator.eq, projectId));
		List<Section> sections = sectionService.findList(null, filters, null);
		if(sections != null && sections.size()==0){
			return true;
		}else{
			boolean flag = true;
			List<Integer> sectionIds = new ArrayList<>();
			
			for(Section section:sections){
				sectionIds.add(section.getId());
				
			}
			List<Filter> monthfilters = new ArrayList<>();
			monthfilters.add(new Filter("enterprise.id", Operator.eq, enterpriseId));
			monthfilters.add(new Filter("project.id", Operator.eq, projectId));
			monthfilters.add(new Filter("sectionId", Operator.in, sectionIds));
			
			List<MonthlyInformation> monthlyInformations = monthlyInformationService.findList(null, monthfilters, null);
			if(monthlyInformations != null && monthlyInformations.size() == 0){
				flag = false;
			}
			
			return flag;
		}
	}
	//原辅料分月是否填写
	public boolean isRawMonth(Integer enterpriseId,Integer projectId,String enterpriseType){
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("enterprise", Operator.eq, enterpriseId));
		filters.add(new Filter("project", Operator.eq, projectId));
		filters.add(Filter.isNotNull("sectionId"));
		List<RawMaterials> rawMaterials = rawMaterialsService.findList(null, filters, null);
		if(rawMaterials != null && rawMaterials.size()==0){
			return true;
		}else{
			boolean flag = true;
			List<Integer> rawMaterialsIds = new ArrayList<>();
			
			for(RawMaterials rawMaterial:rawMaterials){
				rawMaterialsIds.add(rawMaterial.getId());
				
			}
			List<Filter> monthfilters = new ArrayList<>();
			monthfilters.add(new Filter("enterprise.id", Operator.eq, enterpriseId));
			monthfilters.add(new Filter("project.id", Operator.eq, projectId));
			monthfilters.add(new Filter("rawMaterialsId", Operator.in, rawMaterialsIds));
			
			List<MonthlyInformation> monthlyInformations = monthlyInformationService.findList(null, monthfilters, null);
			if(monthlyInformations != null && monthlyInformations.size() == 0){
				flag = false;
			}
			
			return flag;
		}
	}

	@Override
	public List<Map<String, Object>> findStatistics(Integer projectId, Integer type, Date start, Date end,
			String enterpriseType) {
		return enterpriseDao.findStatistics(projectId, type, start,end,enterpriseType);
	}

	@Override
	public boolean isFinish(Integer enterpriseId) {
		//郑有权
		Project  project = SpringUtils.getCurrentProject();
		Enterprise enterprise = find(enterpriseId);
		String enterpriseType = enterprise.getEnterpriseType().toString();
		//简表和详表判断是否完成
		if("NORMAL".equals(enterpriseType) || "SIMPLE".equals(enterpriseType)){
			if(
//				StringUtils.isBlank(enterprise.getInquirer())		//调查人
//				||StringUtils.isBlank(enterprise.getInquirerPhone())	//联系方式
				StringUtils.isBlank(enterprise.getName())		//企业名称
//				||StringUtils.isBlank(enterprise.getProductIndustry())		//产品行业
				||StringUtils.isBlank(enterprise.getIsEmergency())		//是否纳入应急名单
				||StringUtils.isBlank(enterprise.getIsStaggerFastigium())		//是否错峰企业
				||StringUtils.isBlank(enterprise.getIndustryCategoryCodeMain())		//行业大类
				||StringUtils.isBlank(enterprise.getIndustryCategoryCodeMiddle())		//行业中类
				||enterprise.getAddress() == null		//省
				||StringUtils.isBlank(enterprise.getContacts())		//联系人
				||StringUtils.isBlank(enterprise.getContactNumber())		//联系电话
				||StringUtils.isBlank(enterprise.getCorporation())		//法人
					){
				return false;
			}else if(StringUtils.isNotBlank(project.getSysProductIndustrys())){
				//如果项目的产品行业不为空,跳转到分行业表
				if(StringUtils.isBlank(enterprise.getProductIndustry())){
					return false;
				}
			}else{
				//判断是否散乱污,如果营业执照不为空或者散乱污不为空为true
				if((StringUtils.isNotBlank(enterprise.getCode())&&StringUtils.isNotBlank(enterprise.getPhotoUrl()))
					||(enterprise.getIsSanluanwu() != null && "是".equals(enterprise.getIsSanluanwu()))
						){
					return true;
				}else{
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public Enterprise findSaveByEnterpriseName(String enterpriseName) {
		return findSaveByEnterpriseName(enterpriseName, null);
		
	}
	//通过企业名称查询是否存在
	public Enterprise findSaveByEnterpriseName(String enterpriseName,Enterprise enterprise) {
		
		List<Enterprise> enterprises = findByFilter(Filter.eq("name", enterpriseName));
		Enterprise enterpriseBack = null;
		if(enterprises != null && enterprises.size()>0){
			enterpriseBack = enterprises.get(0);
			if(enterprise != null){
				enterpriseBack.setContacts(enterprise.getContacts());
				enterpriseBack.setAccount(enterprise.getAccount());
				enterpriseBack.setContactNumber(enterprise.getContactNumber());
				enterpriseBack.setCorporation(enterprise.getCorporation());
				enterpriseBack.setEnterpriseType(enterprise.getEnterpriseType());
				enterpriseBack = update(enterpriseBack);
			}
		}else{
			if(enterprise != null){
				enterpriseBack = save(enterprise);
			}else{
				Enterprise newEnter = new Enterprise();
				newEnter.setName(enterpriseName);
				enterpriseBack = save(newEnter);
			}
		}
		return enterpriseBack;
		
	}
	

}
