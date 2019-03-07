package com.online.service.impl.a9agriculture;



import org.springframework.stereotype.Service;

import com.online.entity.online.a9agriculture.AgriculturalMachinery;
import com.online.service.a9agriculture.AgriculturalMachineryService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 农业机械信息服务实现
 *
 */
@Service("agriculturalMachineryServiceImpl")
public class AgriculturalMachineryServiceImpl extends BaseServiceImpl<AgriculturalMachinery, Integer> implements AgriculturalMachineryService {
	
	
	@Override
	public AgriculturalMachinery saveAgriculturalMachinery(AgriculturalMachinery agriculturalMachinery) throws Exception {
		agriculturalMachinery.setProject(SpringUtils.getCurrentProject());
		return save(agriculturalMachinery);
	}


	@Override
	public AgriculturalMachinery updateAgriculturalMachinery(AgriculturalMachinery agriculturalMachinery) throws Exception {
		//郑有权
		
		return update(agriculturalMachinery,"project");
	}
	
}
