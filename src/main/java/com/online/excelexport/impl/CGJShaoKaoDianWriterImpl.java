package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a2urbanmanagementbureau.BarbecueRestaurant;

@Component
public class CGJShaoKaoDianWriterImpl extends BaseImpl<BarbecueRestaurant> {

	@Override
	public String getJpql() {
		return "select loading from BarbecueRestaurant loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
