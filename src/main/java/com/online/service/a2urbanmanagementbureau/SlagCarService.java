package com.online.service.a2urbanmanagementbureau;

import com.online.entity.online.a2urbanmanagementbureau.SlagCar;
import com.online.service.BaseService;
/**
 * 城管局	-全市渣土车信息表
 */
public interface SlagCarService extends BaseService<SlagCar, Integer> {
	
	public SlagCar saveSlagCar(SlagCar SlagCar) throws Exception;
	public SlagCar updateSlagCar(SlagCar SlagCar) throws Exception;

}