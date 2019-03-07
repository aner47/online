package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a1constructioncommittee.CityMaterialsStation;

@Component
public class ZJWCaiLiaoZhanWriterImpl extends BaseImpl<CityMaterialsStation> {

	@Override
	public String getJpql() {
		return "select loading from CityMaterialsStation loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
