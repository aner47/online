package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.SysProductIndustry;
import com.online.entity.online.Project;
import com.online.service.ProjectService;
import com.online.service.SelectService;
import com.online.service.SysProductIndustryService;
/**
 * 产品行业
 */
@Service("sysProductIndustryServiceImpl")
public class SysProductIndustryServiceImpl extends BaseServiceImpl<SysProductIndustry, Integer> 
	implements SysProductIndustryService,SelectService {
	
	@Autowired
	private ProjectService projectService;
	
	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<Filter> filters = new ArrayList<>();
		List<DictionaryData> datas = new ArrayList<>();
		if(StringUtils.isNotEmpty(param)){
			String[] split = param.split(",");
			for (String str : split) {
				String[] split2 = str.split(":");
				if(split2.length==2){
					//如果是通过项目查询，单独处理
					if("project.id".equals(split2[0])){
						Project project = projectService.find(Integer.parseInt(split2[1]));
						String sysProductIndustrys =  project.getSysProductIndustrys();
						if(sysProductIndustrys != null){
							String[] splits = sysProductIndustrys.split(",");
							List<Integer> ids = new ArrayList<>();
							for (int j = 0; j < splits.length; j++) {
								ids.add(Integer.parseInt(splits[j]));
							}
							filters.add(new Filter("id",Operator.in,ids));
						}
						
						
					}
					else{
						filters.add(new Filter(split2[0],Operator.eq,split2[1]));
					}
					
				}
			}
		}
		List<SysProductIndustry> findList = findList(null, filters, null);
		if (findList != null) {
			findList.forEach((f) -> {
				DictionaryData aData = new DictionaryData();
				aData.setCode(f.getIndustryValue());
				aData.setCodeValue(f.getIndustryName());
				datas.add(aData);
			});
		}
		return datas;
	}

	
	
}