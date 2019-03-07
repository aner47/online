package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a10marineboard.Port;

@Component
public class HSJGangKouWriterImpl extends BaseImpl<Port> {

	@Override
	public String getJpql() {
		return "select loading from Port loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
