package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.online.fieldset.FieldModule;
import com.online.service.DictionaryService;
import com.online.service.FieldModuleService;
import com.online.service.SelectService;
import com.online.util.SpringUtils;
/**
 * 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年1月16日 下午3:29:02 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Service("fieldModuleServiceImpl")
public class FieldModuleServiceImpl extends BaseServiceImpl<FieldModule, Integer> implements FieldModuleService,SelectService  {

	
	@Autowired
	public DictionaryService dictionaryService;
	
	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<FieldModule> findAll = null;
		if(StringUtils.isNotBlank(param)){
			String[]  params = param.split(":");
			findAll = findByFilter(new Filter(params[0], Operator.eq, params[1]));
		}else{
			findAll = findAll();
		}
        List<DictionaryData>  list = new ArrayList<>();
        findAll = findAll.stream().sorted((a,b)->a.getOrders().compareTo(b.getOrders())).collect(Collectors.toList());
        if(findAll != null){
        	for (FieldModule fieldModule : findAll) {
                DictionaryData aData = new DictionaryData();
                aData.setCode(fieldModule.getId()+"");
                aData.setCodeValue(fieldModule.getName());
                list.add(aData);
            }
        }
        
        return list;
	}

	@Override
	public FieldModule findByNameTableType(String name, String tableType) {
		
		 List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("name", Operator.eq, name));
		filters.add(new Filter("tableType", Operator.eq, tableType));
		
		List<FieldModule> fieldModules = findList(null, filters, null);
		if(fieldModules != null && fieldModules.size()>0){
			return fieldModules.get(0);
		}
		//郑有权
		return null;
	}

	@Override
	public FieldModule findByNameTableType(String name) {
		if(SpringUtils.getCurrentEnterprise() != null ){
			//郑有权
			return findByNameTableType(name, SpringUtils.getCurrentEnterprise().getEnterpriseType().toString());
		}
		
		return null;
	}
	
	
}