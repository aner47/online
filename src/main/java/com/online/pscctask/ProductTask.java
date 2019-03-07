package com.online.pscctask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.online.Filter;
import com.online.FilterGroup;
import com.online.Filter.Operator;
import com.online.FilterGroup.JoinChar;
import com.online.entity.online.ProductOtherName;
import com.online.entity.online.ProductStandardName;
import com.online.entity.online.ProductUnitConfig;
import com.online.entity.online.RawMaterialsStandardName;
import com.online.entity.online.TaskList;
import com.online.service.ProductOtherNameService;
import com.online.service.ProductStandardNameService;
import com.online.service.ProductUnitConfigService;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年9月28日 上午11:42:36 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
@Component
public class ProductTask implements ITaskListInsert{
	
	@Autowired
    private ProductStandardNameService productStandardNameService;
    @Autowired
    private ProductUnitConfigService productUnitConfigService;
    @Autowired
    private ProductOtherNameService productOtherNameService;
	
	
	@Override
	public void insertConfig(TaskList taskList) {
		// TODO Auto-generated method stub
		//郑有权
		ProductOtherName productOtherName = new ProductOtherName();
		productOtherName.setOtherName(taskList.getDataName());
		productOtherName.setIndustryCode(taskList.getIndustryCode());
		
		//根据标准名称和pscc判断标准名称是否存在
		/*List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("standardName", Operator.eq, taskList.getStandardName()));
		filters.add(new Filter("pscc", Operator.eq, taskList.getPscc()));*/
		
		FilterGroup filterGroup = new FilterGroup();
		filterGroup.setJoinChar(JoinChar.or);
		filterGroup.addFilter(new Filter("standardName", Operator.eq, taskList.getStandardName()));
		filterGroup.addFilter(new Filter("pscc", Operator.eq, taskList.getPscc()));
		
		ProductStandardName productStandardName = null;
//		 List<ProductStandardName> productStandardNames = productStandardNameService.findList(null, filters, null);
		List<ProductStandardName> productStandardNames = productStandardNameService.findComplexFilter(null, null, filterGroup, null);
		//标准名称存在
		 if(productStandardNames!= null && productStandardNames.size()>0){
			 productStandardName = productStandardNames.get(0);
		 }else{
			 //不存在则插入标准名称
			 ProductStandardName  entity = new ProductStandardName();
			 if(entity != null){
				 entity.setDefaultConversionFactor(taskList.getConversionFactor());
				 entity.setDefaultUnit(taskList.getDataUnit());
				 entity.setIndustryCode(taskList.getIndustryCode());
				 entity.setPscc(taskList.getPscc());
				 entity.setStandardName(taskList.getStandardName());
				 entity.setStandardUnit(taskList.getDataUnit());
				 productStandardName = productStandardNameService.save(entity);
			 }
			 
		 }
		
		productOtherName.setProductStandardName(productStandardName);
		
		
		//根据名称和单位判断标准单位是否存在
		Set<ProductUnitConfig> productUnitConfigsets = new HashSet<>();
		List<Filter> unitfilters = new ArrayList<>();
		unitfilters.add(new Filter("productOtherName.otherName", Operator.eq, taskList.getDataName()));
		unitfilters.add(new Filter("nameCh", Operator.eq, taskList.getDataUnit()));
		List<ProductUnitConfig> productUnitConfigs = productUnitConfigService.findList(null, unitfilters, null);
		if(productUnitConfigs != null && productUnitConfigs.size()>0){
			for(ProductUnitConfig productUnitConfig:productUnitConfigs){
				productUnitConfigsets.add(productUnitConfig);
			}
			productOtherName.setProductUnitConfigs(productUnitConfigsets);
			
			productOtherNameService.save(productOtherName);
		}else{
			ProductUnitConfig entity = new ProductUnitConfig();
			entity.setProductOtherName(productOtherNameService.save(productOtherName));
			entity.setConversionFactor(taskList.getConversionFactor());
			entity.setNameCh(taskList.getDataUnit());
			productUnitConfigService.save(entity);
		}
		
		
		
		
	}
	@Override
	public boolean isExist(TaskList taskList) {
		//郑有权
		//通过名称和行业代码判断是否存在
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("otherName", Operator.eq, taskList.getDataName()));
		filters.add(new Filter("industryCode", Operator.eq, taskList.getIndustryCode()));
		
		List<ProductOtherName> lists = productOtherNameService.findList(null, filters, null);
		if(lists != null && lists.size()>0){
			return true;
		}
		
		return false;
	}

}
