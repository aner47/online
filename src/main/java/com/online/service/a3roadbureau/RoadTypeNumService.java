package com.online.service.a3roadbureau;

import com.online.entity.online.a3roadbureau.RoadTypeNum;
import com.online.service.BaseService;
/**
 * 交通局-道路类型及数量
 */
public interface RoadTypeNumService extends BaseService<RoadTypeNum, Integer> {
	
	public RoadTypeNum saveRoadTypeNum(RoadTypeNum roadTypeNum) throws Exception;
	public RoadTypeNum updateRoadTypeNum(RoadTypeNum roadTypeNum) throws Exception;

}