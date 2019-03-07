package com.online.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.ProjectType;
import com.online.service.ProjectTypeService;
import com.online.service.SelectService;
import com.online.util.Constants;

/**
 * 项目类型
 */
@Service("projectTypeServiceImpl")
public class ProjectTypeServiceImpl extends BaseServiceImpl<ProjectType, Integer> implements ProjectTypeService ,SelectService{

	

	
	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<Filter> filters = new ArrayList<>();
		List<DictionaryData> datas = new ArrayList<>();
		if(StringUtils.isNotEmpty(param)){
			String[] split = param.split(",");
			for (String str : split) {
				String[] split2 = str.split(":");
				if(split2.length==2){
					if("project".equals(split2[0])){
						filters.add(new Filter("project",Operator.eq,Integer.parseInt(split2[1])));
					}else{
						filters.add(new Filter(split2[0],Operator.eq,split2[1]));
					}
					
					
				}
			}
		}
		List<ProjectType> findList = findList(null, filters, null);
		Set<String> sets = new HashSet<>();
		if (findList != null) {
			//如果参数中包括企业类型，则查询企业类型
			if(param != null&&param.indexOf("enterpriseTypeParam")>0){
				findList.forEach((f) -> {
					sets.add(f.getEnterpriseType());
				});
				sets.forEach((f)->{
					DictionaryData aData = new DictionaryData();
					aData.setCode(f);
					aData.setCodeValue(Constants.maps.get(f));
					datas.add(aData);
				});
			}else{
				findList.forEach((f) -> {
					DictionaryData aData = new DictionaryData();
					aData.setCode(f.getId() + "");
					aData.setCodeValue(f.getProjectType());
					datas.add(aData);
				});
			}
			
			
		}
		return datas;
	}

	
	

}