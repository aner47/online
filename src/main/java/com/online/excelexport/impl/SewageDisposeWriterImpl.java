package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.SewageDispose;

@Component
public class SewageDisposeWriterImpl extends BaseImpl<SewageDispose> {

	@Override
	public String getJpql() {
		return "select loading from SewageDispose loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
