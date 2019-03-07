package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.online.ProductStandardName;
import com.online.service.ProductStandardNameService;
import com.online.service.SelectService;
/**
 * 
 * @author 左志平
 * 
 * 产品标准名称配置服务实现
 *
 */
@Service("productStandardNameServiceImpl")
public class ProductStandardNameServiceImpl extends BaseServiceImpl<ProductStandardName, Integer> implements ProductStandardNameService,SelectService {

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
        List<ProductStandardName> findList = findList(null, filters, null);
        List<DictionaryData> list = new ArrayList<>();
        for (ProductStandardName productOtherName : findList) {
            DictionaryData aData = new DictionaryData();
            aData.setCode(productOtherName.getId() + "");
            aData.setCodeValue(productOtherName.getStandardName());
            list.add(aData);
        }
        return list;
    }
    
    @Override
    public ProductStandardName save(ProductStandardName entity) {
    	//郑有权
    	entity.setStatus("unsynchronized");
    	return super.save(entity);
    }
    
    @Override
    public ProductStandardName update(ProductStandardName entity) {
    	//郑有权
    	entity.setStatus("unsynchronized");
    	return super.update(entity);
    }

}
