package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a14caa.AirportMachinery;

@Component
public class MHJJiChangCheLiangWriterImpl extends BaseImpl<AirportMachinery> {

	@Override
	public String getJpql() {
		return "select loading from AirportMachinery loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
