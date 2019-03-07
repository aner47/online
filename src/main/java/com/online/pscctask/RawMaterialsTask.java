package com.online.pscctask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.FilterGroup;
import com.online.FilterGroup.JoinChar;
import com.online.entity.online.RawMaterialsOtherName;
import com.online.entity.online.RawMaterialsStandardName;
import com.online.entity.online.RawMeterialsUnitConfig;
import com.online.entity.online.TaskList;
import com.online.service.RawMaterialsOtherNameService;
import com.online.service.RawMaterialsStandardNameService;
import com.online.service.RawMeterialsUnitConfigService;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年9月28日 上午11:44:25 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
@Component
public class RawMaterialsTask implements ITaskListInsert{
	
	@Autowired
	RawMaterialsStandardNameService rawMaterialsStandardNameService;
	@Autowired
	RawMeterialsUnitConfigService rawMeterialsUnitConfigService;
	@Autowired
	RawMaterialsOtherNameService rawMaterialsOtherNameService;
	
	@Override
	public void insertConfig(TaskList taskList) {
		// TODO Auto-generated method stub
		//郑有权
		RawMaterialsOtherName rawMaterialsOtherName = new RawMaterialsOtherName();
		rawMaterialsOtherName.setOtherName(taskList.getDataName());
		rawMaterialsOtherName.setIndustryCode(taskList.getIndustryCode());
		
		//根据标准名称和pscc判断标准名称是否存在
		/*List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("standardName", Operator.eq, taskList.getStandardName()));
		List<Filter> filters1 = new ArrayList<>();
		filters1.add(new Filter("pscc", Operator.eq, taskList.getPscc()));
		*/
		FilterGroup filterGroup = new FilterGroup();
		filterGroup.setJoinChar(JoinChar.or);
		filterGroup.addFilter(new Filter("standardName", Operator.eq, taskList.getStandardName()));
		filterGroup.addFilter(new Filter("pscc", Operator.eq, taskList.getPscc()));
		
		
		
		RawMaterialsStandardName rawMaterialsStandardName = null;
//		List<RawMaterialsStandardName> rawMaterialsStandardNames = rawMaterialsStandardNameService.findList(null, filters, null);
		List<RawMaterialsStandardName> rawMaterialsStandardNames = rawMaterialsStandardNameService.findComplexFilter(null, null, filterGroup, null);
		 //标准名称存在
		 if(rawMaterialsStandardNames!= null && rawMaterialsStandardNames.size()>0){
			 rawMaterialsStandardName = rawMaterialsStandardNames.get(0);
		 }else{
			 //不存在则插入标准名称
			 RawMaterialsStandardName  entity = new RawMaterialsStandardName();
			 entity.setDefaultConversionFactor(taskList.getConversionFactor());
			 entity.setDefaultUnit(taskList.getDataUnit());
			 entity.setIndustryCode(taskList.getIndustryCode());
			 entity.setPscc(taskList.getPscc());
			 entity.setStandardName(taskList.getStandardName());
			 entity.setStandardUnit(taskList.getDataUnit());
			 rawMaterialsStandardName = rawMaterialsStandardNameService.save(entity);
		 }
		
		 rawMaterialsOtherName.setRawMaterialsStandardName(rawMaterialsStandardName);
		
		 
		//根据名称和单位判断标准单位是否存在
		Set<RawMeterialsUnitConfig> rawMeterialsUnitConfigsets = new HashSet<>();
		List<Filter> unitfilters = new ArrayList<>();
		unitfilters.add(new Filter("rawMaterialsOtherName.otherName", Operator.eq, taskList.getDataName()));
		unitfilters.add(new Filter("nameCh", Operator.eq, taskList.getDataUnit()));
		List<RawMeterialsUnitConfig> rawMeterialsUnitConfigs = rawMeterialsUnitConfigService.findList(null, unitfilters, null);
		if(rawMeterialsUnitConfigs != null && rawMeterialsUnitConfigs.size()>0){
			for(RawMeterialsUnitConfig rawMeterialsUnitConfig:rawMeterialsUnitConfigs){
				rawMeterialsUnitConfigsets.add(rawMeterialsUnitConfig);
			}
			rawMaterialsOtherName.setRawMeterialsUnitConfigs(rawMeterialsUnitConfigsets);
			
			rawMaterialsOtherNameService.save(rawMaterialsOtherName);
			
		}else{
			RawMeterialsUnitConfig entity = new RawMeterialsUnitConfig();
			entity.setRawMaterialsOtherName(rawMaterialsOtherNameService.save(rawMaterialsOtherName));
			entity.setConversionFactor(taskList.getConversionFactor());
			entity.setNameCh(taskList.getDataUnit());
			rawMeterialsUnitConfigService.save(entity);
		}
		
		
	}

	@Override
	public boolean isExist(TaskList taskList) {
		//郑有权
		//通过名称和行业代码判断是否存在
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("otherName", Operator.eq, taskList.getDataName()));
		filters.add(new Filter("industryCode", Operator.eq, taskList.getIndustryCode()));
		
		List<RawMaterialsOtherName> lists = rawMaterialsOtherNameService.findList(null, filters, null);
		if(lists != null && lists.size()>0){
			return true;
		}
		
		return false;
	}

}
