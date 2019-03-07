package com.online.service.impl.a17fuelgas;



import org.springframework.stereotype.Service;

import com.online.entity.online.a17fuelgas.CivilFuelGasMonth;
import com.online.service.a17fuelgas.CivilFuelGasMonthService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 民用燃气消耗分月统计服务实现
 *
 */
@Service("civilFuelGasMonthServiceImpl")
public class CivilFuelGasMonthServiceImpl extends BaseServiceImpl<CivilFuelGasMonth, Integer> implements CivilFuelGasMonthService {
	
	
	@Override
	public CivilFuelGasMonth saveCivilFuelGasMonth(CivilFuelGasMonth civilFuelGasMonth) throws Exception {
		civilFuelGasMonth.setProject(SpringUtils.getCurrentProject());
		return save(civilFuelGasMonth);
	}


	@Override
	public CivilFuelGasMonth updateCivilFuelGasMonth(CivilFuelGasMonth civilFuelGasMonth) throws Exception {
		//郑有权
		
		return update(civilFuelGasMonth,"project");
	}
	
}
