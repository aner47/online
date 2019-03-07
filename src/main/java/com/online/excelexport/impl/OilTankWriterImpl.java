package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.OilTank;

@Component
public class OilTankWriterImpl extends BaseImpl<OilTank> {

	@Override
	public String getJpql() {
		return "select oilTank from OilTank oilTank where oilTank.project.id=:projectId and oilTank.enterprise.id=:enterpriseId";
	}

}
