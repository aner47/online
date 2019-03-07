package com.online.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.ProjectModuleInfoDao;
import com.online.entity.online.ModuleDetail;
import com.online.entity.online.ModuleInfo;
import com.online.entity.online.ProjectModuleDetail;
import com.online.entity.online.ProjectModuleInfo;
import com.online.entity.online.ProjectTemplates;
import com.online.service.ModuleDetailService;
import com.online.service.ModuleInfoService;
import com.online.service.ProjectModuleDetailService;
import com.online.service.ProjectModuleInfoService;
import com.online.service.ProjectTemplatesService;

/**
 * 
 * @author 左志平
 * 
 *         项目模块信息服务实现
 *
 */
@Service("projectModuleInfoServiceImpl")
public class ProjectModuleInfoServiceImpl extends BaseServiceImpl<ProjectModuleInfo, Integer>
		implements ProjectModuleInfoService {

	@Autowired
	private ProjectTemplatesService projectTemplatesService;
	@Autowired
	private ModuleInfoService moduleInfoService;
	@Autowired
	private ModuleDetailService moduleDetailService;
	@Autowired
	private ProjectModuleDetailService projectModuleDetailService;
	@Autowired
	private ProjectModuleInfoDao  projectModuleInfoDao;

	@Transactional
	public void saveModule(String moduleClassification, Integer moduleInfoId, Integer templateId) {
		ProjectTemplates projectTemplates = projectTemplatesService.find(templateId);
		String templatesType = projectTemplates.getTemplatesType();
		ModuleInfo moduleInfo = moduleInfoService.find(moduleInfoId);
		ProjectModuleInfo projectModuleInfo = new ProjectModuleInfo();
		projectModuleInfo.setName(moduleInfo.getName());
		projectModuleInfo.setProjectTemplates(projectTemplates);
		projectModuleInfo.setModuleType(moduleInfo.getModuleType());
		projectModuleInfo.setOrder(moduleInfo.getOrder());
		projectModuleInfo.setStatus(moduleInfo.getStatus());
		projectModuleInfo.setProjectId(projectTemplates.getProjectId());
		projectModuleInfo.setModuleClassification(moduleInfo.getType());
		super.save(projectModuleInfo);
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("moduleInfo.id", Operator.eq, moduleInfoId));
		List<ModuleDetail> moduleDetails = moduleDetailService.findList(null, filters, null);
		if("审核".equals(templatesType)){
			moduleDetails = moduleDetails.stream()
					.filter(o1->!Pattern.compile("governanceMeasures\\d\\.").matcher(o1.getKey()).find()
					|| o1.getKey().equals("emissionsManagement.governanceMeasures1.gmno") 
					|| o1.getKey().equals("emissionsManagement.governanceMeasures2.gmno") 
					|| o1.getKey().equals("emissionsManagement.governanceMeasures3.gmno") 
					|| o1.getKey().equals("emissionsManagement.governanceMeasures4.gmno") 
					|| o1.getKey().equals("governanceMeasures1.gmno")
					|| o1.getKey().equals("governanceMeasures2.gmno")
					|| o1.getKey().equals("governanceMeasures3.gmno")
					|| o1.getKey().equals("governanceMeasures4.gmno")
					|| o1.getKey().equals("enterpriseEmissionsManagement.governanceMeasures1.gmno")
					|| o1.getKey().equals("enterpriseEmissionsManagement.governanceMeasures2.gmno")
					|| o1.getKey().equals("enterpriseEmissionsManagement.governanceMeasures3.gmno")
					|| o1.getKey().equals("enterpriseEmissionsManagement.governanceMeasures4.gmno")
					)
					.filter(o1->!Pattern.compile("exhaustionHoleTail\\.").matcher(o1.getKey()).find()
					|| o1.getKey().equals("exhaustionHoleTail.exhaustionHoleNum")
					)
					.filter(o1->!Pattern.compile("exhaustionHole\\.").matcher(o1.getKey()).find()
					|| o1.getKey().equals("emissionsManagement.exhaustionHole.exhaustionHoleNum")
					|| o1.getKey().equals("enterpriseEmissionsManagement.exhaustionHole.exhaustionHoleNum")
					|| o1.getKey().equals("exhaustionHole.exhaustionHoleNum")
					|| o1.getKey().equals("exhaustionHoleTail.exhaustionHoleNum")
					)
					.collect(Collectors.toList());
		}
		if (moduleDetails != null) {
			for (ModuleDetail moduleDetail : moduleDetails) {
				
				ProjectModuleDetail projectModuleDetail = new ProjectModuleDetail();
				projectModuleDetail.setHead(moduleDetail.getHead());
				projectModuleDetail.setKey(moduleDetail.getKey());
				projectModuleDetail.setModuleOrder(moduleDetail.getModuleOrder());
				projectModuleDetail.setProjectModuleInfo(projectModuleInfo);
				projectModuleDetailService.save(projectModuleDetail);
			}
		}

	}

	@Override
	public ProjectModuleInfo findName(String name) {
		return projectModuleInfoDao.findName(name);
	}

}
