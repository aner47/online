package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.online.fieldset.FieldConfig;
import com.online.service.FieldConfigService;
import com.online.service.FieldShowConfigService;
import com.online.service.SelectService;
/**
 * 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年1月16日 下午3:29:02 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Service("fieldConfigServiceImpl")
public class FieldConfigServiceImpl extends BaseServiceImpl<FieldConfig, Integer> implements FieldConfigService ,SelectService {

		@Autowired
		private FieldShowConfigService fieldShowConfigService;
	
		@Override
		public List<DictionaryData> getSelectData(String param) {
			List<FieldConfig> findAll = null;
			if(StringUtils.isNotBlank(param)){
				String[]  params = param.split(":");
				findAll = findByFilter(new Filter(params[0], Operator.eq, params[1]));
			}else{
				findAll = findAll();
			}
	        List<DictionaryData>  list = new ArrayList<>();
	        if(findAll != null){
	        	for (FieldConfig FieldConfig : findAll) {
	                DictionaryData aData = new DictionaryData();
	                aData.setCode(FieldConfig.getValue());
	                aData.setCodeValue(FieldConfig.getName());
	                list.add(aData);
	            }
	        }
	        
	        return list;
		}

		@Override
		public void deleteFieldConfig(Integer moduleId, Integer id) {
			//郑有权
			FieldConfig fieldConfig = find(id);
			String value = fieldConfig.getValue();
			
			fieldShowConfigService.removeField(moduleId,value);
			delete(id);
		}
	
	
}