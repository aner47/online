package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.online.ProductOtherName;
import com.online.entity.online.ProductStandardName;
import com.online.service.ProductOtherNameService;
import com.online.service.ProductStandardNameService;
import com.online.service.SelectService;
/**
 * 
 * @author 左志平
 * 
 * 产品名称配置服务实现
 *
 */
@Service("productOtherNameServiceImpl")
public class ProductOtherNameServiceImpl extends BaseServiceImpl<ProductOtherName, Integer> implements ProductOtherNameService,SelectService{

    @Autowired
    private ProductStandardNameService productStandardNameService;
    
    
    @Transactional
    public ProductOtherName save(ProductOtherName productOtherName, String pds) {
        if (StringUtils.isNotEmpty(pds)) {
            int parseInt = Integer.parseInt(pds);
            ProductStandardName productStandard = productStandardNameService.find(parseInt);
            productOtherName.setProductStandardName(productStandard);
            productOtherName.setStatus("unsynchronized");
         }
        return super.save(productOtherName);
        
    }
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
        List<ProductOtherName> findList = findList(null, filters, null);
        List<DictionaryData> list = new ArrayList<>();
        for (ProductOtherName productOtherName : findList) {
            DictionaryData aData = new DictionaryData();
            aData.setCode(productOtherName.getId() + "");
            aData.setCodeValue(productOtherName.getOtherName());
            list.add(aData);
        }
        return list;
    }
    
    @Override
    public ProductOtherName update(ProductOtherName entity, String pds) {
    	//郑有权
    	if (StringUtils.isNotEmpty(pds)) {
            int parseInt = Integer.parseInt(pds);
            ProductStandardName productStandard = productStandardNameService.find(parseInt);
            entity.setProductStandardName(productStandard);
            entity.setStatus("unsynchronized");
         }
    	return super.update(entity);
    }
	
}
