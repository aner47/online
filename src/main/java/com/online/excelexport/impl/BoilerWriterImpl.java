package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.BoilerInformation;

/**
 * 锅炉信息
 * @author DEV2
 *
 */
@Component
public class BoilerWriterImpl extends BaseImpl<BoilerInformation> {


	@Override
	public String getJpql() {
		// TODO Auto-generated method stub
		return "select boiler from BoilerInformation boiler where boiler.project.id=:projectId and boiler.emissionsManagement.enterprise.id=:enterpriseId";
	}
	
	



	


	
	 

}
