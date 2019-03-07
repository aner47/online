package com.online.service.impl.a2urbanmanagementbureau;

import org.springframework.stereotype.Service;

import com.online.entity.online.a2urbanmanagementbureau.SlagCar;
import com.online.service.a2urbanmanagementbureau.SlagCarService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;

/**
 * 城管局	-全市渣土车信息表
 */
@Service("slagCarServiceImpl")
public class SlagCarServiceServiceImpl extends BaseServiceImpl<SlagCar, Integer>
		implements SlagCarService{

	
	@Override
	public SlagCar saveSlagCar(SlagCar SlagCar) throws Exception {
		SlagCar.setProject(SpringUtils.getCurrentProject());
		return save(SlagCar);
	}


	@Override
	public SlagCar updateSlagCar(SlagCar slagCar) throws Exception {
		//郑有权
		return update(slagCar,"project");
	}

	

}
