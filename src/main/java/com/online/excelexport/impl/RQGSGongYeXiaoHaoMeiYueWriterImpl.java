package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a17fuelgas.EnterpriseFuelGasMonth;

@Component
public class RQGSGongYeXiaoHaoMeiYueWriterImpl extends BaseImpl<EnterpriseFuelGasMonth> {

	@Override
	public String getJpql() {
		return "select loading from EnterpriseFuelGasMonth loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
