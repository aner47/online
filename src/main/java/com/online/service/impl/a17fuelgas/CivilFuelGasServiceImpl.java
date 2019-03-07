package com.online.service.impl.a17fuelgas;



import org.springframework.stereotype.Service;

import com.online.entity.online.a17fuelgas.CivilFuelGas;
import com.online.service.a17fuelgas.CivilFuelGasService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 民用燃气消耗信息服务实现
 *
 */
@Service("civilFuelGasServiceImpl")
public class CivilFuelGasServiceImpl extends BaseServiceImpl<CivilFuelGas, Integer> implements CivilFuelGasService {
	
	
	@Override
	public CivilFuelGas saveCivilFuelGas(CivilFuelGas civilFuelGas) throws Exception {
		civilFuelGas.setProject(SpringUtils.getCurrentProject());
		return save(civilFuelGas);
	}


	@Override
	public CivilFuelGas updateCivilFuelGas(CivilFuelGas civilFuelGas) throws Exception {
		//郑有权
		
		return update(civilFuelGas,"project");
	}
	
}
