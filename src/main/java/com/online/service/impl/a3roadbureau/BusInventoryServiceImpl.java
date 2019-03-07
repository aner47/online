package com.online.service.impl.a3roadbureau;

import org.springframework.stereotype.Service;

import com.online.entity.online.a3roadbureau.BusInventory;
import com.online.service.a3roadbureau.BusInventoryService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;

/**
 * 交通局-公交车保有量
 */
@Service("busInventoryServiceImpl")
public class BusInventoryServiceImpl extends BaseServiceImpl<BusInventory, Integer>
		implements BusInventoryService{

	
	
	@Override
	public BusInventory saveBusInventory(BusInventory busInventory) throws Exception {
		//郑有权
		busInventory.setProject(SpringUtils.getCurrentProject());
		return save(busInventory);
	}


	@Override
	public BusInventory updateBusInventory(BusInventory busInventory) throws Exception {
		//郑有权
		return update(busInventory,"project");
	}

	

}
