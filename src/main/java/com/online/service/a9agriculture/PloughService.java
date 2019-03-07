package com.online.service.a9agriculture;

import com.online.entity.online.a9agriculture.Plough;
import com.online.service.BaseService;

/**
 * 耕地信息服务接口
 *
 */
public interface PloughService extends BaseService<Plough, Integer> {
		
		public Plough savePlough(Plough plough) throws Exception;
		public Plough updatePlough(Plough plough) throws Exception;
	
}