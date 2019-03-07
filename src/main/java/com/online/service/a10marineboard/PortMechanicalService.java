package com.online.service.a10marineboard;

import com.online.entity.online.a10marineboard.PortMechanical;
import com.online.service.BaseService;

/**
 * 港口机械信息服务接口
 *
 */
public interface PortMechanicalService extends BaseService<PortMechanical, Integer> {
		
		public PortMechanical savePortMechanical(PortMechanical portMechanical,Integer enterpriseId) throws Exception;
		public PortMechanical updatePortMechanical(PortMechanical portMechanical,Integer enterpriseId) throws Exception;
	
}