package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.ProjectDao;
import com.online.entity.DictionaryData;
import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.online.Project;
import com.online.service.ProjectEnterpriseUserService;
import com.online.service.ProjectService;
import com.online.service.SelectService;
/**
 * 
 * @author 左志平
 * 
 * 项目服务实现
 *
 */
@Service("projectServiceImpl")
public class ProjectServiceImpl extends BaseServiceImpl<Project, Integer> implements ProjectService,SelectService {
	
	@Autowired
	@Lazy
	private ProjectEnterpriseUserService projectEnterpriseUserService;
	
	@Autowired
	private ProjectDao projectDao;
	@Override
	public Project findProjectByInvitationCode(String invitationCode) {
		return projectDao.findProjectByInvitationCode(invitationCode);
	}
	@Override
    public List<DictionaryData> getSelectData(String param) {
		 List<Project> findAll = null;
		if(StringUtils.isNotBlank(param)&&param.indexOf("assessor") != -1){
			String[] params = param.split(":");
			List<ProjectEnterpriseUser> findAllProject = projectEnterpriseUserService.findByFilter(new Filter("assessor", Operator.eq, params[1]));
			findAll = findAllProject.stream().map(o1->o1.getProject()).collect(Collectors.toSet()).stream().collect(Collectors.toList());
		}else{
			findAll = findAll();
		}
         
         
        List<DictionaryData>  list = new ArrayList<>();
        for (Project Project : findAll) {
            DictionaryData aData = new DictionaryData();
            aData.setCode(Project.getId()+"");
            aData.setCodeValue(Project.getName());
            list.add(aData);
        }
        return list;
    }
	@Override
	public Project findProjectByInvitationCode(String invitationCode, boolean isPastDate) {
		return projectDao.findProjectByInvitationCode(invitationCode,isPastDate);
	}
	
}
