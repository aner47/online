package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.beastsbirds.BeastsBirds;

@Component
public class BeastsBirdsWriterImpl extends BaseImpl<BeastsBirds> {

	@Override
	public String getJpql() {
		return "select beastsBirds from BeastsBirds beastsBirds where beastsBirds.project.id=:projectId and beastsBirds.enterprise.id=:enterpriseId";
	}

}
