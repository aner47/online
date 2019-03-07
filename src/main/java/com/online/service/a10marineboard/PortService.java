package com.online.service.a10marineboard;

import com.online.entity.online.a10marineboard.Port;
import com.online.service.BaseService;

/**
 * 港口信息服务接口
 *
 */
public interface PortService extends BaseService<Port, Integer> {
		
		public Port savePort(Port port) throws Exception;
		public Port updatePort(Port port) throws Exception;
	
}