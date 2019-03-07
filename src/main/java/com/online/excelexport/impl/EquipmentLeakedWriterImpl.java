package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.EquipmentLeaked;

@Component
public class EquipmentLeakedWriterImpl extends BaseImpl<EquipmentLeaked> {

	@Override
	public String getJpql() {
		return "select equipmentLeaked from EquipmentLeaked equipmentLeaked where equipmentLeaked.project.id=:projectId and equipmentLeaked.enterprise.id=:enterpriseId";
	}

}
