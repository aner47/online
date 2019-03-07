package com.online.service.impl.a3roadbureau;

import org.springframework.stereotype.Service;

import com.online.entity.online.a3roadbureau.RoadTypeNum;
import com.online.service.a3roadbureau.RoadTypeNumService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;

/**
 * 交通局-道路类型及数量
 */
@Service("roadTypeNumServiceImpl")
public class RoadTypeNumServiceImpl extends BaseServiceImpl<RoadTypeNum, Integer>
		implements RoadTypeNumService{

	
	
	@Override
	public RoadTypeNum saveRoadTypeNum(RoadTypeNum roadTypeNum) throws Exception {
		//郑有权
		roadTypeNum.setProject(SpringUtils.getCurrentProject());
		return save(roadTypeNum);
	}


	@Override
	public RoadTypeNum updateRoadTypeNum(RoadTypeNum roadTypeNum) throws Exception {
		//郑有权
		return update(roadTypeNum,"project");
	}

	

}
