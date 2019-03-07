package com.online.service.impl.a9agriculture;



import org.springframework.stereotype.Service;

import com.online.entity.online.a9agriculture.FertilizerPesticide;
import com.online.service.a9agriculture.FertilizerPesticideService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 化肥与农药施用信息服务实现
 *
 */
@Service("fertilizerPesticideServiceImpl")
public class FertilizerPesticideServiceImpl extends BaseServiceImpl<FertilizerPesticide, Integer> implements FertilizerPesticideService {
	
	
	@Override
	public FertilizerPesticide saveFertilizerPesticide(FertilizerPesticide fertilizerPesticide) throws Exception {
		fertilizerPesticide.setProject(SpringUtils.getCurrentProject());
		return save(fertilizerPesticide);
	}


	@Override
	public FertilizerPesticide updateFertilizerPesticide(FertilizerPesticide fertilizerPesticide) throws Exception {
		//郑有权
		
		return update(fertilizerPesticide,"project");
	}
	
}
