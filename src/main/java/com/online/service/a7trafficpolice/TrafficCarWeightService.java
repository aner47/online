package com.online.service.a7trafficpolice;

import com.online.entity.online.a7trafficpolice.TrafficCarWeight;
import com.online.service.BaseService;

/**
 * 车重信息服务接口
 *
 */
public interface TrafficCarWeightService extends BaseService<TrafficCarWeight, Integer> {
		
		public TrafficCarWeight saveTrafficCarWeight(TrafficCarWeight trafficCarWeight) throws Exception;
		public TrafficCarWeight updateTrafficCarWeight(TrafficCarWeight trafficCarWeight) throws Exception;
	
}