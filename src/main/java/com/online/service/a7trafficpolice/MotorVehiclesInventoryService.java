package com.online.service.a7trafficpolice;

import com.online.entity.online.a7trafficpolice.MotorVehiclesInventory;
import com.online.service.BaseService;

/**
 * 机动车保有量信息服务接口
 *
 */
public interface MotorVehiclesInventoryService extends BaseService<MotorVehiclesInventory, Integer> {
		
		public MotorVehiclesInventory saveMotorVehiclesInventory(MotorVehiclesInventory motorVehiclesInventory) throws Exception;
		public MotorVehiclesInventory updateMotorVehiclesInventory(MotorVehiclesInventory motorVehiclesInventory) throws Exception;
	
}