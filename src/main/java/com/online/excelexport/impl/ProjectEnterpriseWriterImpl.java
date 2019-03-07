package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.ProjectEnterpriseUser;

@Component
public class ProjectEnterpriseWriterImpl extends BaseImpl<ProjectEnterpriseUser> {

	@Override
	public String getJpql() {
		return "select loading from ProjectEnterpriseUser loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
