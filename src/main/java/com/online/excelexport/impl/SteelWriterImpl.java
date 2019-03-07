package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.Steel;

@Component
public class SteelWriterImpl extends BaseImpl<Steel> {

	@Override
	public String getJpql() {
		return "select loading from Steel loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
