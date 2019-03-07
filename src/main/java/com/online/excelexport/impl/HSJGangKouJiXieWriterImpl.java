package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a10marineboard.PortMechanical;

@Component
public class HSJGangKouJiXieWriterImpl extends BaseImpl<PortMechanical> {

	@Override
	public String getJpql() {
		return "select loading from PortMechanical loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
