package com.online.service.impl.a3roadbureau;

import org.springframework.stereotype.Service;

import com.online.entity.online.a3roadbureau.CarWeight;
import com.online.service.a3roadbureau.CarWeightService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;

/**
 * 交通局-车重信息表
 */
@Service("carWeightServiceImpl")
public class CarWeightServiceImpl extends BaseServiceImpl<CarWeight, Integer>
		implements CarWeightService{

	
	
	@Override
	public CarWeight saveCarWeight(CarWeight carWeight) throws Exception {
		//郑有权
		carWeight.setProject(SpringUtils.getCurrentProject());
		return save(carWeight);
	}


	@Override
	public CarWeight updateCarWeight(CarWeight carWeight) throws Exception {
		//郑有权
		return update(carWeight,"project");
	}

	

}
