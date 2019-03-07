package com.online.service.impl.a14caa;



import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.entity.online.Enterprise;
import com.online.entity.online.a14caa.AirplaneType;
import com.online.entity.online.a14caa.CivilAviation;
import com.online.service.a14caa.AirplaneTypeService;
import com.online.service.impl.BaseServiceImpl;
/**
 * 
 * 飞机机型服务实现
 *
 */
@Service("airplaneTypeServiceImpl")
public class AirplaneTypeServiceImpl extends BaseServiceImpl<AirplaneType, Integer> implements AirplaneTypeService {
	
	
	@Override
	public AirplaneType saveAirplaneType(AirplaneType airplaneType) throws Exception {
		
		return save(airplaneType);
	}


	@Override
	public AirplaneType updateAirplaneType(AirplaneType airplaneType) throws Exception {
		//郑有权
		
		return update(airplaneType,"project");
	}


	@Override
	public void updateEnterprise(CivilAviation civilAviation, Enterprise enterprise) throws Exception {
		//郑有权
		List<AirplaneType> lists = findList(null, Arrays.asList(Filter.eq("civilAviation.id", civilAviation.getId())), null);
		if(lists != null && lists.size()>0){
			for(AirplaneType airplaneType:lists){
				airplaneType.setEnterprise(enterprise);
				update(airplaneType);
			}
		}
		
		
	}
	
}
