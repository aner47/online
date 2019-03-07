package com.online.excelexport.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.online.entity.online.RawMaterials;
import com.online.entity.online.Section;

@Component
public class RawMaterialsWriterImpl extends BaseImpl<RawMaterials> {

	@Override
	public String getJpql() {
//		return "select loading from RawMaterials loading where rawMaterialsType='organic' and loading.project=:projectId and loading.enterprise=:enterpriseId";
//		return "select loading from RawMaterials loading where  loading.sectionId=:sectionId and loading.project=:projectId and loading.enterprise=:enterpriseId";
		return "select loading from RawMaterials loading where loading.project=:projectId and loading.enterprise=:enterpriseId and loading.sectionId is not null";
	}
	
	@Override
	public String transtranlate(String value, String fieldName) {
		if(StringUtils.isEmpty(value))return "";
		if("sectionId".equals(fieldName)&&StringUtils.isNotEmpty(value)&&StringUtils.isNumeric(value)){
			Section section = entityManager.find(Section.class, Integer.parseInt(value));
			if(section == null){return "";};
			return section.getName();
		}
		if("rawMaterialsType".equals(fieldName)){
			switch (value) {
			case "organic":
				return "有机溶剂";
			case "inorganic":
				return "无机溶剂";
			default:
				break;
			}
		}
		// TODO Auto-generated method stub
		return super.transtranlate(value, fieldName);
	}

}
