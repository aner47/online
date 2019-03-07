package com.online.service.impl.a14caa;



import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.entity.online.Enterprise;
import com.online.entity.online.a14caa.AirplaneType;
import com.online.entity.online.a14caa.CivilAviation;
import com.online.service.EnterpriseService;
import com.online.service.a14caa.AirplaneTypeService;
import com.online.service.a14caa.CivilAviationService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 民航飞机信息服务实现
 *
 */
@Service("civilAviationServiceImpl")
public class CivilAviationServiceImpl extends BaseServiceImpl<CivilAviation, Integer> implements CivilAviationService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Autowired
	private AirplaneTypeService airplaneTypeService;
	
	@Override
	public CivilAviation saveCivilAviation(CivilAviation civilAviation) throws Exception {
		//郑有权
		String enterpriseName = civilAviation.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		civilAviation.setEnterprise(enterprise);
		civilAviation.setProject(SpringUtils.getCurrentProject());
		return save(civilAviation);
	}


	@Override
	public CivilAviation updateCivilAviation(CivilAviation civilAviation) throws Exception {
		//郑有权
		String enterpriseName = civilAviation.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		civilAviation.setEnterprise(enterprise);
		CivilAviation  civilAviationBack = update(civilAviation,"project","totalOffLand","january","february","march"
					,"april","may","june","july","august","september","october","november","december");
		
		airplaneTypeService.updateEnterprise(civilAviationBack ,enterprise);
		return civilAviationBack;
		
		
	}


	@Override
	public CivilAviation updateAirplanetype(CivilAviation civilAviation) throws Exception {
		//郑有权
		CivilAviation civilAviationBack = update(civilAviation,"project","enterprise","houseNumber","longitude","latitude","passengerCapacity","commodityCapacity","airplaneTypes");
		
		List<AirplaneType> airplaneTypes = civilAviation.getAirplaneTypes();
		if(airplaneTypes != null && airplaneTypes.size() >0){
			for (AirplaneType airplaneType:airplaneTypes) {
				if(airplaneType.getId() == null){
					airplaneType.setCivilAviation(civilAviationBack);
					airplaneType.setEnterprise(civilAviationBack.getEnterprise());
					airplaneType.setProject(SpringUtils.getCurrentProject());
					airplaneTypeService.save(airplaneType);
				}else{
					airplaneType.setEnterprise(civilAviationBack.getEnterprise());
					airplaneTypeService.update(airplaneType,"project","civilAviation");
				}
			}
		}
		return  civilAviationBack;
	}
	
}
