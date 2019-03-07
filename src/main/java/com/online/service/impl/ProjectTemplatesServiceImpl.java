package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.ProjectTemplatesDao;
import com.online.entity.DictionaryData;
import com.online.entity.online.ProjectModuleDetail;
import com.online.entity.online.ProjectModuleInfo;
import com.online.entity.online.ProjectTemplates;
import com.online.service.ProjectModuleDetailService;
import com.online.service.ProjectModuleInfoService;
import com.online.service.ProjectTemplatesService;
import com.online.service.SelectService;
/**
 * 
 * @author 左志平
 * 
 * 项目模板服务实现
 *
 */
@Service("projectTemplatesServiceImpl")
public class ProjectTemplatesServiceImpl extends BaseServiceImpl<ProjectTemplates, Integer> implements ProjectTemplatesService,SelectService {

    @Autowired
    private ProjectTemplatesDao projectTemplatesDao;
    
    @Autowired
    @Lazy
    private ProjectModuleInfoService projectModuleInfoService;
    @Autowired
    @Lazy
    private ProjectModuleDetailService projectModuleDetailService;
    
    
    
    @Override
    public List<DictionaryData> getSelectData(String param) {
    	List<Filter> filters = new ArrayList<>();
		if(StringUtils.isNotEmpty(param)){
			String[] split = param.split(",");
			for (String str : split) {
				String[] split2 = str.split(":");
				if(split2.length==2){
					filters.add(new Filter(split2[0],Operator.eq,split2[1]));
				}
			}
		}
		List<ProjectTemplates> findAll = findList(null, filters, null);
    	
        List<DictionaryData>  list = new ArrayList<>();
        for (ProjectTemplates projectTemplates : findAll) {
            DictionaryData aData = new DictionaryData();
            aData.setCode(projectTemplates.getId()+"");
            aData.setCodeValue(projectTemplates.getName());
            list.add(aData);
        }
        return list;
    }

	@Override
	public ProjectTemplates findName(String name) {
		// TODO Auto-generated method stub
		//郑有权
		return projectTemplatesDao.findName(name);
	}

	@Override
	public ProjectTemplates saveApply(ProjectTemplates projectTemplates, Integer nameId) {
		//郑有权
		ProjectTemplates projectTemplatesBack = save(projectTemplates);
		
		List<ProjectModuleInfo> projectModuleInfos = projectModuleInfoService.findByFilter(new Filter("projectTemplates", Operator.eq, nameId));
		for(ProjectModuleInfo projectModuleInfo:projectModuleInfos){
			ProjectModuleInfo newProjectModuleInfo = new ProjectModuleInfo();
			newProjectModuleInfo.setProjectTemplates(projectTemplatesBack);
			newProjectModuleInfo.setModuleType(projectModuleInfo.getModuleType());
			newProjectModuleInfo.setName(projectModuleInfo.getName());
			newProjectModuleInfo.setOrder(projectModuleInfo.getOrder());
			newProjectModuleInfo.setProjectId(projectTemplatesBack.getProjectId());
			newProjectModuleInfo.setStatus(projectModuleInfo.getStatus());
			newProjectModuleInfo.setModuleClassification(projectModuleInfo.getModuleClassification());
			ProjectModuleInfo projectModuleInfoBack = projectModuleInfoService.save(newProjectModuleInfo);
			
			List<ProjectModuleDetail> projectModuleDetails =projectModuleInfo.getProjectModuleDetails();
			for(ProjectModuleDetail projectModuleDetail:projectModuleDetails){
				ProjectModuleDetail newprojectModuleDetail = new ProjectModuleDetail();
				newprojectModuleDetail.setProjectModuleInfo(projectModuleInfoBack);
				newprojectModuleDetail.setHead(projectModuleDetail.getHead());
				newprojectModuleDetail.setKey(projectModuleDetail.getKey());
				newprojectModuleDetail.setHeadgroup(projectModuleDetail.getHeadgroup());
				newprojectModuleDetail.setModuleOrder(projectModuleDetail.getModuleOrder());
				newprojectModuleDetail.setProjectId(projectTemplatesBack.getProjectId());
				projectModuleDetailService.save(newprojectModuleDetail);
			}
		
		
		}
		
		
		return projectTemplatesBack;
	}
}
