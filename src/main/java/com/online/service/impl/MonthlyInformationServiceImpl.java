package com.online.service.impl;



import java.util.List;

import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.online.MonthlyInformation;
import com.online.service.MonthlyInformationService;
import com.online.util.Constants;
/**
 * 
 * @author 左志平
 * 
 * 分月数据服务实现
 *
 */
@Service("monthlyInformationServiceImpl")
public class MonthlyInformationServiceImpl extends BaseServiceImpl<MonthlyInformation, Integer> implements MonthlyInformationService {

	@Override
	public void deleteByType(String type,Integer powerId) {
		
		Filter filter = null;
		
		switch (type) {
			case Constants.MONTH_TYPE_POWER:
				filter = new Filter("powerPlantId", Operator.eq, powerId);
				break;
			case Constants.MONTH_TYPE_BOILER:
				filter = new Filter("boilerId", Operator.eq, powerId);
				break;
			case Constants.MONTH_TYPE_GASSTOVE:
				filter = new Filter("gasstoveId", Operator.eq, powerId);
				break;
			case Constants.MONTH_TYPE_KILN:
				filter = new Filter("kilnId", Operator.eq, powerId);
				break;
			case Constants.MONTH_TYPE_SECTION:
				filter = new Filter("sectionId", Operator.eq, powerId);
				break;
			case Constants.MONTH_TYPE_RAWMATERIALS:
				filter = new Filter("rawMaterialsId", Operator.eq, powerId);
				break;
	
			default:
				break;
		}
		
		//郑有权
		List<MonthlyInformation> monthlyInformations =findByFilter(filter);
		for(MonthlyInformation monthlyInformation:monthlyInformations){
			delete(monthlyInformation); 
		}
	}
	
//	@Autowired
//	private BoilerInformationService boilerInformationService;
	
//	@Override
//	public List<DictionaryData> getSelectData(String param) {
//		List<Filter> filters = new ArrayList<>();
//		filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
//		List<T> findList = null;
//		if(param.equals("boilerInformation")){
//			findList =  (List<BoilerInformation>)(boilerInformationService.findList(null, filters, null));
//		}
//		
//		
//		List<DictionaryData>  list = new ArrayList<>();
//		for (ExhaustionHole g : findList) {
//			DictionaryData aData = new DictionaryData();
//			aData.setCode(g.getId().toString());
//			aData.setCodeValue("P"+g.getExhaustionHoleNum());
//			list.add(aData);
//		}
//		return list;
//	}
	
}
