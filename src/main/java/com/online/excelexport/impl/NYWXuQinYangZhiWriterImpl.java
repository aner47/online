package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a9agriculture.LivestockBreeding;

@Component
public class NYWXuQinYangZhiWriterImpl extends BaseImpl<LivestockBreeding> {

	@Override
	public String getJpql() {
		return "select loading from LivestockBreeding loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
