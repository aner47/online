package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a11industrialcommercial.InCoBreakdownService;

@Component
public class GSJQiXiuWriterImpl extends BaseImpl<InCoBreakdownService> {

	@Override
	public String getJpql() {
		return "select loading from InCoBreakdownService loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
