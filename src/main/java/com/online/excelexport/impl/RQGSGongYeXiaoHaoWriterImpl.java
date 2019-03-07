package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a17fuelgas.EnterpriseFuelGas;

@Component
public class RQGSGongYeXiaoHaoWriterImpl extends BaseImpl<EnterpriseFuelGas> {

	@Override
	public String getJpql() {
		return "select loading from EnterpriseFuelGas loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
