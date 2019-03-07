package com.online.service.impl.a7trafficpolice;



import org.springframework.stereotype.Service;

import com.online.entity.online.a7trafficpolice.TrafficCarWeight;
import com.online.service.a7trafficpolice.TrafficCarWeightService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 车重信息服务实现
 *
 */
@Service("trafficCarWeightServiceImpl")
public class TrafficCarWeightServiceImpl extends BaseServiceImpl<TrafficCarWeight, Integer> implements TrafficCarWeightService {
	
	
	@Override
	public TrafficCarWeight saveTrafficCarWeight(TrafficCarWeight trafficCarWeight) throws Exception {
		trafficCarWeight.setProject(SpringUtils.getCurrentProject());
		return save(trafficCarWeight);
	}


	@Override
	public TrafficCarWeight updateTrafficCarWeight(TrafficCarWeight trafficCarWeight) throws Exception {
		//郑有权
		
		return update(trafficCarWeight,"project");
	}
	
}
