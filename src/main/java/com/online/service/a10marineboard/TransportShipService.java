package com.online.service.a10marineboard;

import com.online.entity.online.a10marineboard.TransportShip;
import com.online.service.BaseService;

/**
 * 客货运输船舶活动信息服务接口
 *
 */
public interface TransportShipService extends BaseService<TransportShip, Integer> {
		
		public TransportShip saveTransportShip(TransportShip transportShip,Integer enterpriseId) throws Exception;
		public TransportShip updateTransportShip(TransportShip transportShip,Integer enterpriseId) throws Exception;
	
}