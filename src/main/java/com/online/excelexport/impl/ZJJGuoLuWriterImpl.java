package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a16qualitysupervision.BoilerQs;

@Component
public class ZJJGuoLuWriterImpl extends BaseImpl<BoilerQs> {

	@Override
	public String getJpql() {
		return "select loading from BoilerQs loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
