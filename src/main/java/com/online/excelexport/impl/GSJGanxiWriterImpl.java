package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a11industrialcommercial.InCoDryClean;

@Component
public class GSJGanxiWriterImpl extends BaseImpl<InCoDryClean> {

	@Override
	public String getJpql() {
		return "select loading from InCoDryClean loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
