package com.online.service.a3roadbureau;

import com.online.entity.online.a3roadbureau.BusInventory;
import com.online.service.BaseService;
/**
 * 交通局-公交车保有量
 */
public interface BusInventoryService extends BaseService<BusInventory, Integer> {
	
	public BusInventory saveBusInventory(BusInventory busInventory) throws Exception;
	public BusInventory updateBusInventory(BusInventory busInventory) throws Exception;

}