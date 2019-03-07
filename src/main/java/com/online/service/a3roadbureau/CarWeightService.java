package com.online.service.a3roadbureau;

import com.online.entity.online.a3roadbureau.CarWeight;
import com.online.service.BaseService;
/**
 * 交通局-车重信息表
 */
public interface CarWeightService extends BaseService<CarWeight, Integer> {
	
	public CarWeight saveCarWeight(CarWeight carWeight) throws Exception;
	public CarWeight updateCarWeight(CarWeight carWeight) throws Exception;

}