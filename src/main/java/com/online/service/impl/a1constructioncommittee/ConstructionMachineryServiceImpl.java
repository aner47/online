package com.online.service.impl.a1constructioncommittee;

import org.springframework.stereotype.Service;

import com.online.entity.online.a1constructioncommittee.ConstructionMachinery;
import com.online.service.a1constructioncommittee.ConstructionMachineryService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;

/**
 * 住建委-工程机械信息
 */
@Service("constructionMachineryServiceImpl")
public class ConstructionMachineryServiceImpl extends BaseServiceImpl<ConstructionMachinery, Integer>
		implements ConstructionMachineryService{

	
	@Override
	public ConstructionMachinery saveConstructionMachinery(ConstructionMachinery constructionMachinery) throws Exception {
		//郑有权
		
		constructionMachinery.setProject(SpringUtils.getCurrentProject());
		return save(constructionMachinery);
	}


	@Override
	public ConstructionMachinery updateConstructionMachinery(ConstructionMachinery constructionMachinery) throws Exception {
		//郑有权
		
		return update(constructionMachinery,"project");
	}

	

}
