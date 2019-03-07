package com.online.service.a3roadbureau;

import com.online.entity.online.a3roadbureau.PassengerStation;
import com.online.service.BaseService;
import com.online.service.ExportService;
/**
 * 交通局-客运站/货运物流中心
 */
public interface PassengerStationService extends BaseService<PassengerStation, Integer> , ExportService {
	
	public PassengerStation savePassengerStation(PassengerStation passengerStation) throws Exception;
	public PassengerStation updatePassengerStation(PassengerStation passengerStation) throws Exception;

}