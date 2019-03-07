package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.Kiln;

/**
 * 锅炉信息
 * @author DEV2
 *
 */
@Component
public class KilnWriterImpl extends BaseImpl<Kiln> {

	@Override
	public String getJpql() {
		return "select kiln from Kiln kiln where kiln.project.id=:projectId and kiln.enterpriseEmissionsManagement.enterprise.id=:enterpriseId";
	}



	


	
	 

}
