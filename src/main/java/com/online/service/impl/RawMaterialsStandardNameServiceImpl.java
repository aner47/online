package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.online.RawMaterialsStandardName;
import com.online.service.RawMaterialsStandardNameService;
import com.online.service.SelectService;
/**
 * 
 * @author 左志平
 * 
 * 原辅料标准名称配置服务实现
 *
 */
@Service("rawMaterialsStandardNameServiceImpl")
public class RawMaterialsStandardNameServiceImpl extends BaseServiceImpl<RawMaterialsStandardName, Integer> implements RawMaterialsStandardNameService,SelectService {

	
	
	
	
    @Override
    public List<DictionaryData> getSelectData(String param) {
        List<Filter> filters = new ArrayList<>();
        if (StringUtils.isNotEmpty(param)) {
            String[] split = param.split(",");
            for (String str : split) {
                String[] split2 = str.split(":");
                filters.add(new Filter(split2[0], Operator.eq, split2[1]));
            }
        }
        List<RawMaterialsStandardName> findList = findList(null, filters, null);
        List<DictionaryData> list = new ArrayList<>();
        for (RawMaterialsStandardName productOtherName : findList) {
            DictionaryData aData = new DictionaryData();
            aData.setCode(productOtherName.getId() + "");
            aData.setCodeValue(productOtherName.getStandardName());
            list.add(aData);
        }
        return list;
    }

	@Override
	public RawMaterialsStandardName save(RawMaterialsStandardName entity) {
		// TODO Auto-generated method stub
		//郑有权
		entity.setStatus("unsynchronized");
		return super.save(entity);
	}
	
	@Override
	public RawMaterialsStandardName update(RawMaterialsStandardName entity) {
		// TODO Auto-generated method stub
		//郑有权
		entity.setStatus("unsynchronized");
		return super.update(entity);
	}
    
}
