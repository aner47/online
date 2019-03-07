package com.online.service.impl.a7trafficpolice;



import org.springframework.stereotype.Service;

import com.online.entity.online.a7trafficpolice.MotorVehiclesInventory;
import com.online.service.a7trafficpolice.MotorVehiclesInventoryService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 机动车保有量信息服务实现
 *
 */
@Service("motorVehiclesInventoryServiceImpl")
public class MotorVehiclesInventoryServiceImpl extends BaseServiceImpl<MotorVehiclesInventory, Integer> implements MotorVehiclesInventoryService {
	
	
	@Override
	public MotorVehiclesInventory saveMotorVehiclesInventory(MotorVehiclesInventory motorVehiclesInventory) throws Exception {
		motorVehiclesInventory.setProject(SpringUtils.getCurrentProject());
		return save(motorVehiclesInventory);
	}


	@Override
	public MotorVehiclesInventory updateMotorVehiclesInventory(MotorVehiclesInventory motorVehiclesInventory) throws Exception {
		//郑有权
		
		return update(motorVehiclesInventory,"project");
	}
	
}
