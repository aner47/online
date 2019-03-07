package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a1constructioncommittee.HouseBuildSite;

@Component
public class ZJWFangWuJianZhuGongDiWriterImpl extends BaseImpl<HouseBuildSite> {

	@Override
	public String getJpql() {
		return "select loading from HouseBuildSite loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
