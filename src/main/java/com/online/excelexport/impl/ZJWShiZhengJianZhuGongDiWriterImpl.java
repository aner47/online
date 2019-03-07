package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a1constructioncommittee.CityBuildSite;

@Component
public class ZJWShiZhengJianZhuGongDiWriterImpl extends BaseImpl<CityBuildSite> {

	@Override
	public String getJpql() {
		return "select loading from CityBuildSite loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
