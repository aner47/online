package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.constructionsite.ConstructionMember;

@Component
public class ConstructionMemberWriterImpl extends BaseImpl<ConstructionMember> {

	@Override
	public String getJpql() {
		return "select loading from ConstructionMember loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
