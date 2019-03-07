package com.online.service.impl;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.online.ModuleInfo;
import com.online.service.ModuleInfoService;
import com.online.service.SelectService;
/**
 * 
 * @author 左志平
 * 
 * 模块信息服务实现
 *
 */
@Service("moduleInfoServiceImpl")
public class ModuleInfoServiceImpl extends BaseServiceImpl<ModuleInfo, Integer> implements SelectService, ModuleInfoService {
	
    @Override
    public List<DictionaryData> getSelectData(String param) {
        List<Filter> filters = new ArrayList<>();
        if(StringUtils.isNotEmpty(param)){
            String[] split = param.split(",");
            for (String str : split) {
                String[] split2 = str.split(":");
                filters.add(new Filter(split2[0],Operator.eq,split2[1]));
            }
        }
        
       
        List<ModuleInfo> findList = findList(null, filters, null);
        Collections.sort(findList,(o1,o2)->o1.getOrder()-o2.getOrder());
        List<DictionaryData>  list = new ArrayList<>();
//        List<Filter> enterprise = new ArrayList<>();
//        enterprise.add(new Filter("moduleType",Operator.eq,ExcelExprotConstants.MODULE_TYPE_ENTERPRISE));
//        List<ModuleInfo> enterpriseList = findList(null, enterprise, null);
//        for (ModuleInfo moduleInfo : enterpriseList) {
//            DictionaryData aData = new DictionaryData();
//            aData.setCode(moduleInfo.getId()+"");
//            aData.setCodeValue(moduleInfo.getName());
//            list.add(aData);
//		}
        for (ModuleInfo moduleInfo : findList) {
            DictionaryData aData = new DictionaryData();
            aData.setCode(moduleInfo.getId()+"");
            aData.setCodeValue(moduleInfo.getName());
            list.add(aData);
        }

        
        return list;
    }
    
}
