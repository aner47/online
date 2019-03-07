package com.online.service.impl;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.FieldShowConfigDao;
import com.online.FilterGroup;
import com.online.entity.DictionaryData;
import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.FieldShowConfigService;
import com.online.service.ProjectEnterpriseUserService;
import com.online.service.ProjectService;
import com.online.service.SelectService;
import com.online.util.SpringUtils;
/**
 * 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年1月16日 下午3:29:02 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Service("fieldShowConfigServiceImpl")
public class FieldShowConfigServiceImpl extends BaseServiceImpl<FieldShowConfig, Integer> implements FieldShowConfigService,SelectService {

	
	@Autowired 
	private ProjectService projectService;
	
	@Autowired 
	private ProjectEnterpriseUserService projectEnterpriseUserService;
	
	
	@Autowired
	private FieldShowConfigDao fieldShowConfigDao;
	
	@Override
	public FieldShowConfig findByProjectIdTableTypeFieldModule(Integer projectId, String tableType,
			Integer fieldModule,Integer projectTypeId) {
		FilterGroup filterGroup = new FilterGroup();
		filterGroup.addFilter(new Filter("projectId", Operator.eq, projectId));
		filterGroup.addFilter(new Filter("tableType", Operator.eq, tableType));
		filterGroup.addFilter(new Filter("fieldModule", Operator.eq,fieldModule));
		filterGroup.addFilter(new Filter("projectType.id", Operator.eq,projectTypeId));
		List<FieldShowConfig> fieldShowConfigs = findComplexFilter(null, null, filterGroup, null);
		
		if(fieldShowConfigs != null &&fieldShowConfigs.size()>0){
			return fieldShowConfigs.get(0);
		}
		//郑有权
		return null;
	}

	@Override
	public FieldShowConfig findByProjectIdTableTypeFieldModule(Integer fieldModule) {
		//郑有权
		ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(SpringUtils.getCurrentProject().getId(),
				SpringUtils.getPrincipal().getEnterpriseId());
		Integer projectType = -1;
		if(projectEnterpriseUser != null){
			projectType = projectEnterpriseUser.getProjectType();
		}
		return findByProjectIdTableTypeFieldModule(SpringUtils.getCurrentProject().getId(),
				SpringUtils.getCurrentEnterprise().getEnterpriseType().toString(), 
				fieldModule,projectType);
	}

	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<FieldShowConfig> findAll = null;
		if(StringUtils.isNotBlank(param)){
			String[]  params = param.split(":");
			findAll = findByFilter(new Filter(params[0], Operator.eq, params[1]));
		}else{
			findAll = findAll();
		}
        List<DictionaryData>  list = new ArrayList<>();
        Set<Integer> sets = new HashSet<>();
        
        
        if(findAll != null){
        	for (FieldShowConfig fieldShowConfig : findAll) {
        		sets.add(fieldShowConfig.getProjectId());
                
            }
        }
        
        for (Integer projectid : sets) {
        	DictionaryData aData = new DictionaryData();
            aData.setCode(projectid+"");
            aData.setCodeValue(projectService.find(projectid).getName());
            list.add(aData);
		}
        
        return list;
	}

	@Override
	public void removeField(Integer moduleId, String value) {
		// TODO Auto-generated method stub
		//郑有权
		fieldShowConfigDao.removeField(moduleId, value);
	}
	
	
}