package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.OpenYard;

@Component
public class OpenYardWriterImpl extends BaseImpl<OpenYard> {

	@Override
	public String getJpql() {
		// TODO Auto-generated method stub
		return "select openyard from OpenYard openyard where openyard.project.id=:projectId and openyard.enterprise.id=:enterpriseId";
	}



}
