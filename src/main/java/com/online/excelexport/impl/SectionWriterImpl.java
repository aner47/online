package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.Section;

@Component
public class SectionWriterImpl extends BaseImpl<Section> {

	@Override
	public String getJpql() {
		return "select section from Section section where section.project.id=:projectId and section.enterpriseEmissionsManagement.enterprise.id=:enterpriseId";
	}

}
