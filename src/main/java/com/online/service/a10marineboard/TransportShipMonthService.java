package com.online.service.a10marineboard;

import com.online.entity.online.a10marineboard.TransportShipMonth;
import com.online.service.BaseService;

/**
 * 客货运输船舶每月活动水平信息服务接口
 *
 */
public interface TransportShipMonthService extends BaseService<TransportShipMonth, Integer> {
		
		public TransportShipMonth saveTransportShipMonth(TransportShipMonth transportShipMonth,Integer enterpriseId) throws Exception;
		public TransportShipMonth updateTransportShipMonth(TransportShipMonth transportShipMonth,Integer enterpriseId) throws Exception;
	
}